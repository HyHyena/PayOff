export default{
    actions: {
        sendPayout (ctx, payoutObj) {
            console.log(payoutObj)
            ctx.commit('setStatus', 'Success')
        },
        checkStatus (ctx) {
            ctx.commit('setStatus')
        }
    },
    mutations: {
        setStatus (state, arg) {
            state.status = arg ? arg : 'Error'
        }
    },
    state: {
        status: 'Пусто'
    },
    getters: {
        status: (state) => state.status
    }
}