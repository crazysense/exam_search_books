<template>
  <!-- Modal -->
  <b-modal id="login-modal" hide-footer title="Sign In">
    <b-form>
      <b-form-group
        id="fieldset-id"
        label="Enter your ID"
        description="알파벳 소문자로 시작하는 5-15 자리 문자 (숫자를 포함할 수 있음)"
        label-for="input-id"
        :invalid-feedback="invalidFeedbackId"
        :valid-feedback="validFeedbackId"
        :state="stateId">
        <b-form-input id="input-id" v-model="loginUserId" :state="stateId" type="text"></b-form-input>
      </b-form-group>
      <b-form-group
        id="fieldset-pwd"
        label="Enter your Password"
        description="알파벳으로 시작하고 대문자와 소문자, 숫자를 각각 하나 이상 포함하는 8-20 자리 문자"
        label-for="input-pwd"
        :invalid-feedback="invalidFeedbackPwd"
        :valid-feedback="validFeedbackPwd"
        :state="statePwd">
        <b-form-input id="input-pwd" v-model="loginUserPwd" :state="statePwd" type="password"></b-form-input>
      </b-form-group>
      <br/>
      <b-alert variant="danger" :show="loginRequestFailed">
        {{ loginErrorMessage }}
      </b-alert>
      <b-button @click="submit" variant="primary">Sign In</b-button>
    </b-form>
  </b-modal>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      loginUserId: '',
      loginUserPwd: '',
      loginRequestFailed: false,
      loginErrorMessage: ''
    }
  },
  computed: {
    stateId () {
      return /^[a-z]{1}[A-Za-z0-9]{4,14}$/.exec(this.loginUserId) != null
    },
    invalidFeedbackId () {
      if (this.loginUserId.length > 15) {
        return 'Enter a maxinum of 15 characters'
      }
      if (this.loginUserId.length > 4) {
        return ''
      } else if (this.loginUserId.length > 0) {
        return 'Enter at least 5 characters'
      } else {
        return 'Please enter something'
      }
    },
    validFeedbackId () {
      return this.stateId === true ? 'Thank you' : ''
    },
    statePwd () {
      return /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z]{1}[a-zA-Z\d]{7,19}$/.exec(this.loginUserPwd) != null
    },
    invalidFeedbackPwd () {
      if (this.loginUserPwd.length > 20) {
        return 'Enter a maxinum of 20 characters'
      }
      if (this.loginUserPwd.length > 8) {
        return ''
      } else if (this.loginUserPwd.length > 0) {
        return 'Enter at least 8 characters'
      } else {
        return 'Please enter something'
      }
    },
    validFeedbackPwd () {
      return this.statePwd === true ? 'Thank you' : ''
    }
  },
  methods: {
    submit () {

      if (this.stateId === false) {
        this.loginErrorMessage = 'Wrong ID.'
        return
      }
      if (this.statePwd === false) {
        this.loginErrorMessage = 'Wrong Password.'
        return
      }

      axios.get(`${window.location.origin}/login`, {
        params: {
          // TODO
          keyword: '미움받을 용기'
        },
        timeout: 5000
        // headers: {
        // TODO
        // }
      }).then(res => {
        console.log(`status code: ${res.status}`)
        this.loginRequestFailed = false
        this.$bvModal.hide('login-modal')
        this.logged_in = true
      }).catch(err => {
        console.log(err)
        this.loginRequestFailed = true
        this.loginErrorMessage = err
      }).finally(out => {
        this.loginUserId = ''
        this.loginUserPwd = ''
      })
    }
  }
}
</script>

<style>

</style>
