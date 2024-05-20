import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_API_STORE = "http://localhost:8080/api-store";

export const useStoreStore = defineStore("store", () => {
  const accessToken = computed(() => sessionStorage.getItem("access-token"));
  const storeTicketList = ref([])
  const categoryNum = ref("");
  const category = ref("");
  const storeList = ref([]);
  const storeDetail = ref({});
  const searchCondition = ref({
    category: -1,
    key: "none",
    word: "",
    orderBy: "none",
    orderByDir: "asc",
  });

  const searchStoreList = () => {
    axios
      .get(REST_API_STORE, {
        params: searchCondition.value,
        headers: {
          "access-token": accessToken.value,
        },
      })
      .then((res) => {
        console.log(res);
        storeList.value = res.data;
      })
      .catch((error) => {
        console.error("Error fetching store list:", error);
      });
  };

  const getStoreDetail = (storeId, userId) => {
    axios
      .get(`${REST_API_STORE}/${storeId}/${userId}`, {
        headers: {
          "access-token": accessToken.value,
        },
      })
      .then((res) => {
        console.log(res);
        storeDetail.value = res.data;
      })
      .catch((error) => {
        console.error("Error fetching store detail:", error);
      });
  };

  const getCategory = (exerciseId) => {
    axios
      .get(`${REST_API_STORE}/category/${exerciseId}`, {
        headers: {
          "access-token": accessToken.value,
        },
      })
      .then((res) => {
        console.log(res);
        category.value = res.data;
      });
  };

  const getStoreTicketList = (storeId) => {
    axios.get(`${REST_API_STORE}/${storeId}/ticket`, {
        headers: {
            "access-token": accessToken.value,
        }
    })
    .then((res) => {
        console.log(res)
        storeTicketList.value = res.data
    })
  };

  return {
    category,
    storeList,
    storeDetail,
    searchCondition,
    searchStoreList,
    getStoreDetail,
    getCategory,
    storeTicketList,
    getStoreTicketList,
  };
});
