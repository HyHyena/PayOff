<template>
  <div class="payout-form">
    <div class="pan-wrapper">
      <h5>Номер карты</h5>
      <input type="text" v-model="pan">
    </div>
    <div class="amount-wrapper">
      <h5>Сумма</h5>
      <input type="text" v-model="amount">
    </div>
    <div class="btn-wrapper">
      <div class="btn" @click="onClick">Вывести</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyPayout',
  data() {
    return {
      pan: '',
      amount: ''
    }
  },
  methods: {
    onClick() {
      if (this.pan && this.amount) {
        this.$store.dispatch('sendPayout', {
          pan: this.pan,
          amount: this.amount
        })
        alert('Статус выплат: ' + this.$store.getters.statusPayout)
      }
    }
  }
}
</script>

<style scoped>
h5 {
  text-align: left;
  margin: 0 0 0 40px;
}

input {
  height: 40px;
  font-size: 20px;
  border: 0;
  border-bottom: 1px solid #2c3e50;
  outline: none;
  text-align: center;
}

.payout-form {
  padding: 0 10px 10px;
}
.payout-form div{
  margin: 10px;
}
.payout-form .btn{
  margin: 0;
}
.payout-form .amount-wrapper{
  margin-top: 20px;
}


.btn-wrapper {
  justify-content: center;
  display: flex;
}

.btn {
  color: white;
  width: 150px;
  height: 20px;
  background-color: #2c3e50;
  border-radius: 5px;
  padding: 10px;
  transition: .3s;
  cursor: pointer;
}
.btn:hover {
  background-color: #3d566e;
}
</style>