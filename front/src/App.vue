<template>
  <div>
    <my-balance/>
    <my-payout/>
    <my-status/>
  </div>
</template>

<script>
import MyPayout from './components/MyPayout.vue'
import MyBalance from './components/MyBalance'
import MyStatus from './components/MyStatus'

export default {
  name: 'App',
  components: {
    'my-balance': MyBalance,
    'my-status': MyStatus,
    'my-payout': MyPayout
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Jura:wght@300&display=swap');

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
h2, h4 {
  font-family: 'Jura', sans-serif;
}
h2 {
  text-align: left;
}
</style>
