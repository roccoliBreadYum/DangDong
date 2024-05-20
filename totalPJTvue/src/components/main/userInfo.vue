<template>
  <div class="container">
    <div class="border rounded-5 my-2">
      <div style="display: inline">
        <img
          class="rounded-circle my-3 mx-2"
          style="width: 27%"
          :src="`http://localhost:8080/resources/${userStore.loginUserInfo.img}`"
        />
      </div>
      <div class="px-3" style="display: inline-block">
        <h3>{{ userStore.loginUserInfo.nickName }}님 반갑습니다.</h3>
      </div>
      <div class="d-flex justify-content-around align-items-center">
        <div class="p-2 rounded-4 shadow">
          <p>
            <i class="bi bi-cash-coin" style="color: yellowgreen"></i><br />보유
            코인<br />
            {{ userStore.loginUserInfo.coin }} 개
          </p>
          <button type="button" class="btn btn-outline-info btn-sm">
            충전하기
          </button>
        </div>
        <div class="p-2 rounded-4 shadow">
          <p>
            <i class="bi bi-ticket-perforated"></i><br />보유 이용권<br />
            {{ ticketStore.userTicketCnt }} 개
          </p>
          <button type="button" class="btn btn-outline-info btn-sm">
            이용권 확인
          </button>
        </div>
        <div class="p-2 rounded-4 shadow">
          <p>
            <i class="bi bi-alarm"></i> <br />예약된 일정 <br />
            {{ reservationStore.reservationListCnt }} 개
          </p>
          <button type="button" class="btn btn-outline-info btn-sm">
            일정 확인
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { useticketStore } from "@/stores/ticket";
import { useReservationStore } from "@/stores/reservation";
import { ref, onMounted } from "vue";

const userStore = useUserStore();
const ticketStore = useticketStore();
const reservationStore = useReservationStore();
const loginUserId = sessionStorage.getItem("loginUserId");

onMounted(() => {
  userStore.getUserInfo(loginUserId);
  ticketStore.getUserTicketCount(loginUserId);
  reservationStore.getReservation(loginUserId);
});
</script>

<style scoped>
.p-2 {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.container {
  margin-top: 80px;
}
</style>
