<template>
  <div>
    <template v-if="!isSearchView && !isEnterView">
      <TheHeaderSearchNav />
    </template>
    <!-- <template v-if="isAuthenticated||isEnterView"> -->
    <RouterView />
    <!-- </template> -->
    <template v-if="!isEnterView">
      <TheFooterNav />
    </template>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import TheFooterNav from "./components/common/TheFooterNav.vue";
import TheHeaderSearchNav from "./components/common/TheHeaderSearchNav.vue";
import { watch, ref, computed } from "vue";
import { useUserStore } from "./stores/user";

const router = useRouter();
const userStore = useUserStore(); //로그인 여부 판단

// 현재 경로를 확인하여 searchView 여부를 판단하는 계산된 속성
const isSearchView = computed(() => {
  return router.currentRoute.value.name === "search";
});
// 현재 경로를 확인하여 loginView 여부를 판단하는 계산된 속성
const isEnterView = computed(() => {
  return (
    router.currentRoute.value.name === "login" ||
    router.currentRoute.value.name === "userCreate"
  );
});
// 세션에 저장된 access-token을 확인하여 사용자가 인증되었는지 판단하는 계산된 속성
const isAuthenticated = computed(() => {
  return userStore.accessToken !== null && userStore.accessToken !== undefined;
});
</script>

<style scoped></style>
