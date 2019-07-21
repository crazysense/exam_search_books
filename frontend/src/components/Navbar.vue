<template>
  <div>
    <b-navbar type="light" variant="light">
      <b-navbar-brand href="#">Search Books Exam</b-navbar-brand>
      <b-nav-form :hidden="!logged_in">
        <b-input-group>
          <b-dropdown slot="prepend" variant="dark"></b-dropdown>
          <b-form-input placeholder="Keyword"></b-form-input>
          <b-button slot="append" variant="success" type="submit">Search</b-button>
        </b-input-group>
      </b-nav-form>
      <!--<div class="ml-auto" :hidden="logged_in">-->
        <!--<b-navbar-nav>-->
          <!--<b-button v-b-modal.login-modal variant="info" type="submit">SignIn</b-button>-->
          <!--<b-button class="ml-1" v-b-modal.registration-modal variant="info" type="submit">SignUp</b-button>-->
        <!--</b-navbar-nav>-->
      <!--</div>-->
      <div class="ml-auto" :hidden="!logged_in">
        <b-navbar-nav>
          <b-button variant="info" type="submit">SignOut</b-button>
        </b-navbar-nav>
      </div>
    </b-navbar>
    <Login />
    <Registration />
  </div>
</template>

<script>

</script>

<style>

</style>
