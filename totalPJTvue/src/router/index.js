import { createRouter, createWebHistory } from "vue-router";
import { useAuthStore } from "@/stores/auth";
import CalendarView from "@/views/CalendarView.vue";
import HomeView from "@/views/HomeView.vue";
import SearchView from "@/views/SearchView.vue";
import UserView from "@/views/UserView.vue";
import FavoriteView from "@/views/FavoriteView.vue";
import Cookies from "js-cookie";
import axios from "axios";
import EnterView from "@/views/EnterView.vue";
import UserLogin from "@/components/user/UserLogin.vue";
import UserCreate from "@/components/user/UserCreate.vue";
import StoreList from "@/components/store/StoreList.vue";
import StoreTicketList from "@/components/store/StoreTicketList.vue";
import StoreDetail from "@/components/store/StoreDetail.vue";
import StoreRegist from "@/components/store/StoreRegist.vue";
import StoreUpdate from "@/components/store/StoreUpdate.vue";
import StoreLesson from "@/components/store/StoreLesson.vue";
import StoreView from "@/views/StoreView.vue";
import UserPage from "@/components/user/UserPage.vue";
import UserUpdate from "@/components/user/UserUpdate.vue";
import UserTicket from "@/components/user/UserTicket.vue";
import PayView from "@/views/PayView.vue";
import PayCheckout from "@/components/pay/PayCheckout.vue";
import PaySuccess from "@/components/pay/PaySuccess.vue";
import PayFail from "@/components/pay/PayFail.vue";
import PayHow from "@/components/pay/PayHow.vue";
import VideoView from "@/views/VideoView.vue";
import Video from "@/components/video/Video.vue";

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
      path: "/pay",
      name: "pay",
      component: PayView,
      children: [
        {
          path: ":lessonId",
          name: "payHow",
          component: PayHow,
        },
        {
          path: "checkout/:ticketId",
          name: "payCheckout",
          component: PayCheckout,
        },
        {
          path: "success",
          name: "paySuccess",
          component: PaySuccess,
        },
        {
          path: "fail",
          name: "payFail",
          component: PayFail,
        },
      ],
    },
    {
      path: "/store",
      name: "storeView",
      component: StoreView,
      children: [
        {
          path: "",
          name: "storeList",
          component: StoreList,
        },
        {
          path: ":storeId",
          name: "storeDetail",
          component: StoreDetail,
        },
        {
          path: ":storeId/ticket",
          name: "sellTicket",
          component: StoreTicketList,
        },
        {
          path: "regist",
          name: "storeRegist",
          component: StoreRegist,
        },
        {
          path: ":storeId/update",
          name: "storeUpdate",
          component: StoreUpdate,
        },
        {
          path: ":storeId/lesson",
          name: "storeLesson",
          component: StoreLesson,
        },
      ],
    },
    {
      path: "/my",
      component: UserView,
      children: [
        {
          path: "",
          name: "userPage",
          component: UserPage,
        },
        {
          path: "/update",
          name: "userUpdate",
          component: UserUpdate,
        },
        {
          path: "ticket",
          name: "userTicket",
          component: UserTicket
        }
      ],
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
    {
      path: "/video",
      component: VideoView,
      children: [
        {
          path: "",
          name: "video",
          component: Video,
        },
      ],
    },
  ],
});

router.beforeEach((to, from, next) => {
  const store = useAuthStore();
  const accessToken = store.getAccessToken();

  const refreshToken = getCookie("refreshToken");

  // 현재 경로가 회원가입 페이지인지 확인
  if (to.name === "userCreate") {
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

  next();
});

export default router;
