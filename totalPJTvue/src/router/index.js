import { createRouter, createWebHistory } from 'vue-router'
import CalendarView from '@/views/CalendarView.vue'
import HomeView from '@/views/HomeView.vue'
import SearchView from '@/views/SearchView.vue'
import MyPageView from '@/views/MyPageView.vue'
import FavoriteView from '@/views/FavoriteView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/calendar',
      name: 'calendar',
      component: CalendarView
    },
    {
      path: '/search',
      name: 'search',
      component: SearchView
    },
    {
      path: '/myPage',
      name: 'myPage',
      component: MyPageView
    },
    {
      path: '/favorite',
      name: 'favorite',
      component: FavoriteView
    },
  ]
})

export default router
