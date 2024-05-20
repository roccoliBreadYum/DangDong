import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";


export const useAuthStore = defineStore("auth", () => {

    const getAccessToken = () => {
        // sessionStorage에서 accessToken 확인
        let token = sessionStorage.getItem("access-token");
        // sessionStorage에 없으면 localStorage에서 확인
        if (!token) {
          token = localStorage.getItem("access-token");
        }
        return token;
      };

    const getLoginUserId = () => {
    // sessionStorage에서 accessToken 확인
    let token = sessionStorage.getItem("loginUserId");
    // sessionStorage에 없으면 localStorage에서 확인
    if (!token) {
        token = localStorage.getItem("loginUserId");
    }
    return token;
    };

    const removeTokens = () => {
        // localStorage에서 "access-token"과 "loginUserId"를 삭제
        if (localStorage.getItem("access-token") && localStorage.getItem("loginUserId")) {
            localStorage.removeItem("access-token");
            sessionStorage.removeItem("access-token");
            localStorage.removeItem("loginUserId");
        }
        
        // sessionStorage에서 "access-token"과 "loginUserId"를 삭제
        if (sessionStorage.getItem("access-token") && sessionStorage.getItem("loginUserId")) {
            sessionStorage.removeItem("access-token");
            sessionStorage.removeItem("loginUserId");
  }
    }


    return{
        getAccessToken,
        getLoginUserId,
        removeTokens,
    }
});