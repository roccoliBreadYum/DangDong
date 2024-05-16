<template>
    <div id="background" class="container">
      <div class="d-flex align-items-center justify-content-center">
        <div id="create-form" class="d-flex flex-column justify-content-around">
        
        
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue";
  import { useUserStore } from "@/stores/user";
  import { useRouter } from "vue-router";
  
  const store = useUserStore();
  const router = useRouter();

  
  </script>
  
  <style scoped>
  #background {
    background-color: #ecf5fe;
  }
  
  .container > div {
    height: 100vh; /* 화면의 100%만큼의 높이를 갖도록 설정합니다. */
    display: flex; /* 내부 요소들을 정렬하기 위해 flexbox를 사용합니다. */
    justify-content: center; /* 가로 방향으로 가운데 정렬합니다. */
    align-items: center; /* 세로 방향으로 가운데 정렬합니다. */
  }

  #create-form {
    height: 70vh;
    background-color: white;
    width: 90%;
    border-radius: 40px;
  }
  </style>
  