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
import Cookies from 'js-cookie';

export function getCookie(name) {
  return Cookies.get(name);
}

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
      path: "/login",
      name: "login",
      component: LoginView,
    },
  ],
});

// //네비게이션 가드((뷰 라우터로 URL 접근에 대해서 처리할 수 있음)
// router.beforeEach((to, from, next) => { //여기서 모든 라우팅이 대기 상태가 됨
//   /**
//    * to: 이동할 url 정보가 담긴 라우터 객체
//    * from: 현재 url 정보가 담긴 라우터 객체
//    * next: to에서 지정한 url로 이동하기 위해 꼭 호출해야 하는 함수
//    * next() 가 호출되기 전까지 화면 전환되지 않음
//    */

//   console.log(getCookie('refreshToken') !== undefined)
//   console.log(getCookie('refreshToken'))
//   console.log(sessionStorage.getItem('access-token'))

//   if(sessionStorage.getItem('access-token')===null && getCookie('refreshToken') !== undefined){
//     //refreshToken은 있고 accessToken이 없을 경우 토큰 재발급 요청
//     // await store.dispatch('refreshToken');
//     console.log("토큰 재발급 요청")
//     return {name: 'login'};
//   }
//   if (sessionStorage.getItem('access-token')){
//     console.log("accessToken이 있음")
//     //accessToken이 있을 경우 진행
//     return {name: 'home'};
//   }
//   if(sessionStorage.getItem('access-token')===null && getCookie('refreshToken') === undefined){
//     //2개 토큰이 모두 없을 경우 로그인페이지로
//     console.log("토큰 없음")
//     return {name: 'login'};
//   }
//   return {name: 'home'};
// })

// 네비게이션 가드
// 네비게이션 가드
router.beforeEach((to, from, next) => {
  const accessToken = sessionStorage.getItem('access-token');
  const refreshToken = getCookie('refreshToken');

  console.log('Refresh Token:', refreshToken);
  console.log('Access Token:', accessToken);

  // 현재 경로가 로그인 페이지인지 확인
  if (to.name === 'login' && !accessToken && !refreshToken) {
    console.log("로그인 페이지로 이동");
    return next();
  }

  if (!accessToken && refreshToken) {
    // refreshToken은 있고 accessToken이 없을 경우 토큰 재발급 요청
    console.log("토큰 재발급 요청");
    // 여기에 토큰 재발급 로직을 추가해야 합니다.
    // 예를 들어, Vuex 스토어를 사용하여 토큰 재발급 요청을 할 수 있습니다.
    // await store.dispatch('refreshToken');
    // 비동기 작업을 처리하고 나서 next()를 호출해야 합니다.
    return next({ name: 'login' }); // 재발급 요청 후 로그인 페이지로 리디렉션
  }

  if (accessToken) {
    console.log("accessToken이 있음");
    // accessToken이 있을 경우 진행
    return next();
  }

  if (!accessToken && !refreshToken) {
    // 2개 토큰이 모두 없을 경우 로그인 페이지로
    console.log("토큰 없음");
    return next({ name: 'login' });
  }

  // 기본적으로 진행
  next();
});

export default router;
