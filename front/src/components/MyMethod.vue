<template>
  <div class="method">
    <h2>{{ msg }}</h2>
    <my-payout v-if="isPayout()"/>
  </div>
</template>

<script>
import MyPayout from '../components/MyPayout.vue'

export default {
  name: 'MyMethod',
  components: {
    'my-payout': MyPayout
  },
  props: {
    msg: String
  },
  methods: {
    isPayout() {
      return this.$.attrs.class === 'payout';
    }
  }
}
</script>

<style scoped>
.method {
  border: 1px solid black;
  margin: 10px;
  cursor: pointer;
  width: 400px;
  border-radius: 5px;
}

.payout {
  cursor: default;
}
</style>
