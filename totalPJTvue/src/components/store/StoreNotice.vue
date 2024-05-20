<template>
  <div id="square">
    <h2 style="text-align: center; margin-bottom: 0%">공지사항</h2>
    <div style="text-align: right">
      <button id="btn-more">더 보기 ></button>
    </div>
    <div id="notice">
      <div id="notice-head" class="d-flex justify-content-between">
        <h5 class="p-2" style="margin: 0px; width: 16rem">
          채널 오픈 안내 궁시렁 궁시렁
        </h5>
        <h5 class="p-2" style="margin: 0px">2024-05-10</h5>
      </div>
      <hr style="margin: 0px" />
      <div id="notice-body" style="padding: 0.5rem 0 0 0.5rem">
        <p>사실 오픈 안함 ㅎ <br />추후 공지사항 DB만들면 채워질 예정 :D</p>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
#notice {
  box-shadow: inset;
  border: 0.5px solid;
  width: 24.5rem;
  height: 14rem;
}

#btn-more {
  background-color: white;
  border: none;
}
</style>
