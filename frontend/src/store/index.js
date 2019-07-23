import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

const LOGIN = 'LOGIN'
const LOGOUT = 'LOGOUT'
const SEARCH_BOOKS = 'SEARCH_BOOKS'
const HISTORY = 'HISTORY'
const ADD_HISTORY = 'ADD_HISTORY'

Vue.use(Vuex)

const state = {
  isLoggedIn: !!localStorage.getItem('access_token'),
  userId: localStorage.getItem('user_id'),
  token: localStorage.getItem('access_token'),
  books: {},
  myHistory: []
}

const getters = {
  isLoggedIn: (state) => state.isLoggedIn,
  getUserId: (state) => state.userId,
  getToken: (state) => state.token,
  getBooks: (state) => state.books.books,
  getTotalBookCount: (state) => state.books.totalCount,
  getSearchKeyword: (state) => state.books.searchKeyword,
  getMyHistory: (state) => state.myHistory
}

const mutations = {
  [LOGIN] (state, payload) {
    state.isLoggedIn = true
    state.userId = payload.userId
    state.token = payload.token
    localStorage.setItem('user_id', payload.userId)
    localStorage.setItem('access_token', payload.token)
    axios.defaults.headers.common['X-AUTH-TOKEN'] = payload.token
  },
  [LOGOUT] (state) {
    state.isLoggedIn = false
    state.userId = ''
    state.token = ''
    localStorage.removeItem('user_id')
    localStorage.removeItem('access_token')
    axios.defaults.headers.common['X-AUTH-TOKEN'] = ''
  },
  [SEARCH_BOOKS] (state, payload) {
    state.books = {
      books: payload.books,
      totalCount: payload.totalCount,
      searchKeyword: payload.searchKeyword
    }
  },
  [HISTORY] (state, payload) {
    state.myHistory = []
    payload.forEach(item => state.myHistory.push(item))
  }
}

const actions = {
  [LOGIN] ({commit}, payload) {
    return axios.post(`${payload.hostname}/user/login`, {
      userId: payload.userId,
      userPassword: payload.password
    }).then(res => {
      console.log(`status code: ${res.status}`)
      commit(LOGIN, res.data)
    }).catch(err => {
      console.log(err)
    })
  },
  [LOGOUT] ({commit}) {
    commit(LOGOUT)
  },
  [SEARCH_BOOKS] ({commit}, payload) {
    axios.get(`${payload.hostname}/api/v1/books`, {
      params: {
        keyword: payload.keyword,
        page: payload.page
      },
      timeout: 5000
    }).then(res => {
      console.log(`status code: ${res.status}`)
      commit(SEARCH_BOOKS, {
        books: res.data.books,
        totalCount: res.data.totalCount,
        searchKeyword: payload.keyword
      })
    }).catch(err => {
      console.log(err)
      commit(SEARCH_BOOKS, {
        books: [],
        totalCount: 0,
        searchKeyword: payload.keyword
      })
    })
  },
  [HISTORY] ({commit}, payload) {
    axios.get(`${payload.hostname}/api/v1/history`, {
      params: {
        userId: payload.userId
      },
      timeout: 5000
    }).then(res => {
      console.log(`status code: ${res.status}`)
      commit(HISTORY, res.data)
    }).catch(err => {
      console.log(err)
    })
  },
  [ADD_HISTORY] ({commit}, payload) {
    axios.post(`${payload.hostname}/api/v1/history`, {
      userId: payload.userId,
      keyword: payload.keyword,
      datetime: payload.datetime
    }).then(res => {
      console.log(`status code: ${res.status}`)
    }).catch(err => {
      console.log(err)
    })
  }
}

export default new Vuex.Store({
  state,
  getters,
  mutations,
  actions
})
