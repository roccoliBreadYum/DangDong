import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useUserStore } from "./user";


const REST_API_TICKET ="http://localhost:8080/api-ticket/ticket";

export const useticketStore = defineStore("ticket", () => {
  const userStore = useUserStore()

  const loginUserId = userStore.loginUserId;

  const userTicket = ref({})
  const userTicketCnt = ref(0)
  const getUserTicketCount = () => {
    axios.get(`${REST_API_TICKET}/${loginUserId}`)
    .then((res) => {
      //console.log(res)
      userTicket.value = res.data
      userTicketCnt.value = res.data.length
    })
  }

  return {
    userTicketCnt,
    userTicket,
    getUserTicketCount,
  };
});
