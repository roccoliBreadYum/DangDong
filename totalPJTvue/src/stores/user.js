import { ref, computed } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_BOARD_API = `http://localhost:8080/api-user/user`;

export const useUserStore = defineStore("user", () => {
  const loginUserId = ref("n");
  const userLogin = function (id, password) {
    axios
      .post(`${REST_BOARD_API}/login`, {
        id: id,
        password: password,
      })
      .then((res) => {
        sessionStorage.setItem("access-token", res.data["access-token"]);

        const token = res.data["access-token"].split(".");
        let id = JSON.parse(atob(token[1]))["id"];

        loginUserId.value = id;

        router.push({ name: "home" });
      })
      .catch((err) => {});
  };

  return { userLogin, loginUserId };
});
