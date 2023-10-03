<template>
  <div>
    <Navbar />
    <router-view></router-view>
  </div>
</template>

<script>
import Navbar from "./components/Navbar.vue";

export default {
  name: "App",
  components: { Navbar }
}
</script>

<style>
body {
  font-family: "Roboto", sans-serif;
  line-height: 1.5;
  font-size: 16px;
}
</style>
