<template>
  <div>
    <!-- <template v-if="isLogin"> -->
      <template v-if="!isSearchView">
        <TheHeaderSearchNav />
      </template>
      <RouterView />
      <TheFooterNav />
    <!-- </template> -->
    <!-- <template v-else>
      <LoginView />
    </template> -->
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import TheFooterNav from "./components/common/TheFooterNav.vue";
import TheHeaderSearchNav from "./components/common/TheHeaderSearchNav.vue";
import { watch, ref, computed } from "vue";
import { useUserStore } from "./stores/user";
import LoginView from "./views/LoginView.vue";

const router = useRouter();
const userStore = useUserStore(); //로그인 여부 판단

// 현재 경로를 확인하여 searchView 여부를 판단하는 계산된 속성
const isSearchView = computed(() => {
  return router.currentRoute.value.name === "search";
});

// 로그인 여부를 판단하는 계산된 속성
const isLogin = computed(() => {
  return !!sessionStorage.getItem("access-token");
});

// 세션 스토리지 변경을 감지하여 로그인 상태 갱신
watch(
  () => sessionStorage.getItem("access-token"),
  (newVal, oldVal) => {
    if (newVal && !oldVal) {
      // 토큰이 새로 저장된 경우
      isLogin.value = true;
    } else if (!newVal && oldVal) {
      // 토큰이 삭제된 경우
      isLogin.value = false;
    }
  }
);

// export default {
//   name: 'App',
//   created() {
//     //메인 컴포넌트를 렌더링하면서 토큰체크
//     let token = this.$store.getters.getToken;
//     if (token.access == null && token.refresh == null) { //다 없으면 로그인 페이지로
//       //이미 로그인 페이지가 떠있는 상태에서 새로 고침하면 중복 에러 떠서 이렇게 처리함
//       this.$router.push({name: 'login'}).catch(() => {}); 
//     }
//   },
//   components: {
//     'topMenu': menu
//   }
// }

</script>

<style scoped></style>
