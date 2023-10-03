<template>
  <div>
    <div class="zxc">
      <h3 class="title">Boards</h3>
      <div class="my-container">
        <div v-for="(value, index) in Object.keys(boards)" :key = index>
          <h4><span>{{value}}</span></h4>
          <div v-for="(board, index) in boards[value]" :key = index>
            <router-link :to="{ name: 'board', params: {id: board.id } }" style="color: blue;">
              {{board.title}}
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BoardService from "../service/BoardService"

export default {
  name: "home",
  data: () => ({
    boards: []
  }),
  created() {
    BoardService.getAll()
      .then(res => this.boards = res.data);
  }
};
</script>

<style scoped>
a {
    text-decoration: none;
}

.zxc {
  background-color: white;
  margin-left: 200px;
  margin-right: 200px;
  /* border: solid 1px; */
  box-shadow: 2px 2px 10px rgb(218, 217, 217);
}

.title {
  margin: 10px;
  margin-left: 100px;  
}

.my-container {
  display: flex;
  justify-content: space-evenly;
  margin: 20px;
}
</style>