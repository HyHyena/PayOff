export default{
    actions: {
        sendPayout (ctx, payoutObj) {
            return fetch('/payoff/payouts-gateway/payout', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify({
                    amount: payoutObj.amount,
                    cardNumber: payoutObj.pan
                })
            })
                .then(rs => {
                    return rs.json();
                })
                .then(jsonObj => {
                    ctx.commit('setStatus', jsonObj);
                })
                .catch(err => {
                    console.log(err);
                    ctx.commit('setStatus', {status: "Ошибочка"});
                });
        },
        checkStatus (ctx) {
            return fetch('/payoff/payouts-gateway/status', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify({
                    partnerPayoutId: 1111111111
                })
            })
                .then(rs => {
                    return rs.json();
                })
                .then(jsonObj => {
                    ctx.commit('setStatus', jsonObj);
                })
                .catch(err => {
                    console.log(err);
                    ctx.commit('setStatus', {status: "Ошибочечка"});
                });
        }
    },
    mutations: {
        setStatus (state, obj) {
            console.log('Full obj: ' + JSON.stringify(obj));
            state.status = obj.status
        }
    },
    state: {
        status: 'Пусто'
    },
    getters: {
        status: (state) => state.status
    }
}