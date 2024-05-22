<template>
  <div class="container">
    <div class="box_section">
      <!-- 결제 UI -->
      <div id="payment-method"></div>
      <!-- 이용약관 UI -->
      <div id="agreement"></div>
      <!-- 쿠폰 체크박스
      <div style="padding-left: 25px">
        <div class="checkable typography--p">
          <label for="coupon-box" class="checkable__label typography--regular">
            <input
              :disabled="!inputEnabled"
              @change="updateAmount"
              id="coupon-box"
              class="checkable__input"
              type="checkbox"
              aria-checked="true"
            /><span class="checkable__label-text">5,000원 쿠폰 적용</span>
          </label>
        </div>
      </div> -->
      <!-- 결제하기 버튼 -->
      <div class="d-flex justify-content-center" id="btn-area">
        <button
          :disabled="!inputEnabled"
          @click="requestPayment"
          class="button btn"
          id="payment-button"
          style="
            margin-top: 30px;
            font-size: large;
            font-weight: 600;
            background-color: #a2d8df;
            color: #696969;
            border: 2px solid #696969;
          "
        >
          결제하기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { useUserStore } from "@/stores/user";
import { useStoreStore } from "@/stores/store";
import { useAuthStore } from "@/stores/auth";
import { loadPaymentWidget, ANONYMOUS } from "@tosspayments/payment-widget-sdk";
import { nanoid } from "nanoid";
import { onMounted, ref, computed } from "vue";
import { useRoute, useRouter } from "vue-router";

export default {
  data() {
    return {
      paymentWidget: null,
      paymentMethodWidget: null,
      // TODO: clientKey는 개발자센터의 결제위젯 연동 키 > 클라이언트 키로 바꾸세요.
      // TODO: customerKey는 구매자와 1:1 관계로 무작위한 고유값을 생성하세요.
      clientKey: "test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm",
      customerKey: nanoid(),
      amount: null,
      inputEnabled: false,
      userStore: useUserStore(),
      storeStore: useStoreStore(),
      authStore: useAuthStore(),
      route: useRoute(),
      router: useRouter(),
    };
  },
  methods: {
    async requestPayment() {
      try {
        if (this.paymentWidget) {
          // ------ '결제하기' 버튼 누르면 결제창 띄우기 ------
          // 결제를 요청하기 전에 orderId, amount를 서버에 저장하세요.
          // 결제 과정에서 악의적으로 결제 금액이 바뀌는 것을 확인하는 용도입니다.
          // @docs https://docs.tosspayments.com/reference/widget-sdk#requestpayment결제-정보
          await this.paymentWidget.requestPayment({
            orderId: nanoid(),
            orderName: this.storeStore.forBuyTicketInfo.ticketId,
            customerName: this.userStore.getUserInfo.name,
            customerEmail: this.userStore.getUserInfo.email,
            customerMobilePhone: "01012341234",
            successUrl: `${window.location.origin}/pay/success`,
            failUrl: `${window.location.origin}/pay/fail`,
          });
        }
      } catch (error) {
        // 에러 처리하기 
        console.error(error);
      }
    }, 
    // async updateAmount() {
    //   const coupon = document.getElementById("coupon-box");
    //   // ------  결제 금액 업데이트 ------
    //   // @docs https://docs.tosspayments.com/reference/widget-sdk#updateamount결제-금액
    //   if (coupon.checked) {
    //     this.paymentMethodWidget.updateAmount(this.amount - 5000);
    //   } else {
    //     this.paymentMethodWidget.updateAmount(this.amount);
    //   }
    // },
  },
  async mounted() {
    // console.log(this.authStore.getLoginUserId());
    this.userStore.getUserInfo(this.authStore.getLoginUserId());
    await this.storeStore.getTicket(this.route.params.ticketId);
    // console.log(`금액 + ${this.storeStore.forBuyTicketInfo.price}`);
    this.amount = this.storeStore.forBuyTicketInfo.price;

    // ------  결제위젯 초기화 ------
    // @docs https://docs.tosspayments.com/reference/widget-sdk#sdk-설치-및-초기화
    this.paymentWidget = await loadPaymentWidget(this.clientKey, ANONYMOUS);

    // ------  결제 UI 렌더링 ------
    // @docs https://docs.tosspayments.com/reference/widget-sdk#renderpaymentmethods선택자-결제-금액-옵션
    this.paymentMethodWidget = this.paymentWidget.renderPaymentMethods(
      "#payment-method",
      { value: this.amount },
      { variantKey: "DEFAULT" }
    );
    // ------  이용약관 UI 렌더링 ------
    // @docs https://docs.tosspayments.com/reference/widget-sdk#renderagreement선택자-옵션
    this.paymentWidget.renderAgreement("#agreement", {
      variantKey: "AGREEMENT",
    });

    this.paymentMethodWidget.on("ready", () => {
      this.inputEnabled = true;
    });
  },
};
</script>

<style scoped>
.container {
  margin-top: 15%;
  margin-bottom: 120px;
}
</style>
