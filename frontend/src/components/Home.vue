<template>
  <div>
    <div>
      <Navbar/>
      <br/>
      <PopularTop10/>
      <br/>
      <BookBoard/>
    </div>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import BookBoard from '@/components/BookBoard'
import PopularTop10 from '@/components/PopularTop10'

export default {
  name: 'Home',
  components: {
    Navbar,
    BookBoard,
    PopularTop10
  },
  data () {
    return {
      logged_in: false
    }
  }
}
</script>
