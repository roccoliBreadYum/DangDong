import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useUserStore } from "./user";
import { useAuthStore } from "@/stores/auth";



const REST_API_TICKET ="http://localhost:8080/api-ticket/ticket";

export const useticketStore = defineStore("ticket", () => {
  const authStore = useAuthStore()
  const accessToken = authStore.getAccessToken();
  const userStore = useUserStore()

  const loginUserId = userStore.loginUserId;

  const userTicket = ref({})
  const userTicketCnt = ref(0)
  const getUserTicketCount = (id) => {
    console.log(`ticket: ${id}` )
    axios.get(`${REST_API_TICKET}/${id}`, {
      headers: {
        "access-token": accessToken,
      },
    })
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
