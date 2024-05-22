<template>
    <div class="container">
      <div id="header" class="mb-3">
        <div class="d-flex align-items-center justify-content-center">
          <button @click="decreaseDate" class="btn btn-outline-primary me-2">&#9664;</button>
          <div id="calendar">
            <input type="date" v-model="selectedDate" class="form-control date-input" />
          </div>
          <button @click="increaseDate" class="btn btn-outline-primary ms-2">&#9654;</button>
        </div>
      </div>
      <div id="body" class="row">
        <div v-for="teacher in store.teacherList" :key="teacher.teacherId" class="col-md-6 mb-4">
          <div class="card shadow-sm">
            <div class="card-header text-white">
              {{ teacher.name }} 강사
            </div>
            <div class="card-body">
              <div v-for="lesson in store.lessonList" :key="lesson.lessonId" class="mb-2">
                <div v-if="teacher.teacherId === lesson.teacherId">
                  <button 
                    :disabled="lesson.userCnt === lesson.capacity"
                    :class="{'btn-enabled': lesson.userCnt !== lesson.capacity, 
                            'btn-disabled': lesson.userCnt === lesson.capacity}"
                    class="btn w-100 d-flex justify-content-between align-items-center">
                    <div>{{ formatTime(lesson.date) }}</div>
                    <div>{{ lesson.userCnt }} / {{ lesson.capacity }}</div>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div> 
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, watch, onMounted } from 'vue'
  import { useRoute } from 'vue-router'
  import { uselessonStore } from "@/stores/lesson";
  
  const store = uselessonStore()
  const route = useRoute()
  
  const getTodayDate = () => {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }
  
  const formatTime = (datetime) => {
    const date = new Date(datetime);
    const hours = String(date.getUTCHours()).padStart(2, '0');
    const minutes = String(date.getUTCMinutes()).padStart(2, '0');
    return `${hours}:${minutes}`;
  }
  
  const selectedDate = ref(getTodayDate());
  
  const decreaseDate = () => {
    const currentDate = new Date(selectedDate.value);
    currentDate.setDate(currentDate.getDate() - 1);
    selectedDate.value = currentDate.toISOString().substr(0, 10);
  };
  
  const increaseDate = () => {
    const currentDate = new Date(selectedDate.value);
    currentDate.setDate(currentDate.getDate() + 1);
    selectedDate.value = currentDate.toISOString().substr(0, 10);
  };
  
  watch(selectedDate, () => {
      console.log("Selected date:", selectedDate.value);
      store.getLessonList(route.params.storeId, selectedDate.value)
      store.getTeacherList(route.params.storeId, selectedDate.value)
  });
  
  onMounted(() => {
      store.getLessonList(route.params.storeId, selectedDate.value)
      store.getTeacherList(route.params.storeId, selectedDate.value)
      console.log(store.lessonList)
  })
  </script>
  
  <style scoped>


  #calendar {
    width: 12rem;
  }
  
  .date-input {
    font-size: 16px;
    padding: 5px;
  }
  
  button {
    margin-bottom: 10px;
    padding: 10px;
    border: none;
    cursor: pointer;
  }
  
  .btn-enabled {
    background-color: #a2d8df;
    color: white;
  }
  
  .btn-disabled {
    background-color: #6c757d;
    color: black;
    cursor: not-allowed;
  }
  
  .card {
    border-radius: 0.5rem;
  }
  
  .card-header {
    background-color: #a2d8df;
    font-weight: bold;
  }
  
  .card-body {
    padding: 1rem;
  }
  </style>
  