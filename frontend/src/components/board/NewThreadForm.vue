<template>
  <div>
    <div>
      <form class="new-thread-form" ref="form">
        <input type="text" placeholder="Title" class="title-input" v-model="title" :rules="[rules.text]">
        <textarea placeholder="Content" class="text-area" v-model="content" :rules="[rules.text]"></textarea>
        <input type="file" placeholder="Image" accept="image/*" @change="onImageChange">
        <!-- TODO: VALIDATION -->
        <button class="zxc" @click.prevent="addThread()">Send</button>
      </form>

    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    title: '',
    content: '',
    image: null,
    isFormValid: false,
    rules: {
      text: v => (v || '').length > 0 || ''
    }
  }),
  methods: {
    addThread() {
      const thread = {
        title: this.title,
        content: this.content,
        image: this.image
      };
      this.$emit('add-thread', thread);
    },
    resetForm() {
      this.$refs.form.reset();
    },
    onImageChange(e) {
      this.image = e.target.files[0];
      // console.log(this.image)
    }
  }
}
</script>

<style scoped>

.new-thread-form {
  /* border: solid 1px; */

  box-shadow: 2px 2px 10px rgb(218, 217, 217);
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
}

.title-input {
  /* margin-top: 20px; */
  margin-bottom: 20px;
  background-color: #e9e6e6;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 15px;
  padding-bottom: 15px;
  border: none;
}

.title-input:hover {
  background-color: #08080823;
}


.zxc {
  margin-top: 20px;
  /* margin-bottom: 20px; */
  background-color: #e9e6e6;
  padding: 5px;
  padding-left: 20px;
  padding-right: 20px;
  color: black;
  border-radius: 5px;

  width: 100px;
  border: none;
}
.zxc:hover {
  background-color: #08080823;
}

.zxc:disabled:hover {
  background-color: #e9e6e6;
}

.text-area {
  width: inherit;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 15px;
  padding-bottom: 15px;
  background-color: #e9e6e6;
  border: none;
}
.text-area:hover {
  background-color: #08080823;
}

</style>