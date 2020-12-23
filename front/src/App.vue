<template>
  <div>
    <my-method msg="Выплата" class="payout"/>
    <my-method msg="Баланс" @click="balance"/>
    <my-method msg="Проверка статуса" @click="status"/>
  </div>
</template>

<script>
import MyMethod from './components/MyMethod.vue'

export default {
  name: 'App',
  components: {
    'my-method': MyMethod
  },
  methods: {
    balance () {
      this.$store.dispatch('getBalance');
      alert('Баланс: ' + this.$store.getters.balance)
    },
    status () {
      this.$store.dispatch('checkStatus');
      alert('Статус: ' + this.$store.getters.status)
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  justify-content: center;
  display: flex;
}
</style>
