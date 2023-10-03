<template>
  <div>
  <div class="nav">
    <div>
      <router-link to="/" class="zxc">
            The Header Home
      </router-link>
    </div>
    <div>
      <span class="zxc">Sign In</span>
    </div>
  </div>
</div>
</template>

<script>
export default {
    
}
</script>

<style scoped>
.nav {
  display: flex;
  justify-content: space-between;
  background-color: white;
  /* border: solid 1px; */
  box-shadow: 2px 2px 10px rgb(218, 217, 217);
  padding-top: 10px;
  padding-bottom: 10px;
}

.zxc {
  text-decoration: none;
  margin: 20px;
  margin-bottom: 20px;
  background-color: #f5f5f5;
  padding: 5px;
  padding-left: 20px;
  padding-right: 20px;
  color: black;
  border-radius: 5px;
}
.zxc:hover {
  background-color: #08080823;
}

</style>