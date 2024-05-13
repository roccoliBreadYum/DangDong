<template>
  <div class="container">
    <h3>{{ userStore.loginUserInfo.nickName }}님 반갑습니다.</h3>
    <div>
      <span>
        <i class="bi bi-cash-coin" style="color: yellowgreen"></i>
        {{ userStore.loginUserInfo.coin }} 개
      </span>
      <button>충전하기</button>
    </div>
    <div>
      <span>
        <i class="bi bi-ticket-perforated"></i>보유 이용권
        {{ ticketStore.userTicketCnt }} 개
      </span>
      <button>이용권 확인</button>
   </div>
   <div>
      <span>
        <i class="bi bi-alarm"></i> 예약된 일정
        {{ reservationStore.reservationListCnt }} 개
      </span>
      <button>일정 확인</button>
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

onMounted(() => {
  userStore.getUserInfo(), 
  ticketStore.getUserTicketCount();
  reservationStore.getReservation()
});
</script>

<style scoped></style>
