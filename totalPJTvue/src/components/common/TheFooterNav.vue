<template>
  <div style="background-color: #a2d8df" class="container fixed-bottom py-1">
    <footer>
      <nav class="d-flex justify-content-evenly align-items-center">
        <div>
          <RouterLink :to="{ name: 'search' }"
            ><i class="bi bi-search" style="font-size: 50px; color: white"></i>
          </RouterLink>
        </div>
        <div>
          <RouterLink :to="{ name: 'favorite' }"
            ><i
              class="bi bi-bookmark-heart"
              style="font-size: 50px; color: white"
            ></i
          ></RouterLink>
        </div>
        <div>
          <RouterLink to="/"
            ><i
              class="bi bi-house-fill"
              style="font-size: 80px; color: white"
            ></i
          ></RouterLink>
        </div>
        <div>
          <RouterLink :to="{ name: 'calendar' }"
            ><i
              class="bi bi-calendar-check"
              style="font-size: 50px; color: white"
            ></i
          ></RouterLink>
        </div>
        <div>
          <RouterLink :to="{ name: 'myPage' }"
            ><i
              class="bi bi-person-fill"
              style="font-size: 50px; color: white"
            ></i
          ></RouterLink>
        </div>
      </nav>
    </footer>
  </div>
</template>

<script setup></script>

<style scoped>
/* footer nav {
  margin: 0;
  padding: 0;
} */
</style>
