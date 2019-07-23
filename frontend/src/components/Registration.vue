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
import validation from '@/module/validation'

export default {
  name: 'Registration',
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
      return validation.stateId(this.regUserId) != null
    },
    invalidFeedbackId () {
      return validation.invalidFeedbackId(this.regUserId)
    },
    validFeedbackId () {
      return validation.validFeedbackId(this.regUserId)
    },
    statePwd () {
      return validation.statePwd(this.regUserPwd) != null
    },
    invalidFeedbackPwd () {
      return validation.invalidFeedbackPwd(this.regUserPwd)
    },
    validFeedbackPwd () {
      return validation.validFeedbackPwd(this.regUserPwd)
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

      const hostname = this.$hostname
      axios.post(`${hostname}/user/registration`, {
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
      }).finally(() => {
        this.regUserId = ''
        this.regUserPwd = ''
      })
    }
  }
}
</script>
