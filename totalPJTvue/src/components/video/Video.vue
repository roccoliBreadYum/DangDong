<template>
  <div class="container" id="background">
    <div class="container d-flex justify-content-center align-items-center">
      <div
        class="d-flex justify-content-center align-items-center flex-column"
        style="margin-top: 30%"
      >
        <div
          class="d-flex justify-content-center align-items-center p-3 m-3"
          style="
            background-color: #a2d8df;
            border-radius: 40px;
            font-weight: 600;
            font-size: larger;
          "
        >
          <p style="margin: 0">[오늘의 추천 운동 🦾]</p>
        </div>
        <div
          class="video-box d-flex justify-content-center align-items-center p-4"
          style="
            height: 270px;
            width: 400px;
            background-color: white;
            border-radius: 40px;
          "
        >
          <iframe
            width="100%"
            height="100%"
            :src="videoURL"
            title="YouTube video player"
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            referrerpolicy="strict-origin-when-cross-origin"
            allowfullscreen
          ></iframe>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from "vue";
import axios from "axios";

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

// 랜덤 숫자를 생성하는 함수
const generateRandomNumber = (start, end) => {
  return getRandomInt(start, end); // 1과 100 사이의 랜덤 정수 생성
};
const videoId = ref("");
const videoURL = computed(() => {
  return `https://www.youtube.com/embed/${videoId.value}`;
});
onMounted(() => {
  const API_URL = `https://api.odcloud.kr/api/15084814/v1/uddi:3f8d6b98-0082-4792-92a8-90d40ecc4bce`;
  axios
    .get(API_URL, {
      params: {
        returnType: "json",
        page: generateRandomNumber(1, 20),
        perPage: 10,
        serviceKey: import.meta.env.VITE_VIDEO_API_KEY,
      },
      headers: {
        Authorization: import.meta.env.VITE_VIDEO_API_KEY,
      },
      withCredentials: false, // withCredentials 옵션을 false로 설정
    })
    .then((response) => {
      const tmp = response.data.data[generateRandomNumber(0, 9)]["동영상주소"];
      videoId.value = tmp.substring(tmp.lastIndexOf("/") + 1);
    })
    .catch((err) => {});
});
</script>

<style scoped>
#container,
#background {
  background-color: #ecf5fe;
  width: 100vw;
  padding: 15px 15px;
  margin-top: 66px;
  margin-bottom: 90.5px;
  min-height: 1000px;
}
</style>
