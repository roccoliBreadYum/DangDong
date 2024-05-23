<template>
  <div class="container">
    <div id="background" style="margin-top: 43px">
      <br />
      <div
        class="border rounded-5"
        id="p-2"
        style="background-color: white; margin-top: 20px"
      >
        <div style="display: inline">
          <template v-if="userStore.loginUserInfo.img !== null">
            <img
              :src="`http://localhost:8080/resources/${userStore.loginUserInfo.img}`"
              class="my-3 mx-2"
              style="width: 100px; height: 100px; border-radius: 500px"
              alt="user img"
            />
          </template>
          <template v-else>
            <img
              src="@/assets/tmp_user.png"
              alt="user img"
              class="my-3 mx-2"
              style="width: 100px; height: 100px; border-radius: 500px"
            />
          </template>
        </div>
        <div style="display: inline-block">
          <h4 style="font-weight: 600">
            ⭐<u>{{ userStore.loginUserInfo.nickName }}</u
            >⭐님 반갑습니다.
          </h4>
        </div>
        <div id="comment">
          <div>{{ userStore.loginUserInfo.comment }}</div>
        </div>
        <div
          id="detail-info-box"
          class="d-flex flex-column justify-content-center p-2"
          style="width: 100%; height: 120px; border-radius: 20px"
        >
          <div
            id="coin-box"
            class="d-flex justify-content-around flex-row align-items-center m-1"
          >
            <i
              class="bi bi-coin"
              id="coin-icon"
              style="color: #ffbf01ba; font-weight: 700"
            ></i>
            <div id="coin-text" style="font-size: 13px">
              보유 코인: {{ userStore.loginUserInfo.coin }} 개
            </div>
            <button type="button">충전하기</button>
          </div>
          <div
            id="ticket-box"
            class="d-flex justify-content-around flex-row align-items-center m-1"
          >
            <i class="bi bi-ticket-perforated" id="ticket-icon"></i>
            <div id="ticket-text" style="font-size: 13px">
              보유 이용권: {{ ticketStore.userTicketCnt }} 개
            </div>
            <RouterLink :to="{ name: 'userTicket' }" type="button">
              <div>이용권 확인</div>
            </RouterLink>
          </div>
          <div
            id="reservation-box"
            class="d-flex justify-content-around flex-row items-align-center m-1"
          >
            <i class="bi bi-alarm" id="reservation-icon"></i>
            <div id="reservation-text" style="font-size: 13px">
              예약된 일정: {{ reservationStore.reservationListCnt }} 개
            </div>
            <RouterLink :to="{ name: 'calendar' }" type="button">
              <div>일정 확인</div>
            </RouterLink>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { useticketStore } from "@/stores/ticket";
import { useReservationStore } from "@/stores/reservation";
import { useAuthStore } from "@/stores/auth";
import { ref, onMounted } from "vue";

const userStore = useUserStore();
const authStore = useAuthStore();
const ticketStore = useticketStore();
const reservationStore = useReservationStore();
const loginUserId = authStore.getLoginUserId();

onMounted(() => {
  userStore.getUserInfo(loginUserId);
  ticketStore.getUserTicketCount(loginUserId);
  reservationStore.getReservation(loginUserId);
});
</script>

<style scoped>
#p-2 {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
#p-3 {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 0px 0px;
}
p {
  margin-bottom: 0px;
}
a,
button {
  border-radius: 10px;
  background-color: #a2d8df;
  border: none;
  width: 80px;
  height: 30px;
  font-size: 13px;
  text-align: center;
}
a {
  text-decoration: none;
  margin-top: 0px;
  color: black;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
