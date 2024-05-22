<template>
  <div class="container">
    <div id="background" style="margin-top: 43px">
      <br />
      <div
        class="border rounded-5 p-2"
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
        <div class="px-3" style="display: inline-block">
          <h4 style="font-weight: 600">
            ⭐{{ userStore.loginUserInfo.nickName }}⭐님 반갑습니다.
          </h4>
        </div>
        <div class="d-flex justify-content-around align-items-center">
          <div class="p-2 rounded-4 shadow">
            <p>
              <i
                class="bi bi-coin"
                style="color: #ffbf01ba; font-weight: 700"
              ></i
              ><br />보유 코인<br />
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
.p-2 {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
