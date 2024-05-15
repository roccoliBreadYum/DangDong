<template>
  <div style="background-color: #a2d8df" class="container fixed-bottom py-1">
    <footer>
      <nav class="d-flex justify-content-evenly align-items-center">
        <div>
          <RouterLink to="/"
            ><i
              class="bi bi-house-fill"
              style="font-size: 45px; color: white"
            ></i>
            <p>홈</p>
          </RouterLink>
        </div>
        <div>
          <RouterLink :to="{ name: 'search' }"
            ><i class="bi bi-search" style="font-size: 45px; color: white"></i>
            <p>검색</p>
          </RouterLink>
        </div>
        <div>
          <RouterLink :to="{ name: 'favorite' }"
            ><i
              class="bi bi-bookmark-heart"
              style="font-size: 45px; color: white"
            ></i>
            <p>즐겨찾기</p>
          </RouterLink>
        </div>
        <div>
          <RouterLink :to="{ name: 'calendar' }"
            ><i
              class="bi bi-calendar-check"
              style="font-size: 45px; color: white"
            ></i>
            <p>일정</p>
          </RouterLink>
        </div>
        <div>
          <RouterLink :to="{ name: 'myPage' }"
            ><i
              class="bi bi-person-fill"
              style="font-size: 45px; color: white"
            ></i>
            <p>마이페이지</p>
          </RouterLink>
        </div>
      </nav>
    </footer>
  </div>
</template>

<script setup></script>

<style scoped>
footer nav div a.router-link-exact-active i::before,
nav div a.router-link-exact-active p {
  color: gray;
}

nav div {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

nav div a {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: white;
}

nav div p {
  margin: 0;
  color: white;
  text-align: center;
  font-size: 10px;
}
</style>
