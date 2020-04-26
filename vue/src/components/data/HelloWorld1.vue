<template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/test' }">图形测试</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- Form -->
    <el-button type="text"
               @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>

    <el-dialog title="详细信息"
               :visible.sync="dialogFormVisible"
               width="45%">
      <el-form :model="udata">
        <el-form-item label="服务名称 ："
                      :label-width="formLabelWidth">
          <el-tag>{{udata.service}}</el-tag>
        </el-form-item>
        <el-form-item label="ip ："
                      :label-width="formLabelWidth">
          <el-tag>{{udata.ip}}</el-tag>
        </el-form-item>
        <el-form-item label="负责人 ："
                      :label-width="formLabelWidth">
          <el-tag>{{udata.user}}</el-tag>
        </el-form-item>
        <el-form-item label="负责人 ："
                      :label-width="formLabelWidth">
          <el-tag>{{udata.user}}</el-tag>
        </el-form-item>
        <el-form-item label="请求时长 ："
                      :label-width="formLabelWidth">
          <el-tag>{{udata.calltime}}</el-tag>
        </el-form-item>

        <el-form-item label="状态 ："
                      :label-width="formLabelWidth">
          <el-tooltip class="item"
                      effect="dark"
                      content="1 成功  ， 2 down ， 3 访问异常 ， 4 发送通知异常"
                      placement="top">
            <el-tag>{{udata.state}}</el-tag>
          </el-tooltip>
        </el-form-item>
        <el-form-item label="处理时间 ："
                      :label-width="formLabelWidth">
          <el-date-picker v-model="udata.createdate"
                          type="datetime"
                          value-format="timestamp"
                          placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="响应消息 ："
                      :label-width="formLabelWidth">
          <el-input type="textarea"
                    autosize
                    placeholder="请输入内容"
                    v-model="udata.data">
          </el-input>

        </el-form-item>
      </el-form>
    </el-dialog>

  </el-card>
</template>
<script>
export default {
  data () {
    return {
      udata: {
        id: 1,
        service: '测试服务',
        ip: '10.10.12.22',
        notice: 'http://baidu.com.cn',
        user: '李文',
        update: null,
        createdate: 1587094398000,
        state: 4,
        calltime: -2342,
        data: "{a:'a'}"
      },
      dialogFormVisible: false,
      formLabelWidth: '120px'
    }
  }
}
</script>

<style scoped>
.echarts {
  width: 100%;
  height: 700px;
}
.el-alert {
  width: 90%;
  padding-right: 10 !important;
  margin-left: 2%;
}
</style>
