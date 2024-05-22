import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useAuthStore } from "@/stores/auth";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";

const REST_API_RESERVATION = "http://localhost:8080/api-reservation";

export const useReservationStore = defineStore("reservation", () => {
  const userStore = useUserStore();
  const store = useAuthStore()
  const accessToken = store.getAccessToken();
  const loginUserId = store.getLoginUserId();

  const reservationListCnt = ref(0);
  const reservationList = ref([]);
  const reservationAllList = ref([]);
  const filterReservationList = ref([]);
  const router = useRouter();

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

  const makeReservation = (reservationInfo) => {
    if(reservationInfo.payment === 2 && userStore.loginUserInfo.coin < reservationInfo.coin){
      const userConfirm = confirm("보유 코인이 부족합니다. 충전하시겠습니까?");
      if(userConfirm){
        router.push({name:"payCheckout", params:{ticketId:1}});
      } else{
        router.push({name:"home"});
      }
      return;
    }
    axios.post(`${REST_API_RESERVATION}`, reservationInfo, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(() => {
      alert("예약 완료!")
      router.push({name:"calendar"});
    })
    .catch(() => {
      const userConfirm = confirm("예상치 못한 오류가 발생했습니다. 관리자에게 문의하세요");
      if(userConfirm){
        router.push({name:"home"});
      } else{
        router.push({name:"home"});
      }
    })
  }

  return {
    filterReservationList,
    reservationAllList,
    reservationListCnt,
    reservationList,
    getReservation,
    getReservationByDate,
    getAllReservations,
    makeReservation,
  };
});
