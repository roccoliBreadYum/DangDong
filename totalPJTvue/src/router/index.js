import { createRouter, createWebHistory } from "vue-router";

import MainView from "@/views/MainView.vue";
import RegistView from "@/views/RegistView.vue";
import UserInfo from "@/components/main/userInfo.vue";
import CalendarView from "@/views/CalendarView.vue";
import HomeView from "@/views/HomeView.vue";
import SearchView from "@/views/SearchView.vue";
import MyPageView from "@/views/MyPageView.vue";
import FavoriteView from "@/views/FavoriteView.vue";
import Cookies from "js-cookie";
import axios from "axios";
import EnterView from "@/views/EnterView.vue";
import UserLogin from "@/components/user/UserLogin.vue";
import UserCreate from "@/components/user/UserCreate.vue";

const getCookie = function (name) {
  return Cookies.get(name);
};

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

      children: [
        {
          path: "info",
          name: "info",
          component: UserInfo,
        },
      ],
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
      path: "/enter",
      name: "enter",
      component: EnterView,

      children: [
        {
          path: "",
          name: "login",
          component: UserLogin,
        },
        {
          path: "create",
          name: "userCreate",
          component: UserCreate,
        },
      ],
    },
  ],
});

// 네비게이션 가드
router.beforeEach((to, from, next) => {
  const accessToken = sessionStorage.getItem("access-token");
  const refreshToken = getCookie("refreshToken");

  // 현재 경로가 회원가입 페이지인지 확인
  if(to.name==="userCreate"){
    return next();
  }
    
    // 현재 경로가 로그인 페이지인지 확인
  if (to.name === "login" && !accessToken && !refreshToken) {
    return next();
  }

  axios
    .post("http://localhost:8080/api-user/user/refresh", null, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then((res) => {
      sessionStorage.setItem("access-token", res.data["access-token"]);
      next();
    })
    .catch((err) => {
      console.log(err);
      router.push({ name: "login" });
    });
});

export default router;
