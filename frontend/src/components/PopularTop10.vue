<template>
  <div :hidden="!logged_in">
    <!--<h5>Popular Top 10</h5>-->
    <b-list-group horizontal="md row">
      <b-list-group-item class="d-flex justify-content-between align-items-center">
        Cras justo odio
        <b-badge variant="primary" pill>14</b-badge>
      </b-list-group-item>
    </b-list-group>
  </div>
</template>
