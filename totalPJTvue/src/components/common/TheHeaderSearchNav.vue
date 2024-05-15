<template>
  <div style="background-color: #d7fefe" class="container py-2">
    <header class="d-flex justify-content-center">
      <button
        type="button"
        class="btn btn-outline-white btn-group-lg bg-white d-flex align-items-center"
        style="height: 50px; width: 300px"
        @click.prevent="navigateToSearchPage"
      >
        <i class="bi bi-search text-dark me-2" style="font-size: 25px"></i>
        <span
          class="ms-5"
          style="
            font-size: 18px;
            font-weight: 550;
            text-align: right;
            color: #d3d3d3;
          "
          >가게 검색하기</span
        >
      </button>
    </header>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();
const navigateToSearchPage = function () {
  router.push({ name: "search" });
};
</script>

<style scoped></style>
