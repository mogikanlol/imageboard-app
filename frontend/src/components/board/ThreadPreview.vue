<template>
  <div>

    <div class="thread-preview">
      <div class="header">{{thread.title}}</div>
      <div class="main">
        <!-- <img width="auto" height="200px" :src=thread.imageUrl> -->
        <img class="image-preview" :src=thread.imageUrl>
        <div>{{ thread.content }}</div>
        <div class="button">
          <router-link :to="{name: 'thread', params: {id: thread.id} }" class="wtf">
              In The Thread
          </router-link>
        </div>
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

.thread-preview {
  background-color: white;
  /* border: solid 1px; */
  box-shadow: 2px 2px 10px rgb(218, 217, 217);
  padding: 20px;
  margin-bottom: 10px;
}

.header {
  font-size: 18px;
  margin-bottom: 20px;
}

.image-preview {
  object-fit: cover;
  width: 100px;
  height: 100px;
}

.button {
  margin-top: 10px;
}

.wtf {
  text-decoration: none;
  color: white;


  color: white;
  border-radius: 5px;
  padding-top: 7px;
  padding-bottom: 7px;
  padding-left: 15px;
  padding-right: 15px;
  width: fit-content;
  background-color: #1976d2;
  box-shadow: 1px 1px 1px grey;
}


</style>