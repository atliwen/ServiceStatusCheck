import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import axios from 'axios'
import './plugins/vxetable'
import './plugins/echarts'

Vue.config.productionTip = false
// axios.defaults.baseURL = 'http://localhost:11201/'
axios.defaults.baseURL = '/'
Vue.prototype.$http = axios

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
