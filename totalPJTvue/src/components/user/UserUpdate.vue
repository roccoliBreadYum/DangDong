<template>
  <div id="background" class="container">
    <div class="d-flex align-items-center justify-content-center">
      <form @submit.prevent="updateUser">
        <div
          id="update-form"
          class="d-flex flex-column justify-content-evenly p-3"
        >
          <div>회원 정보를 수정하세요</div>
          <div
            id="img-area"
            class="d-flex flex-row justify-content-center align-item-center"
          >
            <div id="img-box" class="d-flex">
              <img src="@/assets/tmp_user.png" alt="user img" />
            </div>
            <input type="file" />
          </div>
          <div class="d-flex flex-column justify-content-around p-3">
            <div
              class="d-flex flex-row justify-content-between align-items-center"
            >
              <label for="name">이름 : </label>
              <input
                type="text"
                v-model="user.name"
                readonly
                required
                class="readonly"
                id="name"
              />
            </div>
            <div
              class="d-flex flex-row justify-content-between align-items-center"
            >
              <label for="email">email : </label>
              <input
                type="email"
                v-model="user.email"
                required
                placeholder="이메일"
                id="email"
              />
            </div>
            <div
              class="d-flex flex-row justify-content-between align-items-center"
            >
              <label for="birth">생년월일 : </label>
              <input
                type="date"
                v-model="user.birth"
                readonly
                class="readonly"
                required
                placeholder="생년월일"
                id="birth"
              />
            </div>
            <div
              class="d-flex flex-row justify-content-between align-items-center"
            >
              <label for="address">주소 : </label>
              <input
                type="text"
                placeholder="주소"
                v-model="user.address"
                id="address"
              />
            </div>
            <div
              class="d-flex flex-row justify-content-between align-items-center"
            >
              <label for="gender">성별 : </label>
              <select
                v-model="user.gender"
                disabled
                required
                class="readonly"
                id="gender"
              >
                <option value="" disabled selected hidden>--성별--</option>
                <option value="0">남</option>
                <option value="1">여</option>
              </select>
            </div>
            <div
              class="d-flex flex-row justify-content-between align-items-center"
            >
              <label for="id">아이디 : </label>
              <input
                type="text"
                v-model="user.id"
                readonly
                required
                class="readonly"
                placeholder="아이디"
                id="id"
              />
            </div>
            <div
              class="d-flex flex-row justify-content-between align-items-center"
            >
              <label for="password">비밀번호 : </label>
              <input
                type="password"
                required
                v-model="user.password"
                placeholder="비밀번호"
                autocomplete="current-password"
                id="password"
              />
            </div>
            <div
              class="d-flex flex-row justify-content-between align-items-center"
            >
              <label for="nickName">닉네임 : </label>
              <input
                type="text"
                v-model="user.nickName"
                required
                placeholder="닉네임"
                id="nickName"
              />
            </div>
            <div
              class="d-flex flex-row justify-content-between align-items-center"
            >
              <label for="comment">소개글 : </label>
              <textarea
                placeholder="소개글"
                v-model="user.comment"
                id="comment"
              ></textarea>
            </div>
          </div>
          <div
            class="d-flex flex-row align-items-center justify-content-center"
          >
            <button type="submit" id="update-btn">수정</button>
            <button @click.prevent="backButton" id="back-btn">뒤로</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";

const userStore = useUserStore();
const router = useRouter();
const loginUserId = sessionStorage.getItem("loginUserId");

const user = ref({
  name: "",
  email: "",
  birth: "",
  address: "",
  gender: "",
  id: "",
  password: "",
  nickName: "",
  comment: "",
});

onMounted(() => {
  userStore.getUserInfo(loginUserId).then(() => {
    user.value = userStore.loginUserInfo;
    const originDate = new Date(user.value.birth);
    user.value.birth = originDate.toISOString().split("T")[0];
  });
});

const updateUser = function () {
  userStore.updateUser(user.value);
};
const backButton = function () {
  router.go(-1);
  // router.back();
};
</script>

<style scoped>
#background {
  background-color: #ecf5fe;
  height: 90vh;
  width: 100vw;
  padding: 15px 15px;
}
#update-form {
  height: 79vh;
  background-color: white;
  width: 90vw;
  border-radius: 40px;
}
#img-area input {
  padding: 3px;
  width: 180px;
}
#img-box {
  /* 이미지 상자의 크기를 조정하여 이미지를 중앙에 배치합니다. */
  width: 60px;
  height: 60px;
  margin-right: 50px;
}
#update-form input,
select {
  border-radius: 10px;
  margin: 10px;
  background-color: #d7fefe;
  border: none;
  width: 197px;
}
#update-form input.readonly,
#update-form select.readonly,
#update-form textarea.readonly {
  background-color: #c4c5c5 !important;
}
#update-form textarea {
  border-radius: 10px;
  margin: 10px;
  background-color: #d7fefe;
  border: none;
  height: 70px;
  width: 197px;
}

#update-btn,
#back-btn {
  border-radius: 10px;
  margin: 10px;
  background-color: #a2d8df;
  border: none;
  width: 20%;
}
input,
select,
textarea {
  padding: 2px 10px; /* 내부 여백을 10px로 설정 */
}
</style>
