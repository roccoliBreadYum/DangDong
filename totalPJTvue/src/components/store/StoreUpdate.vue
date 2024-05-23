<template>
    <div id="background">
        <div class="container my-5 p-5 bg-white rounded-4 shadow">
            <h3 class="text-center mb-4">업체 수정</h3>
            <form @submit.prevent="updateStore">
            <div id="regist-header" class="mb-4">
                <div id="name" class="mb-3">
                <label for="storeName" class="form-label">가게 명</label>
                <input type="text" id="storeName" class="form-control" placeholder="최대 10자" v-model="storeObj.storeName" readonly>
                </div>
                <div id="category" class="mb-3">
                <label for="categorySelect" class="form-label">카테고리</label>
                <select id="categorySelect" class="form-select" :v-model="storeObj.excerciseId" >
                    <option value="none">구분</option>
                    <option value="1">필라테스</option>
                    <option value="2">헬스</option>
                    <option value="3">복싱</option>
                    <option value="4">크로스핏</option>
                    <option value="5">클라이밍</option>
                    <option value="6">수영</option>
                    <option value="7">태권도</option>
                    <option value="8">주짓수</option>
                    <option value="9">요가</option>
                </select>
                </div>
            </div>
            <div id="regist-body" class="mb-4">
                <div class="mb-3">
                <label for="storeAddress" class="form-label">주소</label>
                <input type="text" id="storeAddress" class="form-control" v-model="storeObj.address">
                </div>
                <div class="mb-3">
                <label for="phoneNumber" class="form-label">전화번호</label>
                <small class="text-muted d-block mb-2">Format: 123-456-7890</small>
                <input type="text" id="phoneNumber" class="form-control" v-model="storeObj.phoneNumber">
                </div>
                <div class="mb-3">
                <label for="openingHours" class="form-label">영업시간</label>
                <small class="text-muted d-block mb-2">Format: 9:00 AM - 8:00 PM</small>
                <input type="text" id="openingHours" class="form-control" v-model="storeObj.openingHours">
                </div>
            </div>
            <div id="regist-img" class="mb-4">
                <label for="logoImage" class="form-label">이미지 추가</label>
                <input type="file" id="logoImage" class="form-control" @change="handleFileChange" />
            </div>
            <div id="buttons" class="d-flex justify-content-between">
                <button type="submit" id="regist-btn" class="btn btn-primary">등록</button>
                <button @click.prevent="backButton" id="back-btn" class="btn btn-secondary">뒤로</button>
            </div>
            </form>
        </div>
    </div>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router'
import { useStoreStore } from '@/stores/store';
import { useAuthStore } from "@/stores/auth";

const authStore = useAuthStore()
const router = useRouter()
const route = useRoute()
const store = useStoreStore() 
const file = ref(null)
const loginUserId = authStore.getLoginUserId();                                            

const storeObj = ref({
    storeName:"",
    excerciseId:"",
    address:"",
    phoneNumber:"",
    openingHours:"",
    logoImage:"",
    ownerId: loginUserId
})                        

const handleFileChange = (event) => {
  file.value = event.target.files[0];
};

const backButton = () => {
  router.go(-1);
};

const updateStore = ()=>  {
  store.updateStore(storeObj.value, file.value);
};

onMounted(() => {
  store.getStoreDetail(route.params.storeId, loginUserId)
  storeObj.value = store.storeDetail
  storeObj.value.logoImage = null;
})
</script>

<style scoped>
#background {
  background-color: #ecf5fe;
  width: 100vw;
  padding: 15px 15px;
  margin-top: -4.2rem;
  margin-bottom: 90.5px;
  min-height: 1000px;
  margin-left: -0.75rem
}

.container {
  max-width: 600px;
  margin: auto;
  background-color: #ecf5fe;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h3 {
  color: #333;
}

#regist-header {
  display: flex;
  justify-content: space-between;
}

#regist-header > div {
  flex: 1;
  margin-right: 10px;
}

#regist-header > div:last-child {
  margin-right: 0;
}

#regist-body > div,
#regist-img > div {
  margin-bottom: 15px;
}

#buttons {
  display: flex;
  justify-content: space-between;
}

#regist-btn,
#back-btn {
  width: 48%;
}

#regist-btn {
  background-color: #a2d8df;
  border: none;
}

#back-btn {
  background-color: rgb(192, 200, 201);
  border: none;
}
</style>
