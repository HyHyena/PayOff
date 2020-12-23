export default{
    actions: {
        getBalance (ctx) {
            ctx.commit('setBalance')
        }
    },
    mutations: {
        setBalance (state) {
            state.balance = 120
        }
    },
    state: {
        balance: null
    },
    getters: {
        balance: (state) => state.balance
    }
}