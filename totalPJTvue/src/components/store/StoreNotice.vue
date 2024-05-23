<template>
  <div id="square" style="margin-top: 20px">
    <div
      style="
        text-align: center;
        margin-bottom: 0%;
        font-weight: 600;
        font-size: larger;
      "
    >
      공지사항
    </div>
    <div style="text-align: right">
      <button id="btn-more">더 보기 ></button>
    </div>
    <div id="notice" class="p-2">
      <div id="notice-head" class="d-flex justify-content-between">
        <div
          class="p-2"
          style="
            margin: 0px;
            width: 16rem;
            text-align: center;
            margin-top: auto;
            margin-bottom: auto;
            font-size: 17px;
            font-weight: 600;
          "
        >
          채널 오픈 안내 궁시렁 궁시렁
        </div>
        <div class="p-2" style="margin: 0px">2024-05-10</div>
      </div>
      <hr style="margin: 0px" />
      <div id="notice-body" style="padding: 0.5rem 0 0 0.5rem">
        <p style="font-size: small">
          사실 오픈 안함 ㅎ <br />추후 공지사항 DB만들면 채워질 예정 :D
        </p>
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
  border-radius: 20px;
}

#btn-more {
  background-color: white;
  border: none;
}
</style>
