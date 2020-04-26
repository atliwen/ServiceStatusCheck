
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//import com.sun.istack.internal.Nullable;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.util.StringUtils;
//import org.yaml.snakeyaml.Yaml;
//import org.yaml.snakeyaml.reader.UnicodeReader;
//
//import java.io.IOException;
//import java.util.*;
//
///**
// * my bites 代码生成器
// *
// * @author atliwen
// */
//public class MysqlGenerator
//{
//
//
//    /**
//     * 直接指定包路径
//     * @param c 通常是 spring boot main 类
//     * @throws IOException 创建异常
//     */
//    public static void generate(Class c) throws Exception {
//        execute(c.getPackage().getName());
//    }
//
//
//    /**
//     * 运行生成
//     */
//    public static void main(String[] args) throws Exception {
//
//        String packageName = MysqlGenerator.class.getPackage().getName();
//        execute(packageName);
//
//    }
//
//    private static void execute(String packageName) throws Exception {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//        // 全局配置   更多设置 请访问 https://mybatis.plus/config/
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("atliwen");
//        gc.setOpen(false);
//        gc.setEnableCache(false);
//        mpg.setGlobalConfig(gc);
//        // 数据源配置
//        mpg.setDataSource(getDataSourceConfigToYaml());
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent(packageName);
//        mpg.setPackageInfo(pc);
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig()
//        {
//            @Override
//            public void initMap() {
//                Map<String, Object> map = new HashMap<>();
//                map.put("enableCache", false);
//                this.setMap(map);
//            }
//        };
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/mapper.xml.ftl")
//        {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return projectPath + "/src/main/resources/mapper/"
//                        + "/" + tableInfo.getEntityName() + "Mapper.xml";
//            }
//        });
//
//        focList.add(new FileOutConfig("/templates/baseController.java.ftl")
//        {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return projectPath + "/src/main/java/"+packageName.replace(".","/")
//                        + "/controller/base/BaseController.java";
//            }
//        });
//
//
//
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//        mpg.setTemplate(new TemplateConfig().setXml(null));
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        //strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
//        strategy.setEntityLombokModel(true);
//
//        strategy.setSuperControllerClass(packageName + ".base.BaseController");
//        //strategy.setInclude(scanner());
//        //strategy.setSuperEntityColumns("id");
//        strategy.setControllerMappingHyphenStyle(true);
//        //strategy.setTablePrefix(pc.getModuleName() + "_");
//        mpg.setStrategy(strategy);
//        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
//    }
//
//
//    private static String scanner() throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + "表名" + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (!org.springframework.util.StringUtils.isEmpty(ipt)) {
//                return ipt;
//            }
//        }
//        throw new Exception("请输入正确的" + "表名" + "！");
//    }
//
//    private static DataSourceConfig getDataSourceConfigToYaml() throws IOException {
//        //返回的结果
//        Map<String, Object> result = new LinkedHashMap<>();
//        //读取方式
//        UnicodeReader reader = new UnicodeReader(new ClassPathResource("/application.yml").getInputStream());
//        //单文件处理
//        Yaml yaml = new Yaml();
//        Object object = yaml.load(reader);
//        //这里只是简单处理，需要多个方式可以自己添加
//        if (object instanceof Map) {
//            Map map = (Map) object;
//            buildFlattenedMap(result, map, null);
//        }
//        reader.close();
//
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl(result.get("spring.datasource.url").toString());
//        dsc.setDriverName(result.get("spring.datasource.driver-class-name").toString());
//        dsc.setUsername(result.get("spring.datasource.username").toString());
//        dsc.setPassword(result.get("spring.datasource.password").toString());
//
//        return dsc;
//    }
//
//    private static void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, @Nullable String path) {
//        //循环读取原数据
//        source.forEach((key, value) -> {
//            //如果存在路径进行拼接
//            if (StringUtils.hasText(path)) {
//                if (key.startsWith("[")) {
//                    key = path + key;
//                } else {
//                    key = path + '.' + key;
//                }
//            }
//            //数据类型匹配
//            if (value instanceof String) {
//                result.put(key, value);
//            } else if (value instanceof Map) {
//                //如果是map,就继续读取
//                Map<String, Object> map = (Map) value;
//                buildFlattenedMap(result, map, key);
//            } else if (value instanceof Collection) {
//                Collection<Object> collection = (Collection) value;
//                if (collection.isEmpty()) {
//                    result.put(key, "");
//                } else {
//                    int count = 0;
//                    Iterator var7 = collection.iterator();
//
//                    while (var7.hasNext()) {
//                        Object object = var7.next();
//                        buildFlattenedMap(result, Collections.singletonMap("[" + count++ + "]", object), key);
//                    }
//                }
//            } else {
//                result.put(key, value != null ? value : "");
//            }
//        });
//    }
//
//
//}
