import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_API_USER = "http://localhost:8080/api-user/user";

export const useUserStore = defineStore("user", () => {
  const loginUserId = ref("user1");
  const loginUserInfo = ref({});

  const getUserInfo = () => {
    axios.get(`${REST_API_USER}/${loginUserId.value}`)
    .then((res) => {
      console.log(res)
      loginUserInfo.value = res.data;
    })
  };


  const updateFavorite = (nowStatus, userId, storeId) => {
    if(nowStatus === 0){
      return axios.post(`${REST_API_USER}/${storeId}/${userId}`)
    } else {
      return axios.delete(`${REST_API_USER}/${storeId}/${userId}`)
    }
}

  return {
    loginUserId,
    loginUserInfo,
    getUserInfo,
    updateFavorite,
  };
});