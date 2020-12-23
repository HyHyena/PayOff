export default{
    actions: {
        getBalance (ctx) {
            ctx.commit('setBalance')
        }
    },
    mutations: {
        setBalance (state) {
            state.balance = '120'
        }
    },
    state: {
        balance: String
    },
    getters: {
        balance: (state) => state.balance
    }
}