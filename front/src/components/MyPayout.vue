<template>
  <div class="payout">
    <h2>ВЫПЛАТА</h2>
    <div class="payout-form">
      <div class="pan-wrapper">
        <h4>Номер карты</h4>
        <label>
          <input
            type="text"
            id="pan"
            v-model="pan"
            placeholder="0000 0000 0000 0000"/>
        </label>
      </div>
      <div class="amount-wrapper">
        <h4>Сумма</h4>
        <label>
          <input type="text"
                 v-model="amount"
                 id="amount"
                 placeholder="сумма"/>
        </label>
      </div>
      <div class="btn-wrapper">
        <div class="btn" @click="onClick"><b>Вывести</b></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyPayout',
  data() {
    return {
      pan: null,
      amount: null
    }
  },
  methods: {
    onClick() {
      if (this.pan) {
        if (this.pan.length === 19 && this.amount.length > 0) {
          this.$store.dispatch('sendPayout', {
            pan: this.pan,
            amount: this.amount
          })
        }
      } else {
        alert('Введены неверные данные')
        console.log(this.pan, this.amount)
      }
    }
  },
  mounted() {
    document.getElementById('pan').addEventListener('keyup', (e) => {
      let target = e.target;
      let position = target.selectionEnd;
      let length = target.value.length;
      target.value = target.value.replace(/[^\dA-Z]/g, '').replace(/(.{4})/g, '$1 ').trim();
      target.selectionEnd = position += ((target.value.charAt(position - 1) === ' ' && target.value.charAt(length - 1) === ' ' && length !== target.value.length) ? 1 : 0);
      if (length === 20) {
        target.value = target.value.substr(0, 19)
        this.pan = target.value.substr(0, 19)
      }
    });

    document.getElementById('amount').onkeyup = () => {
      let val_input = document.getElementById('amount').value;
      val_input = val_input.replace(/\s/g, '');

      if (!val_input) return;
      let hasDotAtTheEnd = val_input.endsWith('.');

      val_input = Number.parseFloat(val_input)
      if (isNaN(val_input)) return

      document.getElementById('amount').value = val_input.toLocaleString('ru').replace(',', '.') + (hasDotAtTheEnd ? '.' : '');
      this.amount = document.getElementById('amount').value;
    }
  }
}
</script>

<style scoped>
.payout {
  border: 1px solid black;
  margin: 10px;
  width: 360px;
  border-radius: 5px;
  padding: 0 20px;
}

h4 {
   text-align: left;
   margin: 0 0 0 40px;
 }

input {
  height: 40px;
  font-size: 20px;
  border: 0;
  border-bottom: 1px solid #2c3e50;
  outline: none;
  -moz-appearance: textfield;
  width: 196px;
  padding: 0 10px;
}
input::-webkit-inner-spin-button {
  display: none;
}
#amount {
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
  font-family: 'Jura', sans-serif;
}
.btn:hover {
  background-color: #3d566e;
}
</style>
