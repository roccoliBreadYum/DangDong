<template>
  <br />
  <div id="container" class="d-flex align-items-center justify-content-center">
    <div
      class="d-flex flex-column p-2"
      style="
        border: 1.5px solid #e2e2e2;
        border-radius: 25px;
        background-color: white;
        width: 366px;
      "
    >
      <h5 style="font-weight: 600; margin-left: 15px">운동 예약</h5>
      <div class="category-content">
        <div class="d-flex align-items-center justify-content-center">
          <button
            type="button"
            class="btn btn-white p-2"
            @click="sendCategory(1)"
          >
            <img
              src="@/assets/categoryImg/필라테스.PNG"
              alt="categoryImg"
            /><br />필라테스
          </button>
          <button
            type="button"
            class="btn btn-white p-2"
            @click="sendCategory(2)"
          >
            <img
              src="@/assets/categoryImg/헬스.PNG"
              alt="categoryImg"
            /><br />헬스
          </button>
          <button
            type="button"
            class="btn btn-white p-2"
            @click="sendCategory(3)"
          >
            <img
              src="@/assets/categoryImg/복싱.PNG"
              alt="categoryImg"
            /><br />복싱
          </button>
        </div>
        <div class="d-flex flex-row align-items-center justify-content-center">
          <button
            type="button"
            class="btn btn-white p-2"
            @click="sendCategory(4)"
          >
            <img
              src="@/assets/categoryImg/크로스핏.PNG"
              alt="categoryImg"
            /><br />크로스핏
          </button>
          <button
            type="button"
            class="btn btn-white p-2"
            @click="sendCategory(5)"
          >
            <img
              src="@/assets/categoryImg/클라이밍.PNG"
              alt="categoryImg"
            /><br />클라이밍
          </button>
          <button
            type="button"
            class="btn btn-white p-2"
            @click="sendCategory(6)"
          >
            <img
              src="@/assets/categoryImg/수영.PNG"
              alt="categoryImg"
            /><br />수영
          </button>
        </div>
        <div class="d-flex flex-row align-items-center justify-content-center">
          <button
            type="button"
            class="btn btn-white p-2"
            @click="sendCategory(7)"
          >
            <img
              src="@/assets/categoryImg/태권도.PNG"
              alt="categoryImg"
            /><br />태권도
          </button>
          <button
            type="button"
            class="btn btn-white p-2"
            @click="sendCategory(8)"
          >
            <img
              src="@/assets/categoryImg/주짓수.PNG"
              alt="categoryImg"
            /><br />주짓수
          </button>
          <button
            type="button"
            class="btn btn-white p-2"
            @click="sendCategory(9)"
          >
            <img
              src="@/assets/categoryImg/요가.PNG"
              alt="categoryImg"
            /><br />요가
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useStoreStore } from "@/stores/store";
import { useRouter } from "vue-router";

const store = useStoreStore();
const router = useRouter();
const searchInfo = ref({
  category: -1,
  key: "none",
  word: "",
  orderBy: "none",
  orderByDir: "",
});

const sendCategory = (num) => {
  store.searchCondition.category = num;
  store.searchStoreList();
  router.push({ name: "storeList" });
};
</script>

<style scoped>
/* #container {
  background-color: #ecf5fe;
  margin-bottom: 90.5px;
  min-height: 1000px;
} */
button {
  height: 100px;
  width: 100px;
  font-size: small;
  margin: 3px;
}
button img {
  height: 90%;
  width: 90%;
  border-radius: 12px;
}
</style>
