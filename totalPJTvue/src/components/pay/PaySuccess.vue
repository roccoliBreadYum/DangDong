<template>
  <div class="container">
    <!-- 결제 성공 시 -->
    <section v-if="confirmed">
      <div
        id="info"
        class="d-flex flex-column justify-content-center align-items-center"
        style="margin-top: 50%"
      >
        <img
          style="width: 100px"
          src="https://static.toss.im/illusts/check-blue-spot-ending-frame.png"
        />
        <h2>결제를 완료했어요</h2>

        <div style="margin-top: 50px" class="d-flex flex-column">
          <div><b>결제금액</b></div>
          <div id="amount">{{ jsonData.totalAmount }}원</div>
        </div>
        <div
          style="margin-top: 30px"
          class="d-flex flex-column align-items-center"
        >
          <div><b>주문번호</b></div>
          <div id="orderId">
            {{ jsonData.orderId }}
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { confirmPayment } from "@/confirmPayment";
import axios from "axios";
import { useticketStore } from "@/stores/ticket";
import { useAuthStore } from "@/stores/auth";

export default {
  setup() {
    const route = useRoute();
    const router = useRouter();
    const confirmed = ref(false);
    const jsonData = ref(null);

    onMounted(async () => {
      const requestData = {
        orderId: route.query.orderId,
        amount: route.query.amount,
        paymentKey: route.query.paymentKey,
      };

      async function confirm() {
        try {
          const { response, json } = await confirmPayment(requestData);
          console.log(json);
          if (!response.ok) {
            router.push(`/pay/fail?message=${json.message}&code=${json.code}`);
          } else {
            confirmed.value = true;
            jsonData.value = json;

            const ticketStore = useticketStore()
            const authStore = useAuthStore()
      
            // console.log(jsonData.value.orderName)
            ticketStore.createTicket(jsonData.value.orderName)
          }
        } catch (error) {}
      }

      confirm();
    });

    return {
      confirmed,
      jsonData,
    };
  },
};
</script>

<style scoped>
.container {
  margin-top: 66px;
  margin-bottom: 90.5px;
}
</style>
