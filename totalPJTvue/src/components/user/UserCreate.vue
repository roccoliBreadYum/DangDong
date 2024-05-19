<template>
  <div id="background" class="container">
    <div class="d-flex align-items-center justify-content-center">
      <form @submit.prevent="createUser">
        <div
          id="create-form"
          class="d-flex flex-column justify-content-around p-3"
        >
          <div>회원 정보를 입력하세요</div>
          <div
            id="img-area"
            class="d-flex flex-row justify-content-center align-item-center"
          >
            <div id="img-box" class="d-flex align-item-center">
              <img src="@/assets/tmp_user.png" alt="user img" />
            </div>
            <input type="file" />
          </div>
          <div class="d-flex flex-column justify-content-around p-3">
            <input type="text" v-model="user.name" required placeholder="이름" />
            <input
              type="email"
             v-model="user.email"
              required
              placeholder="이메일"
            />
            <input
              type="date"
             v-model="user.birth"
              required
              placeholder="생년월일"
            />
            <input type="text" placeholder="주소" v-model="user.address"/>
            <select v-model="user.gender" required>
              <option value="" disabled selected hidden>--성별--</option>
              <option value="0">남</option>
              <option value="1">여</option>
            </select>
            <input type="text" v-model="user.id" required placeholder="아이디" />
            <input
              type="password"
              v-model="user.password"
              required
              placeholder="비밀번호"
            />
            <input type="text" v-model="user.nickName" required placeholder="닉네임" />
            <textarea placeholder="소개글" v-model="user.comment" ></textarea>
          </div>
          <div class="d-flex align-items-center justify-content-center">
            <button type="submit" id="join-btn">가입</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";

const store = useUserStore();
const router = useRouter();
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

const createUser = function(){
  store.createUser(user.value)
}
</script>

<style scoped>
#background {
  background-color: #ecf5fe;
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

.container > div {
  height: 100vh; /* 화면의 100%만큼의 높이를 갖도록 설정합니다. */
  display: flex; /* 내부 요소들을 정렬하기 위해 flexbox를 사용합니다. */
  justify-content: center; /* 가로 방향으로 가운데 정렬합니다. */
  align-items: center; /* 세로 방향으로 가운데 정렬합니다. */
}

#create-form {
  height: 90vh;
  background-color: white;
  width: 60vh;
  border-radius: 40px;
}

#create-form input, select {
  border-radius: 10px;
  margin: 10px;
  background-color: #d7fefe;
  border: none;
}

#create-form textarea {
  border-radius: 10px;
  margin: 10px;
  background-color: #d7fefe;
  border: none;
  height: 70px;
}

#join-btn {
  border-radius: 10px;
  margin: 10px;
  background-color: #a2d8df;
  border: none;
  width: 20%;
}
</style>
