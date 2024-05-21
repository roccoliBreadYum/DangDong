import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useAuthStore } from "@/stores/auth";
import router from "@/router";



const REST_API_STORE = "http://localhost:8080/api-store";

export const useStoreStore = defineStore("store", () => {
  const store = useAuthStore()
  const accessToken = computed(() => store.getAccessToken());
  const storeTicketList = ref([])
  const category = ref("");
  const storeList = ref([]);
  const storeDetail = ref({});
  const storeImg = ref([]);
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

  const getImg = (storeId) => {
    axios.get(`${REST_API_STORE}/${storeId}/image`, {
      headers: {
        "access-token": accessToken.value,
      },
    })
    .then((res) => {
      console.log(res)
      storeImg.value = res.data;
    })
  }

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

  const registStore = (store, file) => {
    const formData = new FormData();
    formData.append(
      "store",
      new Blob([JSON.stringify(store)], { type: "application/json" })
    );
    formData.append("file", file);

    axios.post(`${REST_API_STORE}/regist`, formData, {
      headers: {
        "access-token": accessToken.value,
        "Content-Type": "multipart/form-data",
      },
    })
    .then(() => {
      router.push({ name: "home" });
    })
  }

  const updateStore = (store, file) => {
    const formData = new FormData();
    formData.append(
      "store",
      new Blob([JSON.stringify(store)], { type: "application/json" })
    );

    formData.append("file", file);

    axios.put(`${REST_API_STORE}/update`, formData,{
        headers: {
            "access-token": accessToken.value,
        }
    })
    .then(() => {
      router.push({ name: "home" });
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
    registStore,
    updateStore,
    getImg,
    storeImg,

  };
});
