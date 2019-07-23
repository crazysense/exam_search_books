import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    searchKeyword: '',
    books: [],
    booksTotal: 0
  },
  getters: {
    isLoggedOn: () => {
      return localStorage.session != null
    },
    getUserId: () => {
      return localStorage.session.userId
    },
    getBooks: () => {
      return localStorage.books.books
    },
    getTotalBookCount: () => {
      return localStorage.books.booksTotal
    },
    getSearchKeyword: () => {
      return localStorage.books.searchKeyword
    }
  },
  mutations: {
    SEARCH_BOOKS (state, {bookResult, searchKeyword}) {
      localStorage.books = {
        books: bookResult.books,
        booksTotal: bookResult.totalCount,
        searchKeyword: searchKeyword
      }
    },
    LOGIN (state, {userId, token}) {
      localStorage.session = {
        userId: userId,
        accessToken: token
      }
      axios.defaults.headers.common['X-AUTH-TOKEN'] = token
    },
    LOGOUT (state) {
      localStorage.books = {
        books: [],
        booksTotal: 0,
        searchKeyword: ''
      }
      delete localStorage.session
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
      }).catch(err => {
        console.log(err)
      })
    },
    LOGOUT ({commit}) {
      commit('LOGOUT')
    },
    SEARCH_BOOKS ({commit}, {hostname, keyword, page}) {
      axios.get(`${hostname}/api/v1/books`, {
        params: {
          keyword: keyword,
          page: page
        },
        timeout: 5000
      }).then(res => {
        console.log(`status code: ${res.status}`)
        commit('SEARCH_BOOKS', {
          bookResult: res.data,
          searchKeyword: keyword
        })
      }).catch(err => {
        console.log(err)
        commit('SEARCH_BOOKS', {
          bookResult: [],
          searchKeyword: keyword
        })
      })
    }
  }
})
