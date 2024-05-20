import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_API_RESERVATION = "http://localhost:8080/api-reservation";

export const useReservationStore = defineStore("reservation", () => {
  const accessToken = sessionStorage.getItem("access-token");
  const loginUserId = sessionStorage.getItem('loginUserId');

  const reservationListCnt = ref(0);
  const reservationList = ref([]);
  const reservationAllList = ref([]);
  const filterReservationList = ref([]);

  const getReservation = () => {
    axios.get(`${REST_API_RESERVATION}/${loginUserId}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then((res) => {
      reservationList.value = res.data;
      reservationListCnt.value = res.data.length;
    })
    .catch((error) => {
      console.error("Error fetching reservation:", error);
    });
  };

  const getReservationByDate = (date) => {
    axios.get(`${REST_API_RESERVATION}/${date}/${loginUserId}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then((res) => {
      console.log(res)
      filterReservationList.value = res.data;
    })
    .catch((error) => {
      console.error("Error fetching reservations by date:", error);
    });
  };

  const getAllReservations = () => {
    axios.get(`${REST_API_RESERVATION}/now/${loginUserId}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then((res) => {
      reservationAllList.value = res.data;
    })
    .catch((error) => {
      console.error("Error fetching all reservations:", error);
    });
  };

  return {
    filterReservationList,
    reservationAllList,
    reservationListCnt,
    reservationList,
    getReservation,
    getReservationByDate,
    getAllReservations,
  };
});
