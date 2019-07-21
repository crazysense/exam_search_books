<template>
  <div :hidden="!logged_in">
    <!--<h1>Books</h1>-->
    <b-pagination v-model="currentPage"
                  :total-rows="rows"
                  :per-page="perPage"
                  aria-controls="book-table"/>
    <div class="text-left">
      <b-table id="book-table" small striped hover
               :items="items"
               :fields="fields"
               :per-page="perPage"
               :current-page="currentPage">
        <template slot="thumbnail" slot-scope="data">
          <img :src="data.item.thumbnail" alt="" width="120" height="180"/>
        </template>
        <template slot="details" slot-scope="data">
          <h5>{{ data.item.title }}</h5>
          <p>{{ data.item.author }} 지음 | {{ data.item.publisher }} | {{ data.item.datetime }}</p>
          <p>{{ data.item.contents.substring(0, 100) }} ... </p>
          <p>정가 : {{ data.item.price }} | 판매가 : {{ data.item.salePrice }} | ISBN : {{ data.item.isbn }}</p>
        </template>
      </b-table>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      perPage: 5,
      currentPage: 1,
      fields: [
        {key: 'thumbnail', thStyle: {display: 'none'}},
        {key: 'details', thStyle: {display: 'none'}}
      ],
      items: [ ]
    }
  },
  created () {
    axios.get(`${window.location.origin}/api/search_books`, {
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
      this.items = res.data
    }).catch(err => {
      console.log(err)
    })
  },
  computed: {
    rows () {
      return this.items.length
    }
  }
}
</script>
