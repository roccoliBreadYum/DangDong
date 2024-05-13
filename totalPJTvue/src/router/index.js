import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/LoginView.vue'
import MainView from '@/views/MainView.vue'
import RegistView from '@/views/RegistView.vue'
import UserInfo from '@/components/main/userInfo.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView
    },
    {
      path: '/main',
      name: 'main',
      component: MainView,
      children:[
        {
          path: 'info',
          name: 'info',
          component: UserInfo
        }
      ]
    },
    {
      path: '/regist',
      name: 'regist',
      component: RegistView
    },
    
  ]
})

export default router
