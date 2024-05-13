<template>
  <div>
    <template v-if="!isSearchView">
      <TheHeaderSearchNav />
    </template>
    <RouterView />
    <TheFooterNav />
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import TheFooterNav from "./components/common/TheFooterNav.vue";
import TheHeaderSearchNav from "./components/common/TheHeaderSearchNav.vue";
import { watch, ref, computed } from "vue";

const router = useRouter();

// 현재 경로를 확인하여 searchView 여부를 판단하는 계산된 속성
const isSearchView = computed(() => {
  return router.currentRoute.value.name === "search";
});
</script>

<style scoped></style>
