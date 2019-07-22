<template>
  <div>
    <b-list-group horizontal="md row">
      <b-list-group-item class="d-flex justify-content-between align-items-center">
        Cras justo odio
        <b-badge variant="primary" pill>14</b-badge>
      </b-list-group-item>
    </b-list-group>
  </div>
</template>

<script>
import Vue from 'vue'

import {BListGroup, BListGroupItem, BBadge} from 'bootstrap-vue'

Vue.component('b-list-group', BListGroup)
Vue.component('b-list-group-item', BListGroupItem)
Vue.component('b-badge', BBadge)

export default {

}

</script>
