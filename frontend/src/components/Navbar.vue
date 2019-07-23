<template>
  <div>
    <b-navbar type="light" variant="light">
      <b-navbar-brand href="#">Search Books Exam</b-navbar-brand>
      <b-nav-form>
        <b-input-group>
          <b-dropdown slot="prepend" variant="dark">
            <b-dropdown-item v-for="item in historyItems" :key="item.keyword" @click="searchKeyword = item.keyword">
              {{ item.keyword }} ({{ item.datetime }})
            </b-dropdown-item>
          </b-dropdown>
          <b-form-input v-model="searchKeyword"
                        placeholder="Keyword"
                        @keydown.enter="searchBooks">
          </b-form-input>
          <b-button slot="append" variant="success" @click="searchBooks">Search</b-button>
        </b-input-group>
      </b-nav-form>
      <div class="ml-auto">
        <b-navbar-nav>
          <b-button variant="danger" @click="logout">Logout</b-button>
        </b-navbar-nav>
      </div>
    </b-navbar>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import store from '@/store'

export default {
  name: 'Navbar',
  data () {
    return {
      searchKeyword: ''
    }
  },
  computed: {
    ...mapGetters({
      userId: 'getUserId',
      historyItems: 'getMyHistory'
    })
  },
  created () {
    const hostname = this.$hostname
    const userId = this.userId
    store.dispatch('HISTORY', {hostname, userId})
  },
  methods: {
    searchBooks () {
      if (this.searchKeyword.length === 0) {
        alert('Empty Keyword!')
      } else {
        const keyword = this.searchKeyword
        const hostname = this.$hostname
        store.dispatch('SEARCH_BOOKS', {hostname, keyword, page: 1})

        const datetime = this.getCurrentDate()
        const hist = {keyword, datetime}

        this.addHistoryToLocal(hist)

        const userId = this.userId
        store.dispatch('ADD_HISTORY', {hostname, userId, keyword, datetime})
      }
    },
    logout () {
      const router = this.$router
      store.dispatch('LOGOUT')
        .then(() => {
          router.push({name: 'Login'})
        })
    },
    addHistoryToLocal (hist) {
      let i = this.historyItems.length - 1
      while (i >= 0) {
        if (this.historyItems[i].keyword === hist.keyword) {
          break
        }
        --i
      }
      if (i >= 0) {
        this.historyItems.splice(i, 1)
      }

      this.historyItems.unshift(hist)

      if (this.historyItems.length > 10) {
        this.historyItems.pop()
      }
    },
    getCurrentDate () {
      return new Date().toISOString().split('.')[0].replace('T', ' ')
    }
  }
}
</script>
