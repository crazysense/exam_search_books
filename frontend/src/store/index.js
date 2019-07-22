import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userId: null,
    accessToken: null
  },
  getters: {
    isLoggedOn: state => {
      return !!state.accessToken
    }
  },
  mutations: {
    LOGIN (state, {userId, token}) {
      state.userId = userId
      state.accessToken = token
      localStorage.accessToken = token
      axios.defaults.headers.common['X-AUTH-TOKEN'] = token
    },
    LOGOUT (state) {
      state.userId = null
      state.accessToken = null
      delete localStorage.accessToken
      delete axios.defaults.headers.common['X-AUTH-TOKEN']
    }
  },
  actions: {
    LOGIN ({commit}, {hostname, userId, password}) {
      return axios.post(`${hostname}/user/login`, {
        userId: userId,
        userPassword: password
      }).then(({data}) => {
        commit('LOGIN', data)
      })
    },
    LOGOUT ({commit}) {
      commit('LOGOUT')
    }
  }
})
