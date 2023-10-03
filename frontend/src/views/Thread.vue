<template>
  <div>

    <div class="maw">
      <div>
        <button class="button" @click="goBack()">Back</button>
        <button class="button" @click="goBottom()">Bottom</button>
      </div>
      <div>
        <ThreadFirstPost 
          class="mb-2" 
          :thread=thread 
        />
      </div>
      <div>
        <ThreadPost 
          v-for="(post, index) in thread.posts" 
          :key=index
          :post=post
          @delete-post="deletePost(index, $event)"
          @change-post="changePost(index, $event)"
        />
      </div>
      <div class="new-post-form">
        <NewPostForm @add-post="addPost" ref="childForm"  />
      </div>
      <div>
        <button class="button" @click="goBack()">Back</button>
        <button class="button" @click="goTop()">Top</button>
      </div>
    </div>
  </div>
</template>

<script>
import NewPostForm from "../components/thread/NewPostForm.vue"
import ThreadService from "../service/ThreadService"
import ThreadPost from "../components/thread/ThreadPost.vue"
import ThreadFirstPost from "../components/thread/ThreadFirstPost.vue"

export default {
  name: "thread",
  components: { NewPostForm, ThreadPost, ThreadFirstPost },
  data: () => ({
    thread: {}
  }),
  methods: {
    addPost(inputText) {
      const post = {
        threadId: this.thread.id,
        content: inputText
      };

      ThreadService.addPost(post)
        .then(res => this.thread.posts.push(res.data));

      this.$refs.childForm.resetForm();
    },

    deletePost(index, postId) {
      ThreadService.deletePost(postId)
        .then(() => {
          this.thread.posts.splice(index, 1);
        });
    },

    changePost(index, post) {
      ThreadService.updatePost(post)
        .then(res => {
          this.thread.posts.splice(index, 1, res.data);
        });
    },

    goTop() {
      window.scrollTo(0,0);
    },

    goBottom() {
      window.scrollTo(0,document.body.scrollHeight);
    },

    goBack() {
      this.$router.go(-1);
    }
  },
  created() {
    ThreadService.getById(this.$route.params.id)
      .then(res => this.thread = res.data);
  }
}
</script>

<style scoped>

.maw {
  /* border: solid 1px; */
  margin-left: 260px;
  margin-right: 260px;
}

.button {

  margin-left: 20px;
  margin-bottom: 10px;
  margin-top: 10px;
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

  border: none;
}

.button:hover {
  cursor: pointer;
}

.new-post-form {
  margin-top: 10px;
}

</style>