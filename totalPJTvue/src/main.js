import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/js/bootstrap.min.js";


import { createApp } from "vue";
import { createPinia } from "pinia";

import VCalendar from 'v-calendar';
import 'v-calendar/style.css';

import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

import App from "./App.vue";
import router from "./router";

const app = createApp(App);
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate)


app.use(pinia);
app.use(router);
app.use(VCalendar, {});

app.mount("#app");

