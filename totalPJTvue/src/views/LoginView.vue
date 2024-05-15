<template>
  <div>
    <h4>Login</h4>
    <input type="text" placeholder="ID입력" v-model.trim="id" />
    <input type="password" placeholder="PW입력" v-model.trim="pw" />
    <button @click="login">로그인</button>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import {useUserStore} from '@/stores/user'
import {useRouter} from 'vue-router'



const store = useUserStore();
const router = useRouter()
const id = ref("")
const pw = ref("")

const login = async () => {
    const success = await store.userLogin(id.value, pw.value);
    if (success) {
        alert('로그인 성공');
        // router.push({ name: 'home' }); // 로그인 성공 시 홈으로 리디렉션
    } else {
        alert('로그인 실패');
    }
};

</script>

<style scoped></style>
