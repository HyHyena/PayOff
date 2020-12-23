export default{
    actions: {
        checkStatus (ctx) {
            ctx.commit('setStatus')
        }
    },
    mutations: {
        setStatus (state) {
            state.status = 'Error'
        }
    },
    state: {
        status: String
    },
    getters: {
        status: (state) => state.status
    }
}