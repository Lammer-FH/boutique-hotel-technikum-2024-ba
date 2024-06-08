<template>
  <ion-row class="ion-align-items-center">
    <ion-col class="ion-text-end">{{ description }}</ion-col>
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