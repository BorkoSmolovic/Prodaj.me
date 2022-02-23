import Vue from 'vue'
import App from './App.vue'
import { BootstrapVue } from 'bootstrap-vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home.vue'
import Dashboard from '@/components/Dashboard.vue'
import AdminPanel from '@/components/AdminPanel.vue'
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import "mdi-icons/css/materialdesignicons.min.css";
import axios from 'axios'
import VueAxios from 'vue-axios'


Vue.config.productionTip = false

const routes = [
  {path: '/', name: 'Home', component: Home},
  {path: '/dashboard', name: "Dashboard", component: Dashboard, meta: {requiresAuth: true, requiresAdmin: false}},
  {path: '/adminpanel', name: "Admin Panel", component: AdminPanel, meta: {requiresAuth: true, requiresAdmin: true}}
];

const router = new VueRouter({
routes,
mode: 'history'
});
const vuetifyOptions = { };

router.beforeEach((to, from, next) => {
  let user = JSON.parse(localStorage.getItem("user"))
  if (to.matched.some(record => record.meta.requiresAuth && !record.meta.requiresAdmin)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (user==null) {
      router.push("/")
    } else {
      next() // go to wherever I'm going
    }
  } else if (to.matched.some(record => record.meta.requiresAuth && record.meta.requiresAdmin)) {
    // this route requires admin, check if admin is logged in 
    // if not, stay on current page page.
    if (user==null) {
      router.push("/");
    } else if(user.id == 1){
      next();
    } else {
      return;
    }
  } else {
    if(to.matched.some(record => record.path === '')){
      if(user!=null){
        router.push("/dashboard")
      }else{
        next()
      }
    }else{
    next() // does not require auth, make sure to always call next()!
  }
  }
})


Vue.use(BootstrapVue);
Vue.use(VueRouter);
Vue.use(VueSweetalert2);
Vue.use(Vuetify);
Vue.use(VueAxios, axios)

new Vue({
  el: '#app',
  vuetify: new Vuetify(vuetifyOptions),
  router,
  render: h => h(App),
})


