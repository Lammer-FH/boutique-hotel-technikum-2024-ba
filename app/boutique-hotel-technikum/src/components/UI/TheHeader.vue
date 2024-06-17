<template>
  <ion-header class="app-header">
    <ion-toolbar>
      <ion-title v-if="title">{{ title }}</ion-title>
      <slot v-else></slot>
    </ion-toolbar>
  </ion-header>
</template>

<script lang="ts">
export default {
  props: {
    title: {
      type: String,
      required: false
    }
  }
}
</script>