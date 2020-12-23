import { createStore } from 'vuex'
import payout          from './modules/payout'
import balance         from './modules/balance'


export default createStore({
  modules: {
    payout,
    balance
  }
})
