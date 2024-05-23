<template>
  <div class="container mt-5">
    <div id="lessonInfo" class="text-center mb-4 p-4 border rounded shadow-sm">
      <h2 id="storeName" class="mb-3">{{ store.lessonInfo.storeName }}</h2>
      <p id="reservationDate" class="mb-2 text-muted">예약일자: {{ formatTime(store.lessonInfo.date) }}</p>
      <p id="teacherName" class="mb-2 text-muted">강사명: {{ store.lessonInfo.teacherName }}</p>
    </div>
    <div id="payMethod" class="d-grid gap-3">
      <button @click="() => {togglePaymentMethod('ticket'); selectMethod = 1;}" class="btn payment-btn mb-3 d-flex align-items-center justify-content-center">
        <i class="bi bi-ticket-perforated me-2"></i>이용권
      </button>
      <button @click="() => {togglePaymentMethod('coin'); selectMethod = 2;}" class="btn payment-btn mb-3 d-flex align-items-center justify-content-center">
        <i class="bi bi-coin me-2"></i>코인
      </button>
      <button @click="() => {togglePaymentMethod('cash'); selectMethod = 3;}" class="btn payment-btn mb-3 d-flex align-items-center justify-content-center">
        <i class="bi bi-cash-coin me-2"></i>일반 결제
      </button>
      <div class="mt-3 text-center">
        <h5>현재 선택된 결제수단: 
          <span v-if="selectMethod === 1" class="selected-method">이용권</span>
          <span v-if="selectMethod === 2" class="selected-method">코인</span>
          <span v-if="selectMethod === 3" class="selected-method">일반결제</span>
          <span v-if="selectMethod === ''" class="selected-method-none">없음</span>
        </h5>
      </div>
      <div v-if="showTicketList" class="ticket-list mt-3">
        <select class="form-select" aria-label="Default select example" v-model="selectTicket">
          <option value="" selected disabled>Open this select menu</option>
          <option v-for="ticket in store.usableTicket" :key="ticket.ticketId" :value="ticket.ticketId">
            {{ ticket.ticketName }} - 
            {{ ticket.category === 0 ? '다회권' : '정기권' }} - 
            {{ ticket.category === 0 ? `${ticket.remainingQuantity}회 남음` : `만료일: ${formatDate(ticket.expirationDate)}` }}
          </option>
        </select>
      </div>
      <button class="btn mt-3 confirm-btn" @click="makeReservation()"> 확인 </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { uselessonStore } from '@/stores/lesson';
import { useAuthStore } from '@/stores/auth';
import { useRoute, useRouter } from 'vue-router';
import { useReservationStore } from '@/stores/reservation';
import { useStoreStore } from '@/stores/store';

const rStore = useReservationStore()
const store = uselessonStore()
const sStore = useStoreStore();
const route = useRoute()
const router = useRouter()
const authStore = useAuthStore();
const userId = authStore.getLoginUserId();

const showTicketList = ref(false);
const activePaymentMethod = ref('');
const selectTicket = ref('');
const selectCoin = sStore.storeDetail.coin;
const selectMethod = ref('')

const makeReservation = () => {
  if(selectMethod.value === 1){
    const reservationInfo = {
      payment: 1,
      userId: userId,
      storeId: store.lessonInfo.storeId,
      lessonId:store.lessonInfo.lessonId,
      ticketId: selectTicket.value,
      date: store.lessonInfo.date
    }
    console.log(reservationInfo)
    rStore.makeReservation(reservationInfo)
  } else if (selectMethod.value === 2) {
    const reservationInfo = {
      payment:2,
      userId: userId,
      storeId: store.lessonInfo.storeId,
      lessonId:store.lessonInfo.lessonId,
      coin: selectCoin,
      date: store.lessonInfo.date
    }
    console.log(reservationInfo)
    rStore.makeReservation(reservationInfo)
  } 
}

const togglePaymentMethod = (method) => {
  if (activePaymentMethod.value === method) {
    activePaymentMethod.value = '';
    showTicketList.value = false;
    selectMethod.value = ''; // 선택된 결제수단 초기화
  } else {
    activePaymentMethod.value = method;
    showTicketList.value = method === 'ticket';
  }
};

const formatTime = (datetime) => {
    const date = new Date(datetime);
    const year = date.getFullYear();
    const month = String(date.getUTCMonth() + 1).padStart(2, '0');
    const day = String(date.getUTCDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getUTCMinutes()).padStart(2, '0');
    return `${year}-${month}-${day} ${hours}:${minutes}`;
};

const formatDate = (datetime) => {
    const date = new Date(datetime);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
};

onMounted(() => {
  store.getLesson(route.params.lessonId)
  store.getUsableTicket(store.lessonInfo.storeId, userId)
})
</script>

<style scoped>


#lessonInfo {
  margin-top: 5rem;
  background-color: white;
  border: 1px solid #dee2e6;
  border-radius: 0.5rem;
}

.btn {
  border: 1px solid #ECF5FE(24, 127, 134);
}

#storeName {
  font-size: 1.75rem;
  font-weight: bold;
}

#reservationDate, #teacherName {
  font-size: 1.1rem;
}

#payMethod button {
  width: 100%;
  font-size: 1.2rem;
  padding: 0.75rem 1rem;
  border-radius: 0.5rem;
  transition: background-color 0.3s, transform 0.3s;
}

#payMethod button:hover {
  transform: translateY(-3px);
  background-color: #e2f0f3;
}

#payMethod button:active {
  transform: translateY(1px);
}

#payMethod button i {
  font-size: 1.5rem;
}

.ticket-list {
  border: 1px solid #dee2e6;
  border-radius: 0.5rem;
  background-color: #ffffff;
  padding: 1rem;
}

.ticket-list select {
  width: 100%;
  padding: 0.5rem;
  font-size: 1rem;
  border-radius: 0.5rem;
  border: 1px solid #dee2e6;
  transition: border-color 0.3s;
}

.ticket-list select:focus {
  border-color: #007bff;
}

.selected-method {
  font-weight: bold;
  color: #007bff;
}

.selected-method-none {
  font-weight: bold;
  color: #dc3545;
}

.confirm-btn {
  background-color: #d7fefe;
  border-color: #d7fefe;
  transition: background-color 0.3s, border-color 0.3s;
}

.confirm-btn:hover {
  background-color: #0056b3;
  border-color: #0056b3;
}

.payment-btn {
  background-color: #ECF5FE;
  color: black;
}

.payment-btn:hover {
  background-color: #e2f0f3;
  color: #0056b3;
}

.payment-btn:active {
  background-color: #d4e8eb;
}
</style>
