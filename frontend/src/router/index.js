import Home from "../views/Home.vue";
import Board from "../views/Board.vue";
import Thread from "../views/Thread.vue";
import { createRouter, createWebHistory } from "vue-router";


const routes = [
  {
    path: "/",
    name: "home",
    component: Home
  },
  {
    path: "/boards/:id",
    name: "board",
    component: Board
  },
  {
    path: "/threads/:id",
    name: "thread",
    component: Thread
  }
];

const router = new createRouter({
  history: createWebHistory(),
  routes: routes
});

export default router;
