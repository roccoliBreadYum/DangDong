<template>
  <div id="background" class="container">
    <div class="d-flex align-items-center justify-content-center">
      <div id="login-form" class="d-flex flex-column justify-content-around">
        <div
          id="logo"
          class="d-flex justify-content-center align-items-center flex-grow-1"
        >
          <img src="" alt="이미지 자리지롱" />
        </div>
        <div
          id="input-form"
          class="d-flex justify-content-center align-items-center flex-column flex-grow-1"
        >
          <input type="text" placeholder="ID" v-model.trim="id" class="p-3" />
          <input
            type="password"
            placeholder="PWD"
            v-model.trim="pw"
            class="p-3"
          />
        </div>
        <div
          id="login-form-button"
          class="d-flex justify-content-center align-items-center flex-column flex-grow-1"
        >
          <button id="login-button" @click="login">로그인</button>
          <button id="join-button" @click="joinUser">회원가입 하기</button>
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

const login = async () => {
  const success = await store.userLogin(id.value, pw.value);
  if (success) {
    alert("로그인 성공");
    // router.push({ name: 'home' }); // 로그인 성공 시 홈으로 리디렉션
  } else {
    alert("로그인 실패");
  }
};
</script>

<style scoped>
#background {
  background-color: #ecf5fe;
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
}
</style>
