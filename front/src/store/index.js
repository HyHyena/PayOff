import { createStore } from 'vuex'
import payout          from './modules/payout'
import balance         from './modules/balance'
import status          from './modules/status'


export default createStore({
  modules: {
    payout,
    balance,
    status
  }
})
