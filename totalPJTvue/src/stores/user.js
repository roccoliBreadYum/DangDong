import { ref, computed } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_API_USER = `http://localhost:8080/api-user/user`;
const REST_API_FAVORITE = "http://localhost:8080/api-favorite/favorite";


axios.defaults.withCredentials = true;
//.withCredentials의 기본값을 true값으로 변경->CORS 요청 허용, 쿠키값을 전달 할 수 있게 됨.
// 전역 설정 
export const useUserStore = defineStore("user", () => {
  const accessToken = sessionStorage.getItem("access-token");
  const loginUserId = ref(null);


  const userLogin = (id, password) => {
      axios.post(`${REST_API_USER}/login`, {
        id: id,
        password: password,
      }).then((res)=>{
        sessionStorage.setItem("access-token", res.data["access-token"]);
        const token = res.data["access-token"].split(".");
        let userId = JSON.parse(atob(token[1]))["id"];
        sessionStorage.setItem("loginUserId", userId);
        loginUserId.value = userId;
        router.push("/");
        
      }).
      catch (err=>{
        console.error(err);
        alert("아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.");
      })
  };
  

  const loginUserInfo = ref({});

  const getUserInfo = (id) => {
    console.log(id)
    axios.get(`${REST_API_USER}/${id}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then((res) => {
      console.log(res)
      loginUserInfo.value = res.data;
    })
    .catch((err)=>{
      console.log(err)
    })
  };


  const updateFavorite = (nowStatus, userId, storeId) => {
    if(nowStatus === 0){
      return axios.post(`${REST_API_FAVORITE}`,{
        userId : userId,
        storeId : storeId,
      },{
        headers: {
          "access-token": accessToken,
        },
      })
    } else {
      return axios.delete(`${REST_API_FAVORITE}/${userId}/${storeId}`,{
        headers: {
          "access-token": accessToken,
        },
      })
  }}

  return { 
    userLogin, 
    loginUserId ,
    loginUserInfo,
    getUserInfo,
    updateFavorite,};
});
