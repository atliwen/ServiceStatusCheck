import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import welcome from '../components/welcome.vue'
import datas from '../components/data/datas.vue'
import datas2 from '../components/data/datas2.vue'
// import HelloWorld1 from '../components/data/HelloWorld1.vue'
import RealTimeState from '../components/data/RealTimeState.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  }, {
    path: '/',
    component: Home,
    redirect: '/welcome',
    children: [{
      path: '/welcome',
      component: welcome
    }, {
      path: '/datas',
      component: datas
    }, {
      path: '/datas2',
      component: datas2
    }, {
      path: '/RealTimeState',
      component: RealTimeState
    }]
  }
]

const router = new VueRouter({
  routes
})

export default router
