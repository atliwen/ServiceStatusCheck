import echarts from 'echarts'
import vueecharts from 'vue-echarts'
import Vue from 'vue'

Vue.use(echarts)
Vue.component('v-chart', vueecharts)
Vue.prototype.$echarts = echarts
