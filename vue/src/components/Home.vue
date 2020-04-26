<template>
  <el-container class="home-contatiner">
    <el-header>
      <div>
        <img src="../assets/logo.png"
             style="height: 50px" />
        <span>服务健康监控系统</span>
      </div>
    </el-header>
    <el-container>
      <el-aside width="150px">
        <el-menu background-color="#333744"
                 text-color="#fff"
                 active-text-color="#409eff"
                 unique-opened
                 router>
          <el-menu-item :index="'/' +'datas' ">
            <i class="el-icon-setting"></i>
            <span slot="title">服务配置</span>
          </el-menu-item>
          <el-menu-item :index="'/datas2'">
            <i class="el-icon-setting"></i>
            <span slot="title">实时状态</span>
          </el-menu-item>
          <el-menu-item :index="'/RealTimeState'">
            <i class="el-icon-setting"></i>
            <span slot="title">图形数据</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <!-- 主体 -->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<style lang="less" scoped>
.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0%;
  color: #ffffff;
  font-size: 20px;
  > div {
    display: flex;
    align-items: center;
    margin-left: 15px;
    span {
      margin-left: 15px;
    }
  }
}
.el-aside {
  background-color: #333744;
  .el-menu {
    border-right: none;
  }
}
.el-main {
  background-color: #eaedf2;
}
.home-contatiner {
  height: 100%;
}
</style>
<script>
export default {}
</script>
