<template>
  <div>
    <div class="maw">
      <button class="button" @click="showNewThreadForm = !showNewThreadForm;">{{!showNewThreadForm ? '[Start a New Thread]' : '[Close Form]'}}</button>
      <div v-if="showNewThreadForm">
        <NewThreadForm @add-thread="addThread" ref="childForm"/>
      </div>
      <ThreadPreview 
        v-for="(value, index) in board.threads" 
        :key = index 
        :thread= value
      ></ThreadPreview>
    </div>
  </div>
</template>

<script>
import ThreadPreview from "../components/board/ThreadPreview.vue"
import NewThreadForm from "../components/board/NewThreadForm.vue"
import BoardService from "../service/BoardService"

export default {
  name: "board",
  components: { ThreadPreview, NewThreadForm },
  data: () => ({
    board: {},
    showNewThreadForm: false
  }),
  created() {
    BoardService.getById(this.$route.params.id)
      .then(res => this.board = res.data);
  },
  methods: {
    addThread(thread) {
      BoardService.addNewThread(this.$route.params.id, thread)
        .then(res => this.board.threads.push(res.data));
        
      this.$refs.childForm.resetForm();
    }
  }
}
</script>

<style scoped>

#newThreadForm {
  width: 800px
}

.maw {
  /* border: solid 1px; */
  margin-left: 260px;
  margin-right: 260px;
}


.button {
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

  margin: 0 auto;
  margin-top: 10px;
  margin-bottom: 10px; 
  display: block;

}


</style>