import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useUserStore } from "./user";
import { useAuthStore } from "@/stores/auth";
import { useStoreStore } from "./store";

const REST_API_TICKET = "http://localhost:8080/api-ticket/ticket";
const REST_API_STORE = "http://localhost:8080/api-store";

export const useticketStore = defineStore("ticket", () => {
  const authStore = useAuthStore();
  const accessToken = authStore.getAccessToken();
  const userStore = useUserStore();
  const storeStore = useStoreStore();
  const loginUserId = userStore.loginUserId;

  const userTicket = ref([]);
  const userTicketCnt = ref(0);
  const getUserTicketCount = (id) => {
    // console.log(`ticket: ${id}`);
    axios
      .get(`${REST_API_TICKET}/${id}`, {
        headers: {
          "access-token": accessToken,
        },
      })
      .then((res) => {
        console.log(res)
        userTicket.value = res.data;
        userTicketCnt.value = res.data.length;
      });
  };

  const getStoreNamebyStoreId = (storeId) => {
    axios.get(`${REST_API_STORE}/${storeId}/getName`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then((res) => {
      console.log(res)
      return res.data
    })
  }

  // const createTicket = async function(sellTicketid){
  //   await storeStore.getTicket(sellTicketid);
  //   axios
  //   .post(`${REST_API_TICKET}`, storeStore.forBuyTicketInfo.value, {
  //     headers: {
  //       "access-token": accessToken,
  //       "Content-Type": "application/json",
  //     },
  //   })
  //   .then((res)=>{
  //     console.log(`createUserTicket성공${res.data}`)
  //   })
  // }

  const createTicket = async function(sellTicketid) {
    try {
      const ticketInfo = await storeStore.getTicket(sellTicketid);
  
      // forBuyTicketInfo가 올바르게 설정되었는지 확인
      if (!ticketInfo) {
        throw new Error("Ticket info not available");
      }
  
      const response = await axios.post(`${REST_API_TICKET}`,ticketInfo, {
        headers: {
          "access-token": accessToken,
          "Content-Type": "application/json",
        },
      });
  
      console.log(`createUserTicket 성공: ${response.data}`);
    } catch (error) {
      console.error('Error creating ticket:', error);
    }
  };

  return {
    userTicketCnt,
    userTicket,
    getUserTicketCount,
    createTicket,
    getStoreNamebyStoreId,
  };
}, {persist:true});
