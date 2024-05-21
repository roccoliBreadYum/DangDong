import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useAuthStore } from "@/stores/auth";



const REST_API_STORE = "http://localhost:8080/api-lesson";

export const uselessonStore = defineStore("lesson", () => {
    const store = useAuthStore();
    const accessToken = computed(() => store.getAccessToken());

    const lessonList = ref([])
    const teacherList = ref([])


    const getLessonList = (storeId, date) => [
        axios.get(`${REST_API_STORE}/lesson/${storeId}/${date}`, {
            headers: {
              "access-token": accessToken.value,
            },
        })
        .then((res) => {
            lessonList.value = res.data;
        })
    ]

    const getTeacherList = (storeId, date) => {
        axios.get(`${REST_API_STORE}/teacher/${storeId}/${date}`, {
            headers: {
              "access-token": accessToken.value,
            },
        })
        .then((res) => {
            teacherList.value = res.data;
        })
    }


    return{
        lessonList,
        teacherList,
        getLessonList,
        getTeacherList,

    }
}, {persist:true});