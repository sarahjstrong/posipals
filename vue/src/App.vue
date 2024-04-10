
import { RouterLink } from 'vue-router';
<template>
  <div id="capstone-app">
    <div class="nav">
      <div class="left-nav">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;&nbsp;
        <router-link v-bind:to="{ name: 'submit' }">Submit Message</router-link>&nbsp;&nbsp;
      </div>
      <div class="right-nav">
        <router-link v-bind:to="{ name: 'profile' }">Profile</router-link>&nbsp;&nbsp;
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>

<style scoped>
  .nav {
    display: flex;
    justify-content: space-between;
  }
</style>
