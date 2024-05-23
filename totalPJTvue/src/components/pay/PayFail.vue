<template>
  <div class="container">
    <div
      id="info"
      class="d-flex flex-column justify-content-center align-items-center"
      style="margin-top: 50%"
    >
      <img
        style="width: 100px"
        src="https://static.toss.im/lotties/error-spot-no-loop-space-apng.png"
      />
      <h2>결제를 실패했어요</h2>

      <div style="margin-top: 50px">
        <div id="message">
          {{ this.$route.query.message }}
        </div>
      </div>
      <!-- <div style="margin-top: 10px">
        <div><b>에러코드</b></div>
        <div id="code">
          {{ this.$route.query.code }}
        </div>
      </div> -->
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.container {
  margin-top: 66px;
  margin-bottom: 90.5px;
}
</style>
