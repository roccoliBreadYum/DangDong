import { ref, computed, onMounted} from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useUserStore } from "./user";


const REST_API_RESERVATION ="http://localhost:8080/api-reservation";

export const useReservationStore = defineStore("reservation", () => {
  const userStore = useUserStore()

  const loginUserId = userStore.loginUserId;

  const reservationListCnt = ref(0)
  const reservationList = ref([])

  const getReservation = () => {
    axios.get(`${REST_API_RESERVATION}/${loginUserId}`)
    .then((res) => {
      reservationList.value = res.data
      reservationListCnt.value = res.data.length
    })
  }





  return {
    reservationListCnt,
    reservationList,
    getReservation,

  };
});
