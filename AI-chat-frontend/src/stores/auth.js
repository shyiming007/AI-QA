import { createStore } from 'vuex';
const authModule = createStore({
    state: {
        username: null // 初始值为 null
    },
    mutations: {
        setUsername(state, username) {
            state.username = username;
        }
    },
    actions: {
        loginUser({ commit }, username) {
            commit('setUsername', username);
        }
    },
    getters: {
        loggedInUsername: state => state.username
    }
});

export default authModule;
