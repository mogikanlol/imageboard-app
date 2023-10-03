<template>
  <div>
    <div>
      <form ref="form">
        <textarea v-model="inputText" :rules="[rules.textarea]" class="post-edit-area"></textarea>
        <!-- TODO: VALIDATION -->
        <button @click.prevent="$emit('add-post', inputText)" class="zxc">Send</button>
      </form>
    </div>
  </div> 
</template>

<script>
export default {
  data: () => ({
    isFormValid: false,
    inputText: '',
    rules: {
      textarea: v => (v || '').length > 0 || ''
    }
  }),
  methods: {
    resetForm() {
      this.$refs.form.reset();
    }
  }
}
</script>

<style scoped>

.post-edit-area {
  width: 100%;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 15px;
  padding-bottom: 15px;
  background-color: #e9e6e6;
  border: none;
}
.post-edit-area:hover {
  background-color: #08080823;
}

.zxc {
  margin-top: 20px;
  margin-bottom: 20px;
  background-color: #e9e6e6;
  padding: 5px;
  padding-left: 20px;
  padding-right: 20px;
  color: black;
  border-radius: 5px;
  border: none;
}
.zxc:hover {
  background-color: #08080823;
}
</style>