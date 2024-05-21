<template>
  <div calss="container">
    <!-- 업체 정보 위치 -->
    <div id="detail" class="shadow" style="margin-top: 1rem">
      <!-- 상호명, 채팅, 즐겨찾기 -->
      <div
        id="detail-head"
        class="d-flex justify-content-between align-items-center"
      >
        <div id="head-title" class="p-2">
          <h3>{{ store.storeDetail.storeName }}</h3>
        </div>
        <div id="head-icons" class="p-2 d-flex flex-row mb-3 align-items-center">
          <button v-if="isOwner" class="modifyButton" @click="moveUpdate">수정</button>
          <i id="icons-talk" class="bi bi-chat-dots p-2 px-3"></i>
          <div v-if="store.storeDetail.isFavorite === 1">
            <button id="icon-btn" @click="swichFavorite">
              <i
                id="icons"
                class="bi bi-star-fill p-2"
                style="color: #ffc81e"
              ></i>
            </button>
          </div>
          <div v-else>
            <button id="icon-btn" @click="swichFavorite">
              <i id="icons" class="bi bi-star p-2" style="color: #ffc81e"></i>
            </button>
          </div>
        </div>
      </div>
      <!-- 업체사진 -->
      <div class="d-flex justify-content-around">
        <img v-for="(image, index) in store.storeImg" 
        :src="`https://ssafit08-11th.s3.ap-southeast-2.amazonaws.com/${image}`" 
        :key="index" 
        class="p-2 rounded-3" 
        id="store-img" />            
        <!-- <img :src="`https://ssafit08-11th.s3.ap-southeast-2.amazonaws.com/${img}`"/> -->
          <!-- <img :src="`https://ssafit08-11th.s3.ap-southeast-2.amazonaws.com/${store.storeDetail.logoImage}`" class="p-2 rounded-3" id="store-img" /> -->
          <!-- <img src="@/assets/temp.png" class="p-2 rounded-3" id="store-img" /> -->
      </div>
      <!-- 상세정보 -->
      <div id="store-detail" class="my-3 pb-3">
        <div class="my-1">
          <i class="bi bi-geo-alt"></i> {{ store.storeDetail.address }}
        </div>
        <div class="my-1">
          <i class="bi bi-telephone-fill"></i>
          {{ store.storeDetail.phoneNumber }}
        </div>
        <div class="my-1">
          <i class="bi bi-clock-fill"></i> {{ store.storeDetail.openingHours }}
        </div>
      </div>
    </div>

    <!-- 구매 / 예약 버튼 위치 -->
    <div id="button" class="d-flex justify-content-around pb-3">
      <RouterLink :to="{name: 'sellTicket', params:{storeId: route.params.storeId}}" class="btn">
          <p id="btn-type">정기권/다회권</p>
          <p id="btn-click">구매</p>
      </RouterLink>
      <button class="btn">
        <p id="btn-type">일일권/이용권</p>
        <p id="btn-click">예약</p>
      </button>
    </div>

    <!-- 공지사항 위치 -->
    <StoreNotice />
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useStoreStore } from "@/stores/store";
import { useUserStore } from "@/stores/user";
import { useAuthStore } from "@/stores/auth";
import { useRoute, useRouter } from "vue-router";
import StoreNotice from "@/components/store/StoreNotice.vue";

const store = useStoreStore();
const userStore = useUserStore();
const authStore = useAuthStore();
const route = useRoute();
const router = useRouter();

const loginUserId = ref(authStore.getLoginUserId());

const swichFavorite = () => {
  userStore.updateFavorite(store.storeDetail.isFavorite, loginUserId.value, route.params.storeId)
    .then(() => {
      store.getStoreDetail(route.params.storeId, loginUserId.value);
    })
    .catch(() => {

    });
};

const isOwner = computed(() => store.storeDetail.ownerId === loginUserId.value);

const moveUpdate = () => {
  router.push({name:'storeUpdate', params:{storeId: route.params.storeId }});
}

onMounted(() => {
  store.getStoreDetail(route.params.storeId, loginUserId.value);
  store.getImg(route.params.storeId)
});
</script>

<style scoped>
#detail-head {
  margin-bottom: -6%;
}
.btn {
  background-color: #a2d8df;
  color: #fff;
  border-radius: 30px;
}

#btn-type {
  margin: -0.2rem;
}

#btn-click {
  margin: -0.4rem;
  padding: 0 3.5rem;
  font-size: x-large;
}

#store-detail {
  margin-left: 0.5rem;
}

#head-icons {
  padding: 0rem !important;
  margin-right: 0.5rem;
}

#icon-btn {
  background-color: white;
  border: none;
}

#icons,
#icons-talk {
  font-size: 1.5rem;
  margin: 0 -0.5rem;
}

#icons-talk {
  padding: -50px;
}

#store-img {
  width: 12rem;
  height: 10rem;
}

.modifyButton{
  background-color:orange ;
  border-radius: 25%;
  border: none;
}
</style>
