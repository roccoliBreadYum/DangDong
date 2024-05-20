<template>
    <div class="container">
        <!-- 가게 이름 -->
        <div id="store-title">
            {{ store.storeDetail.storeName }}
        </div>
        
        <!-- 정기권 -->
        <div id="season-ticket">
            <h3 id="ticket-title">정기권</h3>
            <div class="d-flex flex-wrap">
                <div v-for="ticket in store.storeTicketList" :key="ticket.ticketId">
                    <div v-if="ticket.category === 1">
                        <button class="ticket btn btn-white p-2">
                            <img src="@/assets/Transparent_Ticket.png" id="ticket-img">
                            <div class="main_image_text"><div id="ticket-name"> {{ ticket.name }} </div><div id="ticket-price"> ₩{{ money(ticket.price) }}</div> </div>
                        </button>
                    </div>
                </div>
                <div v-if="cntSeasonTicket === 0">
                    <div class="no-ticket">판매중인 이용권이 없습니다</div>
                </div>
            </div>
        </div>
        <!-- 다회권 -->
        <div id="multiple-ticket">
            <h3 id="ticket-title">다회권</h3>
            <div class="d-flex flex-wrap">
                <div v-for="ticket in store.storeTicketList" :key="ticket.ticketId">
                    <div v-if="ticket.category === 0">
                        <button class="ticket btn btn-white p-2">
                            <img src="@/assets/Transparent_Ticket.png" id="ticket-img">
                            <div class="main_image_text"><div id="ticket-name"> {{ ticket.name }} </div><div id="ticket-price"> ₩{{ money(ticket.price) }}</div> </div>
                        </button>
                    </div>
                </div>
                <div v-if="cntMultipleTicket === 0">
                    <div class="no-ticket">판매중인 이용권이 없습니다</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue';
import { useStoreStore } from '@/stores/store';
import { useRoute, useRouter } from 'vue-router';


const store = useStoreStore()
const route = useRoute()
let cntSeasonTicket = ref(0)
let cntMultipleTicket = ref(0)

const countTicket = () => {
    cntSeasonTicket.value = 0;
    cntMultipleTicket.value = 0;
    for(let i = 0; i < store.storeTicketList.length; i++){
        if(store.storeTicketList[i].category === 0){
            cntMultipleTicket.value++;
        } else{
            cntSeasonTicket.value++;
        }
    }
    console.log(cntMultipleTicket.value)
    console.log(cntSeasonTicket.value)
}


const money = (num) => {
    return num.toLocaleString('ko-KR')
}

onMounted(() => {
    store.getStoreTicketList(route.params.storeId)
    countTicket()
})

watch(
    () => store.storeTicketList,
    (newList, oldList) => {
        countTicket(); // 데이터 변경 시 호출
    }
);

</script>

<style scoped>
.ticket{
    position: relative;
}
.main_image_text {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate( -50%, -50% );
    color: black;
}

#ticket-img{
    width: 10rem;
    height: 5rem;
}
#ticket-title{
    margin-top: 1rem;
    text-align: center;
    font-weight: bolder
}
#ticket-name{
    font-size: 0.8rem;
    margin-bottom: 0.5rem;
}
#ticket-price{
    font-weight:bold;
}
#store-title{
    font-size: 1.5rem;
}
.no-ticket{
    font-size: 1.3rem;
    text-align: center;
}
</style>