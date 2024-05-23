<template>
    <div class="text-center">
        <div class="row align-items-center justify-content-center">
            <div class="col-auto">
                <select class="form-select" v-model="store.searchCondition.category">
                    <option value="none">운동 선택</option>
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
            <div class="col-auto">
                <select class="form-select" v-model="store.searchCondition.orderBy">
                    <option value="none">정렬기준</option>
                    <option value="teacherCount">강사 수</option>
                    <option value="store_name">업체 명</option>
                </select>
            </div>
            <div class="col-auto">
                <div @click="sortChange" class="sort-icon">
                    <span v-if="store.searchCondition.orderByDir === 'asc'">
                        <i class="bi bi-arrow-up-right-square-fill"></i>
                    </span>
                    <span v-else>
                        <i class="bi bi-arrow-down-right-square-fill"></i>
                    </span>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script setup>
import { ref, watch } from "vue";
import { useStoreStore } from '@/stores/store';

const store = useStoreStore();

const searchInfo = ref({
    category: '',
    key: "none",
    word: "",
    orderBy: "none",
    orderByDir: "asc",
});

const searchBoardList = function () {
    store.searchStoreList(searchInfo.value); // 이 함수는 Pinia store에 있는 함수로, store 목록을 갱신합니다.
};

const sortChange = function () {
    if (store.searchCondition.orderByDir === "asc")
        store.searchCondition.orderByDir = "desc";
    else 
        store.searchCondition.orderByDir = "asc";
};

// 검색 조건이 변경될 때마다 자동으로 목록을 갱신합니다.
watch(store.searchCondition, () => {
    store.searchStoreList()
})

</script>
  
<style scoped>
.text-center {
    text-align: center;
}

.row {
    display: flex;
    align-items: center;
    justify-content: center;
}

.col-auto {
    margin: 0 10px;
}

.sort-icon {
    cursor: pointer;
}
</style>
