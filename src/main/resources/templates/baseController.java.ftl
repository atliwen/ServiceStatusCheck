package ${package.Controller}.base;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atliwen.domain.page.QueryPage;
import com.atliwen.mic.entity.atliwenResponseEntity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.atliwen.domain.validator.ValidList;
import org.springframework.boot.autoconfigure.atliwen.domain.validator.groups.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
* @author 李文
* @date 2019-08-15 15:31
**/
@Slf4j
public class BaseController<T>
 {
 @Autowired(required = false)
 protected IService<T> baseService;

  /**
  * 查询
  */
  @ApiOperation(value = "查询")
  @GetMapping("/queryList")
  @ResponseBody
  public atliwenResponseEntity queryLearnList() {
  try {
  List datas = baseService.list();
  return new atliwenResponseEntity<>(datas);
  } catch (Exception e) {
  log.error(" 获取所以信息异常 ", e);
  return new atliwenResponseEntity<>(false, "5001");
  }
  }


  /**
  * 新增
  */
  @ApiOperation(value = "批量新增")
  @PostMapping("/adds")
  @ResponseBody
  public atliwenResponseEntity adds(@Validated(IAdd.class) @RequestBody ValidList<T> data) {
   try {
   if (baseService.saveBatch(data.getList())) {
   return new atliwenResponseEntity<>(true);
   } else {
   return new atliwenResponseEntity<>(false, "4001");
   }

   } catch (Exception e) {
   log.error(" 创建异常 ", e);
   return new atliwenResponseEntity<>(false, "5001");
   }
   }


   /**
   * 新增
   */
   @ApiOperation(value = "新增")
   @PostMapping("/add")
   @ResponseBody
   public atliwenResponseEntity add(@Validated(IAdd.class) @RequestBody T data) {

   try {

   if (baseService.save(data)) {
   return new atliwenResponseEntity<>(true);
   } else {
   return new atliwenResponseEntity<>(false, "4001");
   }

   } catch (Exception e) {
   log.error(" 创建异常 ", e);
   return new atliwenResponseEntity<>(false, "5001");
   }
   }


   /**
   * 通过ID修改
   */
   @ApiOperation(value = "通过ID修改")
   @PostMapping("/update")
   @ResponseBody
   public atliwenResponseEntity update(@Validated(IUpdate.class) @RequestBody T data) {
   try {
   if (baseService.updateById(data)) {
   return new atliwenResponseEntity<>(true);
   } else {
   return new atliwenResponseEntity<>(false, "4001");
   }
   } catch (Exception e) {
   log.error(" 获取信息 ", e);
   return new atliwenResponseEntity<>(false, "5001");
   }
   }


   /**
   * 删除
   */
   @ApiOperation(value = "删除")
   @PostMapping("/delete")
   @ResponseBody
   public atliwenResponseEntity delete(@Validated(IDelete.class) @RequestBody T data) {
   try {
   if (baseService.remove(new QueryWrapper<>(data))) {
   return new atliwenResponseEntity<>(true);
   } else {
   return new atliwenResponseEntity<>(false, "4001");
   }
   } catch (Exception e) {
   log.error(" 获取信息 ", e);
   return new atliwenResponseEntity<>(false, "5001");
   }
   }

   /**
   * 通过查询条件获取多条数据
   */
   @ApiOperation(value = "通过查询条件获取多条数据")
   @PostMapping("/queryWhereList")
   @ResponseBody
   public atliwenResponseEntity queryWhereList(@Validated(ISelect.class) @RequestBody T user) {
   try {
   List datas = baseService.list(new QueryWrapper<>(user));
   return new atliwenResponseEntity<>(datas);
   } catch (Exception e) {
   log.error(" 获取所以信息异常 ", e);
   return new atliwenResponseEntity<>(false, "5001");
   }
   }


   @ApiOperation(value = "带有条件的分页查询")
   @PostMapping("/queryPage")
   @ResponseBody
   public atliwenResponseEntity queryPage(@Validated(IPage.class) @RequestBody QueryPage<T> p) {
    try {
    Page datas = baseService.page(p.getPage(), new QueryWrapper<>(p.getWhere()));
    return new atliwenResponseEntity<>(datas);
    } catch (Exception e) {
    log.error(" 获取所以信息异常 ", e);
    return new atliwenResponseEntity<>(false, "5001");
    }
    }


    }
