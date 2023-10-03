import axios from 'axios'

export default class ThreadService {

  url = "http://localhost:8081"

  static getById (id) {
    return axios.get("http://localhost:8081" + "/threads/" + id);
  }

  static addPost(post) {
    return axios.post("http://localhost:8081" + "/posts", post);
  }

  static deletePost(postId) {
    return axios.delete("http://localhost:8081" + "/posts/" + postId);
  }

  static updatePost(post) { 
    return axios.patch("http://localhost:8081" +  "/posts/" + post.id, {
      content: post.content
    })
  }
}