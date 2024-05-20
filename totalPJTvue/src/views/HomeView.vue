<template>
  <div class="container">
    <template v-if="isAuthenticated">

      <UserInfo />
      <ExcerciseCategory />
    </template>
    

    
  </div>
</template>

<script setup>
import ExcerciseCategory from '@/components/main/excerciseCategory.vue';
import UserInfo from '@/components/main/userInfo.vue';
import {computed, onMounted} from 'vue'
import { useStoreStore } from '@/stores/store';

const store = useStoreStore()

// 세션에 저장된 access-token을 확인하여 사용자가 인증되었는지 판단하는 계산된 속성
const isAuthenticated = computed(() => {
  return sessionStorage.getItem("access-token");
});


</script>

<style scoped>

</style>
