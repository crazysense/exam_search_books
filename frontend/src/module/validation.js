export default {
  stateId (userId) {
    return /^[a-z]{1}[A-Za-z0-9]{4,14}$/.exec(userId)
  },

  invalidFeedbackId (userId) {
    if (userId.length > 15) {
      return 'Enter a maxinum of 15 characters'
    }
    if (userId.length > 4) {
      return ''
    } else if (userId.length > 0) {
      return 'Enter at least 5 characters'
    } else {
      return 'Please enter something'
    }
  },

  validFeedbackId (userId) {
    return this.stateId(userId) === true ? 'Thank you' : ''
  },

  statePwd (userPwd) {
    return /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z]{1}[a-zA-Z\d]{7,19}$/.exec(userPwd)
  },

  invalidFeedbackPwd (userPwd) {
    if (userPwd.length > 20) {
      return 'Enter a maxinum of 20 characters'
    }
    if (userPwd.length > 8) {
      return ''
    } else if (userPwd.length > 0) {
      return 'Enter at least 8 characters'
    } else {
      return 'Please enter something'
    }
  },

  validFeedbackPwd (userPwd) {
    return this.statePwd(userPwd) === true ? 'Thank you' : ''
  }
}
