<template>
  <div style="background-color: #d7fefe" class="container fixed-top py-2">
    <header class="d-flex justify-content-around align-items-center">
      <button
        type="button"
        class="btn btn-outline-white btn-group-lg bg-white d-flex align-items-center"
        style="height: 50px; width: 300px"
        @click.prevent="navigateToSearchPage"
      >
        <i class="bi bi-search text-dark me-2" style="font-size: 25px"></i>
        <RouterLink
          id="search-bar"
          :to="{ name: 'search' }"
          class="ms-4"
          style="
            font-size: 18px;
            font-weight: 550;
            text-align: right;
            color: #d3d3d3;
          "
          >{{ store.category }}가게 검색하기</RouterLink
        >
      </button>
      <div
        class="d-flex justify-content-center align-items-center"
        id="video-icon-box"
      >
        <button
          id="video-icon"
          @click.prevent="navigateToVideoPage"
          class="d-flex justify-content-center align-items-center"
        >
          <i class="bi bi-youtube"></i>
        </button>
      </div>
    </header>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { useStoreStore } from "@/stores/store";

const router = useRouter();
const store = useStoreStore();
const route = useRoute();

const navigateToSearchPage = function () {
  router.push({ name: "search" });
};

const navigateToVideoPage = function () {
  if (route.name === "video") {
    // 현재 경로가 "video"이면 페이지를 새로고침
    router.go(0);
  } else {
    // 그렇지 않으면 "video" 페이지로 이동
    router.push({ name: "video" });
  }
};
const searchInfo = {
  category: -1,
  key: "none",
  word: "",
  orderBy: "none",
  orderByDir: "asc",
};

store.searchCondition = searchInfo;
</script>

<style scoped>
#search-bar {
  text-decoration-line: none;
}
#video-icon {
  color: red;
  font-size: 40px;
  border: 0px solid #d7fefe;
  background-color: #d7fefe;
}
</style>
