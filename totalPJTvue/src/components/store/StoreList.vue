<template>
    <div class="container">
        <div v-for="st in store.storeList" :key="st.storeId" class="store-item mb-3 rounded-5 shadow">
            <a href="https://www.naver.com" class="store-link">
                <img src="@/assets/temp.png" alt="..." class="store-image rounded-3">
                <div class="store-info" style="color: black;">
                    <h5>{{ st.storeName }}</h5>
                    <div>{{ st.description }}</div>
                    <div>{{ st.openingHours }}</div>
                    <div>강사 수: {{ st.teacherCount }}</div>
                </div>
            </a>
        </div>
    </div>
</template>

<script setup>
import { useStoreStore } from '@/stores/store';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

const store = useStoreStore()


onMounted(() => {


})
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
