<template>
  <div>
    <div class="post">
      <div class="post-header">
        <h3>{{ thread.title }}</h3>
      </div>
      <div>
        <img :src=thread.imageUrl class="post-image">
      </div>
      <div class="post-content">
        {{ thread.content }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    thread: {}
  }
}
</script>

<style scoped>

.post {
  /* border: solid 1px; */
  background-color: white;
  box-shadow: 2px 2px 10px rgb(218, 217, 217);
}

.post-header {
  margin: 20px;
}

.post-content {
  margin: 20px;
}

.post-image {
  object-fit: contain;
  height: 400px; 
  width: 400px;
}

</style>