// store/record.js
import { createStore } from 'vuex';

const store = createStore({
    state: {
        records: [] // 初始为空数组
    },
    mutations: {
        addRecord(state, record) {
            state.records.push(record);
        },
        clearRecords(state) {
            state.records = [];
        }
    },
    actions: {
        saveRecord({ commit }, record) {
            commit('addRecord', record);
        },
        clearAllRecords({ commit }) {
            commit('clearRecords');
        }
    },
    getters: {
        allRecords: state => state.records
    }
});

export default store;
