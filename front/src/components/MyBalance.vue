<template>
  <div class="balance">
    <h2>БАЛАНС: {{$store.getters.balance}}</h2>
    <my-reload @click="onClick()"/>
  </div>
</template>

<script>
import MyReload from "./MyReload";

export default {
  name: 'MyBalance',
  components: {
    'my-reload': MyReload
  },
  methods: {
    onClick() {
      this.$store.dispatch('getBalance');
    }
  },
  beforeCreate() {
    this.$store.dispatch('getBalance');
  }
}
</script>

<style scoped>
.balance {
  border: 1px solid black;
  margin: 10px;
  width: 360px;
  border-radius: 5px;
  padding: 0 20px;
  position: relative;
}
</style>
