<template>
  <div class="container">
    <div id="total">
      <div id="searchIcon">
        <!-- <i class="bi bi-emoji-surprise" style="color: #a2d8df">👀</i> -->
        <p style="color: gray; opacity: 0.8">👀</p>
      </div>
      <div id="search-bar">
        <div id="search-text">
          <div class="input-group mb-3">
            <input
              type="text"
              class="form-control"
              placeholder="상호명을 입력해주세요"
              v-model="store.searchCondition.word"
              @keyup.enter="search"
              aria-label="Recipient's username"
              aria-describedby="basic-addon2"
            />
            <button class="input-group-text" id="basic-addon2">
              <i class="bi bi-search" @click="search"></i>
            </button>
          </div>
        </div>
        <div id="search-click"></div>
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

const search = () => {
  store.searchCondition.key = "store_name";
  store.searchStoreList();
  router.push({ name: "storeList" });
};
</script>

<style scoped>
.container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ecf5fe;
  min-height: 1000px;
}

#searchIcon {
  width: 20rem;
  height: 20rem;
  text-align: center;
  font-size: 11rem;
}

#total {
  /* display: flex; */
  justify-content: center;
  align-items: center;
}

#search-bar {
  margin-bottom: 23rem;
}

.form-control {
  height: 5rem;
  border: 4px solid #a2d8df;
  text-align: center;
  font-weight: 600;
}

.input-group-text {
  background-color: #a2d8df;
  border-color: #a2d8df;
}

.input-group-text i {
  color: white;
  font-size: 30px;
  font-weight: 800;
}
</style>
