import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useAuthStore } from "@/stores/auth";



const REST_API_LESSON = "http://localhost:8080/api-lesson";
const REST_API_TICKET = "http://localhost:8080/api-ticket/ticket";


export const uselessonStore = defineStore("lesson", () => {
    const store = useAuthStore();
    const accessToken = computed(() => store.getAccessToken());

    const lessonList = ref([])
    const teacherList = ref([])
    const lessonInfo = ref({})
    const usableTicket = ref([])

    const getUsableTicket = (storeId, userId) => {
        axios.get(`${REST_API_TICKET}/${storeId}/${userId}`, {
            headers: {
              "access-token": accessToken.value,
            },
        })
        .then((res) => {
            console.log(res)
            usableTicket.value = res.data;
        })
    }

    const getLessonList = (storeId, date) => [
        axios.get(`${REST_API_LESSON}/lesson/${storeId}/${date}`, {
            headers: {
              "access-token": accessToken.value,
            },
        })
        .then((res) => {
            lessonList.value = res.data;
        })
    ]

    const getTeacherList = (storeId, date) => {
        axios.get(`${REST_API_LESSON}/teacher/${storeId}/${date}`, {
            headers: {
              "access-token": accessToken.value,
            },
        })
        .then((res) => {
            teacherList.value = res.data;
        })
    }

    const getLesson = (lessonId) => {
        axios.get(`${REST_API_LESSON}/lesson/${lessonId}`, {
            headers: {
              "access-token": accessToken.value,
            },
        })
        .then((res) => {
            console.log(res)
            lessonInfo.value = res.data;
        })
    }


    return{
        lessonList,
        teacherList,
        lessonInfo,
        usableTicket,
        getLessonList,
        getTeacherList,
        getLesson,
        getUsableTicket,


    }
}, {persist:true});