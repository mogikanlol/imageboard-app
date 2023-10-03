<template>
  <div>

    <div class="post">
      <div class="post-top">
        <div>
          #{{localPost.id}}
        </div>
        <div>
          <span @click="editPost()" class="post-top-icon">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="currentColor" d="M20.71 7.04c.39-.39.39-1.04 0-1.41l-2.34-2.34c-.37-.39-1.02-.39-1.41 0l-1.84 1.83l3.75 3.75M3 17.25V21h3.75L17.81 9.93l-3.75-3.75L3 17.25Z"/></svg>
          </span>
          <span @click="deletePost()" class="post-top-icon">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path fill="currentColor" d="M19 6.41L17.59 5L12 10.59L6.41 5L5 6.41L10.59 12L5 17.59L6.41 19L12 13.41L17.59 19L19 17.59L13.41 12L19 6.41Z"/></svg>
          </span>
        </div>
      </div>

      <div class="post-content" v-if="editable == false">
        {{localPost.content}}
      </div>
      <div v-if="editable == true" class="post-editable">
        <form>
          <textarea v-model="localPost.content" class="post-edit-area"></textarea>
        </form>
        <button class="edit-button" @click="changePost()">Save</button>
        <button class="edit-button" @click="discardChanges()">Discard</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    editable: false,
    localPost: {}
  }),
  props: {
    post: Object
  },
  created() {
    this.localPost = {
      id: this.post.id,
      content: this.post.content
    }
  },
  watch: {
    post(newPost) {
      this.localPost = {
        id: newPost.id,
        content: newPost.content
      }
    }
  },
  methods: {
    deletePost() {
      if (confirm("Are you sure you want to delete this post?")) {
        this.$emit("delete-post", this.post.id);
      }
    },

    editPost() {
      this.editable = true;
    },

    changePost() {
      this.$emit('change-post', this.localPost);
      this.editable = false;
    },

    discardChanges() {
      this.localPost.content = this.post.content;
      this.editable = false;
    }
  }
}
</script>


<style scoped>

.post {
  background-color: white;
  /* border: solid 1px; */
  box-shadow: 2px 2px 10px rgb(218, 217, 217);
}

.post-top {
  display: flex;
  justify-content: space-between;
  margin: 5px;
}

.post-top-icon:hover {
  cursor: pointer;
}

.post-content {
  margin-left: 15px;
  margin-right: 15px;
  margin-bottom: 15px;
}

.post-editable {
  margin: 20px;
}

.post-edit-area {
  width: 100%;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 15px;
  padding-bottom: 15px;
  background-color: #f5f5f5;
}
.post-edit-area:hover {
  background-color: #08080823;
}

.edit-button {
  text-decoration: none;
  color: white;

  border: none;

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