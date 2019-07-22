<template>
  <div>
    <b-navbar type="light" variant="light">
      <b-navbar-brand href="#">Search Books Exam</b-navbar-brand>
      <b-nav-form>
        <b-input-group>
          <b-dropdown slot="prepend" variant="dark"></b-dropdown>
          <b-form-input v-model="keyword" placeholder="Keyword"></b-form-input>
          <b-button slot="append" variant="success" @click="searchBooks">Search</b-button>
        </b-input-group>
      </b-nav-form>
      <div class="ml-auto">
        <b-navbar-nav>
          <b-button variant="info" @click="logout">Logout</b-button>
        </b-navbar-nav>
      </div>
    </b-navbar>
  </div>
</template>

<script>
import Vue from 'vue'
import {BNavbar, BNavbarBrand, BNavbarNav, BNavbarToggle,
  BNavForm, BFormInput, BButton, BInputGroup, BDropdown, BDropdownItem} from 'bootstrap-vue'

Vue.component('b-navbar', BNavbar)
Vue.component('b-navbar-brand', BNavbarBrand)
Vue.component('b-navbar-nav', BNavbarNav)
Vue.component('b-navbar-toggle', BNavbarToggle)
Vue.component('b-nav-form', BNavForm)
Vue.component('b-form-input', BFormInput)
Vue.component('b-button', BButton)
Vue.component('b-input-group', BInputGroup)
Vue.component('b-dropdown', BDropdown)
Vue.component('b-dropdown-item', BDropdownItem)

export default {
  name: 'Navbar',
  data () {
    return {
      keyword: ''
    }
  },
  methods: {
    searchBooks () {
      if (this.keyword.length === 0) {
        alert('Empty Keyword!')
      } else {
        const hostname = this.$hostname
        const keyword = this.keyword
        this.$store.dispatch('SEARCH_BOOKS', {hostname, keyword})
      }
    },
    logout () {
      const router = this.$router
      this.$store.dispatch('LOGOUT')
        .then(() => {
          router.push({name: 'Login'})
        })
    }
  }
}

</script>

<style>

</style>
