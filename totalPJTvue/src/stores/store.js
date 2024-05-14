import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";


const REST_API_STORE ="http://localhost:8080/api-store";

export const useStoreStore = defineStore("store", () => {

    const storeList = ref([])
    const searchStoreList = (searchCondition) => {
        axios.get(REST_API_STORE,{
            params: searchCondition
        })
        .then((res) => {
            storeList.value = res.data
        })
    }

  

    return {
        storeList,
        searchStoreList,
    };
});
