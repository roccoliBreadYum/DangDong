import { ref, computed } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_USER_API = `http://localhost:8080/api-user/user`;

axios.defaults.withCredentials = true;
//.withCredentials의 기본값을 true값으로 변경->CORS 요청 허용, 쿠키값을 전달 할 수 있게 됨.
// 전역 설정 
export const useUserStore = defineStore("user", () => {
  const loginUserId = ref(null);
  const userLogin = async (id, password) => {
    try {
      const res = await axios.post(`${REST_USER_API}/login`, {
        id: id,
        password: password,
      });

      sessionStorage.setItem("access-token", res.data["access-token"]);
      const token = res.data["access-token"].split(".");
      let userId = JSON.parse(atob(token[1]))["id"];

      loginUserId.value = userId;

      router.push("/");
      return true; // 로그인 성공
    } catch (err) {
      console.error(err);
      return false; // 로그인 실패
    }
  };

  // const User = ref({})
  // const createUser = function(){

  // }

  return { userLogin, loginUserId };
});
