<template>
  <div id="background" class="container">
    <div class="d-flex align-items-center justify-content-center">
      <div id="login-form" class="d-flex flex-column justify-content-around">
        <div id="logo" class="d-flex justify-content-center align-items-center">
          <div
            class="d-flex justify-content-center align-items-center"
            id="img-box"
          >
            <img id="logo-img" src="@/assets/logo3.png" alt="logo img" />
          </div>
        </div>
        <div>
          <div
            id="input-form"
            class="d-flex justify-content-center align-items-center flex-column flex-grow-1"
          >
            <input
              type="text"
              placeholder="ID"
              v-model.trim="id"
              class="p-3"
              @keyup.enter="login"
            />
            <input
              type="password"
              placeholder="PWD"
              v-model.trim="pw"
              class="p-3"
              @keyup.enter="login"
            />
          </div>
          <div class="form-check form-switch" id="keep-login">
            <input
              class="form-check-input"
              type="checkbox"
              role="switch"
              id="flexSwitchCheckDefault"
              v-model="isKeepLogin"
            />
            <label class="form-check-label" for="flexSwitchCheckDefault"
              >로그인 유지하기</label
            >
          </div>
        </div>
        <div
          id="login-form-button"
          class="d-flex justify-content-center align-items-center flex-column flex-grow-1"
        >
          <button id="login-button" @click="login">로그인</button>
          <RouterLink :to="{ name: 'userCreate' }" id="join-button"
            >회원가입 하기</RouterLink
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";

const store = useUserStore();
const router = useRouter();
const id = ref("");
const pw = ref("");

const isKeepLogin = ref(false);

const login = () => {
  store.userLogin(id.value, pw.value, isKeepLogin.value);
};
</script>

<style scoped>
#background {
  background-color: #ecf5fe;
}

#img-box {
  width: 200px;
  height: 200px;
}
#logo {
  margin-top: 60px;
  margin-bottom: 10px;
}
#logo-img {
  width: 100%;
  height: 100%;
}
.container > div {
  height: 100vh; /* 화면의 100%만큼의 높이를 갖도록 설정합니다. */
  display: flex; /* 내부 요소들을 정렬하기 위해 flexbox를 사용합니다. */
  justify-content: center; /* 가로 방향으로 가운데 정렬합니다. */
  align-items: center; /* 세로 방향으로 가운데 정렬합니다. */
}

#login-form {
  height: 70vh;
  background-color: white;
  width: 90%;
  border-radius: 40px;
}

#login-form input {
  border-radius: 10px;
  margin: 10px;
  background-color: #d7fefe;
  font-weight: bold;
  border: none;
}

#login-button {
  border-radius: 10px;
  margin: 10px;
  background-color: #a2d8df;
  font-weight: bold;
  border: none;
}

#join-button {
  color: #000;
  background-color: white;
  font-weight: bold;
  border: none;
  text-decoration: none;
}

#keep-login {
  display: flex;
  align-items: center; /* 세로 가운데 정렬 */
  padding-left: 3.2rem;
}
</style>
