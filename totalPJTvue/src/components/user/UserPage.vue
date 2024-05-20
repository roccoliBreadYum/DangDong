<template>
  <div class="container" id="background">
    <div
      id="user-info"
      class="d-flex flex-row justify-content-around align-items-stretch p-4 m-2"
    >
      <template v-if="userStore.loginUserInfo.img !== null">
        <div id="user-img" class="d-flex">
          <img
            :src="`http://localhost:8080/resources/${userStore.loginUserInfo.img}`"
            class="rounded-circle"
            alt="user img"
          />
        </div>
      </template>
      <template v-else>
        <div id="user-img" class="d-flex">
          <img src="@/assets/tmp_user.png" alt="user img" />
        </div>
      </template>

      <div
        id="user-name"
        class="d-flex align-items-center justify-content-center"
      >
        <p>{{ userStore.loginUserInfo.name }} ❤</p>
      </div>
      <div id="user-state-nav" class="d-flex flex-column">
        <div
          id="user-logout"
          class="d-flex flex-row align-items-center justify-content-center my-1"
        >
          <div id="user-logout-img">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="16"
              height="16"
              fill="currentColor"
              class="bi bi-box-arrow-right"
              viewBox="0 0 16 16"
            >
              <path
                fill-rule="evenodd"
                d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0z"
              />
              <path
                fill-rule="evenodd"
                d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708z"
              />
            </svg>
          </div>
          <button @click.prevent="logout">로그아웃</button>
        </div>
        <div id="user-update">
          <RouterLink :to="{ name: 'userUpdate' }">개인정보 수정 ></RouterLink>
        </div>
      </div>
    </div>
    <div
      id="user-nav"
      class="d-flex flex-column justify-content-around p-2 m-2"
    >
      <div id="favorite-manage" class="d-flex flex-row">
        <div class="mx-3">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-star"
            viewBox="0 0 16 16"
          >
            <path
              d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.56.56 0 0 0-.163-.505L1.71 6.745l4.052-.576a.53.53 0 0 0 .393-.288L8 2.223l1.847 3.658a.53.53 0 0 0 .393.288l4.052.575-2.906 2.77a.56.56 0 0 0-.163.506l.694 3.957-3.686-1.894a.5.5 0 0 0-.461 0z"
            />
          </svg>
        </div>
        <RouterLink to="/favorite">즐겨찾기 관리</RouterLink>
      </div>
      <div id="calender-manage" class="d-flex flex-row">
        <div class="mx-3">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-calendar-check"
            viewBox="0 0 16 16"
          >
            <path
              d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0"
            />
            <path
              d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"
            />
          </svg>
        </div>
        <RouterLink to="/calendar">캘린더 관리</RouterLink>
      </div>
      <div id="ticket-manage" class="d-flex flex-row">
        <div class="mx-3">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-ticket-perforated"
            viewBox="0 0 16 16"
          >
            <path
              d="M4 4.85v.9h1v-.9zm7 0v.9h1v-.9zm-7 1.8v.9h1v-.9zm7 0v.9h1v-.9zm-7 1.8v.9h1v-.9zm7 0v.9h1v-.9zm-7 1.8v.9h1v-.9zm7 0v.9h1v-.9z"
            />
            <path
              d="M1.5 3A1.5 1.5 0 0 0 0 4.5V6a.5.5 0 0 0 .5.5 1.5 1.5 0 1 1 0 3 .5.5 0 0 0-.5.5v1.5A1.5 1.5 0 0 0 1.5 13h13a1.5 1.5 0 0 0 1.5-1.5V10a.5.5 0 0 0-.5-.5 1.5 1.5 0 0 1 0-3A.5.5 0 0 0 16 6V4.5A1.5 1.5 0 0 0 14.5 3zM1 4.5a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 .5.5v1.05a2.5 2.5 0 0 0 0 4.9v1.05a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-1.05a2.5 2.5 0 0 0 0-4.9z"
            />
          </svg>
        </div>
        <p>이용권 현황</p>
      </div>
      <div id="reservation-manage" class="d-flex flex-row">
        <div class="mx-3">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-alarm"
            viewBox="0 0 16 16"
          >
            <path
              d="M8.5 5.5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9z"
            />
            <path
              d="M6.5 0a.5.5 0 0 0 0 1H7v1.07a7.001 7.001 0 0 0-3.273 12.474l-.602.602a.5.5 0 0 0 .707.708l.746-.746A6.97 6.97 0 0 0 8 16a6.97 6.97 0 0 0 3.422-.892l.746.746a.5.5 0 0 0 .707-.708l-.601-.602A7.001 7.001 0 0 0 9 2.07V1h.5a.5.5 0 0 0 0-1zm1.038 3.018a6 6 0 0 1 .924 0 6 6 0 1 1-.924 0M0 3.5c0 .753.333 1.429.86 1.887A8.04 8.04 0 0 1 4.387 1.86 2.5 2.5 0 0 0 0 3.5M13.5 1c-.753 0-1.429.333-1.887.86a8.04 8.04 0 0 1 3.527 3.527A2.5 2.5 0 0 0 13.5 1"
            />
          </svg>
        </div>
        <p>나의 예약 내역</p>
      </div>
      <div id="user-manage" class="d-flex flex-row">
        <div class="mx-3">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-person-x"
            viewBox="0 0 16 16"
          >
            <path
              d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4m.256 7a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"
            />
            <path
              d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m-.646-4.854.646.647.646-.647a.5.5 0 0 1 .708.708l-.647.646.647.646a.5.5 0 0 1-.708.708l-.646-.647-.646.647a.5.5 0 0 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 .708-.708"
            />
          </svg>
        </div>
        <button @click="withdrawal">회원 탈퇴</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import axios from "axios";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth";

const authStore = useAuthStore();
const userStore = useUserStore();
const loginUserId = authStore.getLoginUserId();
const accessToken = authStore.getAccessToken();
const router = useRouter();

onMounted(() => {
  userStore.getUserInfo(loginUserId);
});
const logout = () => {
  userStore.logout();
};
const withdrawal = function () {
  const userConfirm = confirm("정말 탈퇴하시겠습니까?");
  if (userConfirm) {
    axios
      .delete(`http://localhost:8080/api-user/user/${loginUserId}`, {
        headers: { "access-token": accessToken },
      })
      .then(() => {
        sessionStorage.removeItem("access-token");
        sessionStorage.removeItem("loginUserId");
        router.push({ name: "enter" });
      })
      .catch((e) => {});
  }
};
</script>

<style scoped>
#background {
  background-color: #ecf5fe;
  height: 90vh;
  width: 100vw;
  padding: 15px 15px;
  margin-top: 66px;
  margin-bottom: 90.5px;
}
#user-info {
  background-color: white;
  border-radius: 20px;
}
#user-name {
  font-weight: 600;
  font-size: 20px;
}
#user-info button,
#user-manage button {
  background-color: white;
  border: none;
}
a {
  text-decoration: none;
  color: black;
}
#user-img {
  /* 이미지 상자의 크기를 조정하여 이미지를 중앙에 배치합니다. */
  width: 60px;
  height: 60px;
}

#user-nav {
  background-color: white;
  border-radius: 20px;
  height: 75%;
}
</style>
