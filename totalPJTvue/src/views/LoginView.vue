<template>
  <div>
    <label> ID </label>
    <input type="text" v-model="loginUser.loginId" /> <br />
    <label> PW </label>
    <input type="password" v-model="loginUser.loginPw" /> <br />
    <button @click="login">Login</button>
    <button>Regist</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import axios from "axios";
const REST_API = "http://localhost:8080/api-user/user";
const route = useRoute();
const router = useRouter();

const loginUser = ref({
  loginId: "",
  loginPw: "",
});

const login = (loginUser) => {
  //console.log(loginUser.value.loginId)
  //console.log(loginUser.value.loginPw)
  axios
    .get(`${REST_API}/login`, {
      params: loginUser,
    })
    .then(() => {
      router.push({ name: "main" });
    })
    .catch(() => {
      router.push({ name: "login" });
    });
};
</script>

<style scoped></style>
