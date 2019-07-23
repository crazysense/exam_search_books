<template>
  <div>
    <b-pagination v-model="currentPage"
                  :total-rows="rows"
                  :per-page="perPage"
                  v-on:click.native="moreBooks(currentPage)"/>
    <div class="text-left">
      <b-table id="book-table" small striped hover
               :items="items"
               :fields="fields">
        <template slot="thumbnail" slot-scope="data">
          <img :src="data.item.thumbnail" alt="" width="120" height="180"/>
        </template>
        <template slot="details" slot-scope="data">
          <h5>{{ data.item.title }}</h5>
          <p>{{ data.item.author }} 지음 | {{ data.item.publisher }} | {{ data.item.datetime }}</p>
          <p>{{ data.item.contents.substring(0, 100) }} ... </p>
          <p>정가 : {{ data.item.price }} | 판매가 : {{ data.item.salePrice }} | ISBN : {{ data.item.isbn }}</p>
        </template>
        <template slot="button" slot-scope="data">
          <b-button v-b-modal.book-detail-modal variant="info" @click="sendToModal(data.item)">Book Details</b-button>
        </template>
      </b-table>
    </div>
    <div>
      <b-modal id="book-detail-modal" hide-footer title="Book Details">
        <b-jumbotron :header="selectedItem.title" header-level="5" :lead="authorText(selectedItem)">
          <b-container fluid>
            <b-row class="text-left">
              <b-col md="auto">
                <b-img :src="selectedItem.thumbnail" fluid></b-img>
              </b-col>
              <b-col>
                <p>출판사 : {{ selectedItem.publisher }}</p>
                <p>출판일 : {{ selectedItem.datetime }}</p>
                <p>정가 : {{ selectedItem.price }}</p>
                <p>판매가 : {{ selectedItem.salePrice }}</p>
                <p>ISBN :  {{ selectedItem.isbn }}</p>
              </b-col>
            </b-row>
            <b-row>
              <h3>요약</h3>
              <p>{{ selectedItem.contents }}...</p>
            </b-row>
          </b-container>
        </b-jumbotron>
      </b-modal>
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import {BTable, BPagination} from 'bootstrap-vue'

Vue.component('b-table', BTable)
Vue.component('b-pagination', BPagination)

export default {
  name: 'BookBoard',
  data () {
    return {
      perPage: 10,
      currentPage: 1,
      totalCount: 0,
      fields: [
        {key: 'thumbnail', thStyle: {display: 'none'}},
        {key: 'details', thStyle: {display: 'none'}},
        {key: 'button', thStyle: {display: 'none'}}
      ],
      selectedItem: ''
    }
  },
  computed: {
    rows () {
      console.log('111: ' + this.$store.getters.getTotalBookCount)
      return this.$store.getters.getTotalBookCount
    },
    items () {
      console.log('222')
      return this.$store.getters.getBooks
    }
  },
  methods: {
    getSearchKeyword () {
      return this.$store.getters.getSearchKeyword
    },
    sendToModal (item) {
      this.selectedItem = item
    },
    authorText (item) {
      return item.author + ' 지음'
    },
    moreBooks (page) {
      const keyword = this.getSearchKeyword()
      const hostname = this.$hostname
      this.$store.dispatch('SEARCH_BOOKS', {hostname, keyword, page})
    }
  }
}
</script>
