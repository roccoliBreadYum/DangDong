<template>
  <div style="background-color: #d7fefe" class="container fixed-top py-2">
    <header class="d-flex justify-content-center">
      <button
        type="button"
        class="btn btn-outline-white btn-group-lg bg-white d-flex align-items-center"
        style="height: 50px; width: 300px"
        @click.prevent="navigateToSearchPage"
      >
        <i class="bi bi-search text-dark me-2" style="font-size: 25px"></i>
        <RouterLink id="search-bar" :to="{ name: 'search' }"
          class="ms-4"
          style="
            font-size: 18px;
            font-weight: 550;
            text-align: right;
            color: #d3d3d3;
          "
          >{{store.category}}가게 검색하기</RouterLink
        >
      </button>
    </header>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { useStoreStore } from '@/stores/store';

const router = useRouter();
const store = useStoreStore();


const navigateToSearchPage = function () {
  router.push({ name: "search" });
};


const searchInfo = {
    category: -1,
    key: 'none',
    word: '',
    orderBy: 'none',
    orderByDir: 'asc'
  };

  store.searchCondition = searchInfo


</script>

<style scoped>
#search-bar{
  text-decoration-line : none;
}

</style>
