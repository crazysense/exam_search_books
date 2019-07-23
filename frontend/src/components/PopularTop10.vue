<template>
  <div>
    <b-list-group horizontal="md row">
      <b-list-group-item v-for="item in popularTop10" :key="item.keyword" class="d-flex justify-content-between align-items-center">
        {{ item.keyword }}
        <b-badge variant="primary" pill>{{ item.searchCount }}</b-badge>
      </b-list-group-item>
    </b-list-group>
  </div>
</template>

<script>
import axios from 'axios'
import {mapGetters} from 'vuex'

export default {
  name: 'PopularTop10',
  data () {
    return {
      popularTop10: []
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn'])
  },
  created () {
    setInterval(() => {
      if (this.isLoggedIn) {
        const hostname = this.$hostname
        axios.get(`${hostname}/api/v1/rank`, {
          timeout: 5000
        }).then(res => {
          console.log(`status code: ${res.status}`)
          this.popularTop10 = res.data
        }).catch(err => {
          console.log(err)
        })
      }
    }, 3000)
  }
}
</script>
