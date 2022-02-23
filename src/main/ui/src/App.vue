<template>
  <v-app id="app">
    <Header />
    <router-view></router-view>
    <Footer />
  </v-app>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import Header from './components/Header.vue'
import Footer from './components/Footer.vue'

export default {
  name: 'App',
  components: {
    Header,
    Footer
  }
}
</script>

<style>
  @import './assets/styles/global.css';
</style>