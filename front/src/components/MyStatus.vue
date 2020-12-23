<template>
  <div class="status">
    <h2>СТАТУС ТРАНЗАКЦИИ</h2>
    <h4>{{$store.getters.status}}</h4>
    <my-reload @click="onClick()"/>
  </div>
</template>

<script>
import MyReload from "./MyReload";


export default {
  name: 'MyStatus',
  components: {
    'my-reload': MyReload
  },
  methods: {
    onClick() {
      this.$store.dispatch('checkStatus');
    }
  }
}
</script>

<style scoped>
.status {
  border: 1px solid black;
  margin: 10px;
  width: 360px;
  border-radius: 5px;
  padding: 0 20px;
  position: relative;
}
</style>
