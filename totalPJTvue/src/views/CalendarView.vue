<template>
  <div class="container">
    <VDatePicker
      type="date"
      v-model="date"
      @change="onDateSelected"
      expanded
      borderless
      :attributes="attributes"
    />
    <div v-for="list in store.filterReservationList" :key="list.reservationId">
      <div id="card" class="card my-2">
        <div class="card-row">
          <div
            id="color-belt"
            :style="getStyle(list.exerciseCategoryNum)"
          ></div>
          <div class="card-body">
            <h5>{{ list.storeName }}</h5>
            <h6>{{ list.exerciseCategory }}</h6>
            <div class="d-flex justify-content-between">
              <div class="p-2">이용시간: {{ list.time }}</div>
              <div class="p-2">{{ list.ticketCategory }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useReservationStore } from "@/stores/reservation";
import { ref, watch, computed, onMounted } from "vue";

// Helper function to format date as java.sql.Timestamp
const formatToTimestamp = (date) => {
  const d = new Date(date);
  const year = d.getFullYear();
  const month = String(d.getMonth() + 1).padStart(2, "0");
  const day = String(d.getDate()).padStart(2, "0");
  return `${year}-${month}-${day}`;
};

const date = ref(new Date().toISOString().substr(0, 10)); // 기본 날짜 설정
const store = useReservationStore();
const attributes = ref([]);

const formattedDate = computed(() => formatToTimestamp(date.value));

watch(date, () => {
  console.log("Selected date:", formattedDate.value);
  store.getReservationByDate(formattedDate.value); // 날짜로 필터링된 예약 목록 요청
});

// Function to determine the style based on exerciseCategoryNum
const getStyle = (exerciseCategoryNum) => {
  const colors = {
    1: "lightpink",
    2: "lightblue",
    3: "lightgreen",
    4: "lightcoral",
    5: "lightsalmon",
    6: "lightseagreen",
    7: "lightgoldenrodyellow",
    8: "lightsteelblue",
    9: "lightcyan",
  };
  return {
    backgroundColor: colors[exerciseCategoryNum] || "gray",
  };
};

// Function to handle date selection
const onDateSelected = (event) => {
  const selectedDate = new Date(event.target.value).toISOString();
  console.log("Date selected:", formatToTimestamp(selectedDate));
  date.value = selectedDate;
};

onMounted(() => {
  store.getReservationByDate(formattedDate.value);
  console.log(attributes.value);
  console.log("출력됐나요?");
});
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100vh;
  margin-top: 90px;
  margin-bottom: 90.5px;
  background-color: #ecf5fe;
  height: 200vh;
  width: 100vw;
  padding: 15px 15px;
  margin-top: 73px;
  margin-bottom: 90.5px;
}

input[type="date"] {
  padding: 8px;
  margin-bottom: 16px;
  font-size: 16px;
}

#card {
  width: 20rem;
  height: 7rem;
}

.card-row {
  display: flex;
}

#color-belt {
  width: 8.33%; /* 1/12 of the container */
  height: 6.9rem;
}

.card-body {
  width: 91.67%; /* 11/12 of the container */
  padding-top: 0.5rem;
}
</style>
