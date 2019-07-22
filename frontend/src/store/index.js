import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userId: null,
    accessToken: null,
    books: []
  },
  getters: {
    isLoggedOn: state => {
      return !!state.accessToken
    },
    getBooks: state => {
      return state.books
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
    },
    SEARCH_BOOKS (state, items) {
      console.log('333: ' + items)
      state.books = items
    }
  },
  actions: {
    LOGIN ({commit}, {hostname, userId, password}) {
      return axios.post(`${hostname}/user/login`, {
        userId: userId,
        userPassword: password
      }).then(({data}) => {
        commit('LOGIN', data)
      }).catch(err => {
        console.log(err)
      })
    },
    LOGOUT ({commit}) {
      commit('LOGOUT')
    },
    SEARCH_BOOKS ({commit}, {hostname, keyword}) {
      axios.get(`${hostname}/api/v1/books`, {
        params: {
          keyword: keyword
        },
        timeout: 5000
      }).then(res => {
        console.log(`status code: ${res.status}`)
        commit('SEARCH_BOOKS', res.data)
      }).catch(err => {
        console.log(err)
        commit('SEARCH_BOOKS', [])
      })
    }
  }
})
