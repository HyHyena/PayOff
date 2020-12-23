export default{
    actions: {
        sendPayout (ctx, payoutObj) {
            ctx.commit('rmStatus')
            console.log(payoutObj)
            ctx.commit('returnStatus', 'Success')
        }
    },
    mutations: {
        rmStatus(state) {
            state.statusPayout = '';
        },
        returnStatus(state, status) {
            state.statusPayout = status;
        }
    },
    state: {
        statusPayout: ''
    },
    getters: {
        statusPayout: (state) => state.statusPayout
    }
}