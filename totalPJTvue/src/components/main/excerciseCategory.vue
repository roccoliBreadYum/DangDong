<template>
  <br>
    <div class="container">
      <h2> 운동 예약</h2>
      <div>
        <div class="d-flex flex-row mb-2">
          <button type="button" class="btn btn-white p-2" @click="sendCategory(1)"><img src="@/assets/temp.png" style="width: 55%;"><br>운동이름</button>
          <button type="button" class="btn btn-white p-2" @click="sendCategory(2)"><img src="@/assets/temp.png" style="width: 55%;"><br>운동이름</button>
          <button type="button" class="btn btn-white p-2" @click="sendCategory(3)"><img src="@/assets/temp.png" style="width: 55%;"><br>운동이름</button>
        </div>
        <div class="d-flex flex-row mb-2">
          <button type="button" class="btn btn-white p-2" @click="sendCategory(4)"><img src="@/assets/temp.png" style="width: 55%;"><br>운동이름</button> 
          <button type="button" class="btn btn-white p-2" @click="sendCategory(5)"><img src="@/assets/temp.png" style="width: 55%;"><br>운동이름</button>
          <button type="button" class="btn btn-white p-2" @click="sendCategory(6)"><img src="@/assets/temp.png" style="width: 55%;"><br>운동이름</button>
        </div>
        <div class="d-flex flex-row mb-2">
          <button type="button" class="btn btn-white p-2" @click="sendCategory(7)"><img src="@/assets/temp.png" style="width: 55%;"><br>운동이름</button>
          <button type="button" class="btn btn-white p-2" @click="sendCategory(8)"><img src="@/assets/temp.png" style="width: 55%;"><br>운동이름</button>
          <button type="button" class="btn btn-white p-2" @click="sendCategory(9)"><img src="@/assets/temp.png" style="width: 55%;"><br>운동이름</button>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { useStoreStore } from '@/stores/store'; 
  import { useRouter } from 'vue-router';

  const store = useStoreStore()
  const router = useRouter()

  const sendCategory = (num) => {
    const searchInfo = {
      category : num
    }

    store.searchStoreList(searchInfo)
    router.push({name:"storeList"})
  }



  </script>
  
  <style scoped>

  </style>
  