<template>
  <ion-row class="ion-align-items-center">
    <ion-col>{{ description }}</ion-col>
  </ion-row>

  <ion-row>
    <ion-col>
      <slot></slot>
    </ion-col>
  </ion-row>
</template>

<script lang="ts">
export default {
  props: {
    description: {
      type: String,
      required: true
    }
  }
}
</script>