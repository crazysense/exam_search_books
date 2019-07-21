<template>
  <div>
    <b-modal id="registration-modal" hide-footer title="Registration">
      <b-form>
        <b-form-group
          id="fieldset-id"
          label="Enter your ID"
          description="알파벳 소문자로 시작하는 5-15 자리 문자 (숫자를 포함할 수 있음)"
          label-for="input-id"
          :invalid-feedback="invalidFeedbackId"
          :valid-feedback="validFeedbackId"
          :state="stateId">
          <b-form-input id="input-id" v-model="regUserId" :state="stateId" type="text"></b-form-input>
        </b-form-group>
        <b-form-group
          id="fieldset-pwd"
          label="Enter your Password"
          description="알파벳으로 시작하고 대문자와 소문자, 숫자를 각각 하나 이상 포함하는 8-20 자리 문자"
          label-for="input-pwd"
          :invalid-feedback="invalidFeedbackPwd"
          :valid-feedback="validFeedbackPwd"
          :state="statePwd">
          <b-form-input id="input-pwd" v-model="regUserPwd" :state="statePwd" type="password"></b-form-input>
        </b-form-group>
        <br/>
        <b-alert variant="danger" :show="regRequestFailed">
          {{ regErrorMessage }}
        </b-alert>
        <b-button @click="submit" variant="primary">Register</b-button>
      </b-form>
    </b-modal>
    <b-modal id="registration-succeed-modal" hide-footer title="Success!">
      <b-alert variant="success" show="true">
        User registration has been successfully.<br/>
        Web pages are available after sign-in.
      </b-alert>
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      regUserId: '',
      regUserPwd: '',
      regRequestFailed: false,
      regErrorMessage: ''
    }
  },
  created () {
    this.regUserId = ''
    this.regUserPwd = ''
    this.regRequestFailed = false
  },
  computed: {
    stateId () {
      return /^[a-z]{1}[A-Za-z0-9]{4,14}$/.exec(this.regUserId) != null
    },
    invalidFeedbackId () {
      if (this.regUserId.length > 15) {
        return 'Enter a maxinum of 15 characters'
      }
      if (this.regUserId.length > 4) {
        return ''
      } else if (this.regUserId.length > 0) {
        return 'Enter at least 5 characters'
      } else {
        return 'Please enter something'
      }
    },
    validFeedbackId () {
      return this.stateId === true ? 'Thank you' : ''
    },
    statePwd () {
      return /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z]{1}[a-zA-Z\d]{7,19}$/.exec(this.regUserPwd) != null
    },
    invalidFeedbackPwd () {
      if (this.regUserPwd.length > 20) {
        return 'Enter a maxinum of 20 characters'
      }
      if (this.regUserPwd.length > 8) {
        return ''
      } else if (this.regUserPwd.length > 0) {
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
        this.regErrorMessage = 'Wrong ID.'
        return
      }
      if (this.statePwd === false) {
        this.regErrorMessage = 'Wrong Password.'
        return
      }

      axios.post(`${window.location.origin}/registration`, {
        userId: this.regUserId,
        userPassword: this.regUserPwd
      }).then(res => {
        console.log(`status code: ${res.status}`)
        this.regRequestFailed = false
        this.$bvModal.hide('registration-modal')
        this.$bvModal.show('registration-succeed-modal')
      }).catch(err => {
        console.log(err)
        this.regRequestFailed = true
        this.regErrorMessage = err
      }).finally(out => {
        this.regUserId = ''
        this.regUserPwd = ''
      })
    }
  }
}
</script>
