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
import Vue from 'vue'
import axios from 'axios'

import {BListGroup, BListGroupItem, BBadge} from 'bootstrap-vue'

Vue.component('b-list-group', BListGroup)
Vue.component('b-list-group-item', BListGroupItem)
Vue.component('b-badge', BBadge)

export default {
  name: 'PopularTop10',
  data () {
    return {
      popularTop10: []
    }
  },
  created () {
    setInterval(() => {
      if (this.$store.getters.isLoggedOn) {
        axios.get(`${this.$hostname}/api/v1/history/rank`, {
          timeout: 5000
        }).then(res => {
          this.popularTop10 = res.data
        }).catch(err => {
          console.log(err)
        })
      }
    }, 5000)
  }
}

</script>
