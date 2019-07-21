<template>
  <div>
    <div>
      <Navbar/>
      <br/>
      <PopularTop10/>
      <br/>
      <BookBoard/>
    </div>
    <div class="text-left" :hidden="logged_in">
      <b-jumbotron header="Search Books Exam" lead="Sign in for more information.">
        <p>Created by MINYONG YUK</p>
        <b-button v-b-modal.login-modal size="lg" class="col-2" variant="primary" type="submit">SignIn</b-button>
        <b-button v-b-modal.registration-modal size="lg" class="col-2" variant="success" type="submit">SignUp</b-button>
      </b-jumbotron>
    </div>
    <Login />
    <Registration />
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import BookBoard from '@/components/BookBoard'
import PopularTop10 from '@/components/PopularTop10'
import Login from '@/components/Login'
import Registration from '@/components/Registration'

export default {
  name: 'Home',
  components: {
    Navbar,
    BookBoard,
    PopularTop10,
    Login,
    Registration
  },
  data () {
    return {
      logged_in: false
    }
  }
}
</script>
