import Vue from 'vue'
import XEUtils from 'xe-utils'

// import {
//   // 实例
//   VXETable,
//   // 模块
//   Edit,
//   Column,
//   Header,
//   // 核心
//   Table,
//   Toolbar,
//   Button,
//   Modal,
//   Loading,
//   Form
// } from 'vxe-table'
import zhCNLocat from 'vxe-table/lib/locale/lang/zh-CN'
import VXETable, { ModalController } from 'vxe-table'
import 'vxe-table/lib/index.css'
// 按需加载的方式默认是不带国际化的，需要自行导入
Vue.prototype.$XModal = ModalController
Vue.prototype.$XEUtils = XEUtils
VXETable.setup({
  i18n: (key, value) => XEUtils.get(zhCNLocat, key)
})
Vue.use(VXETable)
// 先安装依赖模块
// Vue.use(Edit)
// Vue.use(Column)
// Vue.use(Header)
// Vue.use(Toolbar)
// Vue.use(Button)
// Vue.use(Modal)
// Vue.use(Form)
// Vue.use(Loading)
// // 再安装核心库
// Vue.use(Table)
