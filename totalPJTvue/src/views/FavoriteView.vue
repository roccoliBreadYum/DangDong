<template>
  <div class="container" id="background">
    <div>
      <template
        v-if="
          !userStore.favoriteStoreList ||
          userStore.favoriteStoreList.length === 0
        "
      >
        <div id="store-none"><p>즐겨찾기 목록이 비어있습니다.</p></div>
      </template>
      <div
        v-for="st in userStore.favoriteStoreList"
        :key="st.storeId"
        class="store-item mb-3 rounded-5 shadow"
      >
        <div class="store-link">
          <div class="store-info d-flex flex-column" style="color: black">
            <div class="d-flex flex-row justify-content-between">
              <div class="d-flex justify-content-center align-items-center">
                <p id="name" fl>{{ st.storeName }}</p>
              </div>
              <button
                class="mx-3"
                id="icon-btn"
                @click="deleteFavorite(st.storeId)"
              >
                <i
                  id="icons"
                  class="bi bi-star-fill p-2"
                  style="color: #ffc81e"
                ></i>
              </button>
            </div>
            <div>
              <h6>{{ st.description }}</h6>
              <h6>{{ st.openingHours }}</h6>
              <div class="d-flex">
                <div>⭐: {{ st.favoriteCount }}개</div>
                <div style="margin-left: 20px">👥: {{ st.teacherCount }}명</div>
                <div>
                  <RouterLink
                    :to="`/store/${st.storeId}`"
                    id="go-btn"
                    class="p-2"
                    style="margin-left: 55px"
                    >바로가기 ></RouterLink
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useAuthStore } from "@/stores/auth";
import { useUserStore } from "@/stores/user";
import { onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import axios from "axios";

const userStore = useUserStore();
const authStore = useAuthStore();
const router = useRouter();
const loginUserId = authStore.getLoginUserId();
const accessToken = authStore.getAccessToken();

onMounted(() => {
  userStore.getFavoriteStoreList(loginUserId);
  // console.log(loginUserId);
  // console.log(userStore.favoriteStoreList);
});

const deleteFavorite = function (storeId) {
  const userConfirm = confirm("즐겨찾기에서 삭제하시겠습니까?");
  if (userConfirm) {
    axios
      .delete(
        `http://localhost:8080/api-favorite/favorite/${loginUserId}/${storeId}`,
        {
          headers: { "access-token": accessToken },
        }
      )
      .then(() => {
        window.location.reload(); //새로고침
        // router.push({ name: "favorite" });
      })
      .catch((e) => {});
  }
};
</script>

<style scoped>
#background {
  background-color: #ecf5fe;
  width: 100vw;
  padding: 15px 15px;
  margin-top: 75px;
  margin-bottom: 90.5px;
  min-height: 1000px;
}

#name {
  color: #38747c;
  font-size: 20px;
  font-weight: 600;
  text-align: center;
  margin-bottom: 0;
}

h6 {
  font-size: small;
}

.container {
  display: flex;
  flex-direction: column;
}

.store-link {
  display: flex;
  text-decoration: none;
  align-items: center;
  padding: 10px;
  background-color: white;
  border-radius: 30px;
}

#store-none {
  display: flex;
  text-decoration: none;
  align-items: center;
  padding: 10px;
  background-color: white;
  border-radius: 30px;
  justify-content: center;
}

.store-image {
  flex-shrink: 0;
  width: 30%;
  border-radius: 25px;
}

.store-info {
  padding-left: 20px;
  width: 100%;
}

h5 {
  font-weight: 600;
}

#icon-btn {
  background-color: white;
  border: none;
}

#go-btn {
  text-decoration: none;
  color: black;
}

#icons {
  font-size: 1.5rem;
  margin: 0 -0.5rem;
}
</style>
