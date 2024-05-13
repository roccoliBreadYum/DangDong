import { ref, computed, onMounted} from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useUserStore } from "./user";


const REST_API_RESERVATION ="http://localhost:8080/api-reservation";

export const useReservationStore = defineStore("reservation", () => {
  const userStore = useUserStore()

  const loginUserId = userStore.loginUserId;

  const reservationListCnt = ref(0)
  const reservationList = ref({})

  const date = Date.now()
  let today = ref({
    year: date.getFullYear,
    month: date.getMonth,
    day: date.getDay,
    hour: date.getHours,
    minute: date.getMinutes
  })

  const getReservation = () => {
    axios.get(`${REST_API_RESERVATION}/${date}/${loginUserId}`)
    .then((res) => {
      console.log(res)
    })
  }

  const test = () => {
    console.log(today)
  }





  return {
    reservationListCnt,
    reservationList,
    getReservation,
    test,
  };
});
