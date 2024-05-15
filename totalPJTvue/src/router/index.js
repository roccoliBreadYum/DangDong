import { createRouter, createWebHistory } from "vue-router";

import MainView from "@/views/MainView.vue";
import RegistView from "@/views/RegistView.vue";
import UserInfo from "@/components/main/userInfo.vue";
import CalendarView from "@/views/CalendarView.vue";
import HomeView from "@/views/HomeView.vue";
import SearchView from "@/views/SearchView.vue";
import MyPageView from "@/views/MyPageView.vue";
import FavoriteView from "@/views/FavoriteView.vue";
import LoginView from "@/views/LoginView.vue";
import StoreList from "@/components/store/StoreList.vue";
import ExcerciseCategory from "@/components/main/excerciseCategory.vue";
import StoreDetail from "@/components/store/StoreDetail.vue";
import StoreView from "@/views/StoreView.vue";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/calendar",
      name: "calendar",
      component: CalendarView,
    },
    {
      path: "/search",
      name: "search",
      component: SearchView,
    },
    {
      path: "/main",
      name: "main",
      component: MainView,
    },
    {
      path: "/store",
      name: "storeView",
      component: StoreView,
      children:[
        {
          path: "",
          name: "storeList",
          component: StoreList,
        },
        {
          path:":storeId/:userId",
          name:"storeDetail",
          component: StoreDetail,
        }
      ]
    },
    {
      path: "/myPage",
      name: "myPage",
      component: MyPageView,
    },
    {
      path: "/favorite",
      name: "favorite",
      component: FavoriteView,
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
  ],
});

export default router;
