import { ref, computed, onMounted} from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useUserStore } from "./user";


const REST_API_RESERVATION ="http://localhost:8080/api-reservation";

export const useReservationStore = defineStore("reservation", () => {
  const accessToken = sessionStorage.getItem("access-token");
  const userStore = useUserStore()

  const loginUserId = userStore.loginUserId;

  const reservationListCnt = ref(0)
  const reservationList = ref([])

  const getReservation = (id) => {
    axios.get(`${REST_API_RESERVATION}/${id}`, {
      headers: {
        "access-token": accessToken,
      },
    })
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
