<template>
  <div class="container">
    <storeSearch style="margin-bottom: 1rem" />
    <div
      v-for="st in store.storeList"
      :key="st.storeId"
      class="store-item mb-3 rounded-5 shadow"
    >
      <RouterLink :to="`/store/${st.storeId}`" class="store-link">
        <img :src="`https://ssafit08-11th.s3.ap-southeast-2.amazonaws.com/storeThumbnail/${st.thumbnailImg}`"
        alt="..." class="store-image rounded-3" />
        <div class="store-info" style="color: black">
          <h5>{{ st.storeName }}</h5>
          <div>{{ st.description }}</div>
          <div>{{ st.openingHours }}</div>
          <div>강사 수: {{ st.teacherCount }}</div>
        </div>
      </RouterLink>
    </div>
  </div>
</template>

<script setup>
import { useStoreStore } from "@/stores/store";
import { useUserStore } from "@/stores/user";
import { onMounted, ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import storeSearch from "@/components/store/StoreSearch.vue";

const store = useStoreStore();
const userStore = useUserStore();
const router = useRouter();

onMounted(() => {
  store.getCategory(store.searchCondition.category);
});
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
}

.store-link {
  display: flex;
  text-decoration: none;
  align-items: center;
  padding: 10px;
}

.store-image {
  flex-shrink: 0;
  width: 30%;
  border-radius: 5px;
}

.store-info {
  padding-left: 20px;
}
</style>
