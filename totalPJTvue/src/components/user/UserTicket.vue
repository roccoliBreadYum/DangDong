<template>
  <div class="container" id="background">
    <div class="ticket-list">
      <button
        class="btn"
        v-for="ticket in store.userTicket"
        :key="ticket.ticketId"
        @click="moveDetail(ticket.storeId)"
      >
        <div class="ticket-item">
          <div class="ticket-info">
            <div class="ticket-name">{{ ticket.storeName }}</div>
            <div class="ticket-type">
              {{ ticket.category === 1 ? "기간권" : "다회권" }}
            </div>
          </div>
          <div class="ticket-detail">
            <div class="ticket-status">
              <span>{{ ticket.category === 1 ? "만료일:" : "잔여횟수:" }}</span>
              <span>{{
                ticket.category === 1
                  ? formatToTimestamp(ticket.expirationDate)
                  : ticket.remainingQuantity
              }}</span>
            </div>
          </div>
        </div>
      </button>
    </div>
  </div>
</template>

<script setup>
import { useticketStore } from "@/stores/ticket";
import { useRouter } from "vue-router";
const store = useticketStore();
const router = useRouter();

const formatToTimestamp = (date) => {
  const d = new Date(date);
  const year = d.getFullYear();
  const month = String(d.getUTCMonth() + 1).padStart(2, "0");
  const day = String(d.getUTCDate()).padStart(2, "0");
  return `${year}-${month}-${day}`;
};

const moveDetail = (id) => [
  router.push({ name: "storeDetail", params: { storeId: id } }),
];
</script>

<style scoped>
#background {
  background-color: #ecf5fe;
  width: 100vw;
  padding: 15px 15px;
  margin-top: 40px;
  margin-bottom: 90.5px;
  min-height: 1000px;
}

.ticket-list {
  display: flex;
  flex-wrap: wrap;
  margin-top: 4rem;
  margin-left: 2.3rem;
}

.ticket-item {
  width: 300px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  padding: 15px;
  border-radius: 10px; /* 테두리 둥글게 만들기 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  margin: 4 auto; /* 가운데 정렬 */
}

.ticket-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-bottom: 10px;
  border-bottom: 1px solid #eee;
}

.ticket-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-bottom: 10px; /* 상단 여백 추가 */
  border-bottom: 1px solid #eee; /* 구분선 추가 */
}

.ticket-name {
  font-size: 18px;
  font-weight: bold;
  color: #333; /* 글자색 설정 */
}

.ticket-type {
  color: #999; /* 글자색 설정 */
  text-decoration: none;
}

.ticket-detail {
  padding-top: 10px; /* 하단 여백 추가 */
}

.ticket-status {
  display: flex;
  align-items: center;
}

.ticket-status span:first-child {
  margin-right: 5px;
}
</style>
