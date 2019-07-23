<template>
  <div>
    <b-jumbotron header="Search Books Exam" lead="Login for more information.">
      <p>Created by MINYONG YUK</p>
      <div class="text-left">
        <b-form>
          <b-form-group
            label="Enter your ID"
            description="알파벳 소문자로 시작하는 5-15 자리 문자 (숫자를 포함할 수 있음)"
            label-for="input-id"
            :invalid-feedback="invalidFeedbackId"
            :valid-feedback="validFeedbackId"
            :state="stateId">
            <b-form-input id="input-id" v-model="loginUserId" :state="stateId" type="text"></b-form-input>
          </b-form-group>
          <b-form-group
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
          <b-button @click="submit(loginUserId, loginUserPwd)" variant="primary">Login</b-button>
          <b-button v-b-modal.registration-modal variant="info">Registration</b-button>
        </b-form>
      </div>
    </b-jumbotron>
    <Registration />
  </div>
</template>

<script>
import validation from '@/module/validation'
import Registration from '@/components/Registration'
import store from '@/store'

export default {
  name: 'Login',
  components: {
    Registration
  },
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
      return validation.stateId(this.loginUserId) != null
    },
    invalidFeedbackId () {
      return validation.invalidFeedbackId(this.loginUserId)
    },
    validFeedbackId () {
      return validation.validFeedbackId(this.loginUserId)
    },
    statePwd () {
      return validation.statePwd(this.loginUserPwd) != null
    },
    invalidFeedbackPwd () {
      return validation.invalidFeedbackPwd(this.loginUserPwd)
    },
    validFeedbackPwd () {
      return validation.validFeedbackPwd(this.loginUserPwd)
    }
  },
  methods: {
    submit (userId, password) {
      if (this.stateId === false) {
        this.loginErrorMessage = 'Wrong ID.'
        return
      }
      if (this.statePwd === false) {
        this.loginErrorMessage = 'Wrong Password.'
        return
      }

      const hostname = this.$hostname
      const router = this.$router

      store.dispatch('LOGIN', {
        hostname, userId, password
      }).then(() => {
        router.push({name: 'Home'})
      }).catch(err => {
        console.log(err)
        this.loginRequestFailed = true
        this.loginErrorMessage = err
      }).finally(() => {
        this.loginUserId = ''
        this.loginUserPwd = ''
      })
    }
  }
}
</script>
