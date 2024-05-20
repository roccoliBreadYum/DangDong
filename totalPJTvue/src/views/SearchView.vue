<template>
  <div class="container">
    <div id="total">
      <div id="searchIcon">
        <i class="bi bi-search"></i>
      </div>
      <div id="search-bar">
        <div id="search-text">
          <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="상호명을 입력해주세요" v-model="store.searchCondition.word" @keyup.enter="search" aria-label="Recipient's username" aria-describedby="basic-addon2">
            <button class="input-group-text" id="basic-addon2"><i class="bi bi-search" @click="search"></i></button>
          </div>
        </div>
        <div id="search-click">

        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useStoreStore } from '@/stores/store'; 
import { useRouter } from 'vue-router';

const store = useStoreStore()
const router = useRouter()

const search = () => {
  store.searchCondition.key = 'store_name'
  store.searchStoreList()
  router.push({ name: 'storeList' })
}
</script>

<style scoped>
.container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to bottom, white, #a2d8df);
  
}

#searchIcon {
  width: 20rem;
  height: 20rem;
  text-align: center;
  font-size: 11rem;
}

#total{
  /* display: flex; */
  justify-content: center;
  align-items: center;
}

#search-bar{
  margin-bottom: 10rem;
}

.form-control{
  height: 2.5rem
}
</style>