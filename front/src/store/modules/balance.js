export default{
    actions: {
        getBalance (ctx) {
            return fetch('/payoff/payouts-gateway/balance', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify({
                    accountId: "1234567800000000"
                })
            })
                .then(rs => {
                    return rs.json();
                })
                .then(jsonObj => {
                    ctx.commit('setBalance', jsonObj);
                })
                .catch(err => {
                    console.log(err);
                    ctx.commit('setBalance', {accountEntity: {balance: 404}});
                });
        }
    },
    mutations: {
        setBalance (state, obj) {
            console.log('Full obj: ' + JSON.stringify(obj));
            console.log('Only balance: ' + JSON.stringify(obj.accountEntity.balance));
            state.balance = obj.accountEntity.balance;
        }
    },
    state: {
        balance: null
    },
    getters: {
        balance: (state) => state.balance
    }
}