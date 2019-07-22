import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/Login'
import store from '@/store'

Vue.use(Router)

const requireAuth = () => (from, to, next) => {
  console.log('state: ' + store.getters.isLoggedOn)
  return store.getters.isLoggedOn ? next() : next('/login')
}

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
      beforeEnter: requireAuth()
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
