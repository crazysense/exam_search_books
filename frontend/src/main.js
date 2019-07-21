// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
// import router from './router'
import BootstrapVue from 'bootstrap-vue'
import Validation from './module/validation'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import {BNavbar, BNavbarBrand, BNavbarNav, BNavbarToggle,
  BFormGroup, BFormInput, BButton, BTable, BPagination,
  BInputGroup, BInputGroupAppend, BInputGroupPrepend,
  BModal, BDropdown, BDropdownItem, BJumbotron,
  BListGroup, BListGroupItem, BBadge, BForm, BAlert,
  BFormInvalidFeedback, BFormValidFeedback} from 'bootstrap-vue/es/components'
import {BNavForm} from 'bootstrap-vue/es/components/nav/index'

Vue.config.productionTip = false

Vue.use(BootstrapVue)
Vue.use(Validation)

Vue.component('b-navbar', BNavbar)
Vue.component('b-navbar-brand', BNavbarBrand)
Vue.component('b-navbar-nav', BNavbarNav)
Vue.component('b-navbar-toggle', BNavbarToggle)
Vue.component('b-nav-form', BNavForm)
Vue.component('b-alert', BAlert)
Vue.component('b-form', BForm)
Vue.component('b-form-group', BFormGroup)
Vue.component('b-form-input', BFormInput)
Vue.component('b-button', BButton)
Vue.component('b-table', BTable)
Vue.component('b-pagination', BPagination)
Vue.component('b-input-group', BInputGroup)
Vue.component('b-input-group-append', BInputGroupAppend)
Vue.component('b-input-group-prepend', BInputGroupPrepend)
Vue.component('b-dropdown', BDropdown)
Vue.component('b-dropdown-item', BDropdownItem)
Vue.component('b-list-group', BListGroup)
Vue.component('b-list-group-item', BListGroupItem)
Vue.component('b-badge', BBadge)
Vue.component('b-modal', BModal)
Vue.component('b-jumbotron', BJumbotron)
Vue.component('b-form-valid-feedback', BFormValidFeedback)
Vue.component('b-form-invalid-feedback', BFormInvalidFeedback)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  // router,
  components: {App},
  template: '<App/>'
})
