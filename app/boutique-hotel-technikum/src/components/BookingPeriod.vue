<template>
  <ion-row>
    <ion-col>
      <ion-text>Anreise: <span>{{ arrival }}</span></ion-text>
    </ion-col>
  </ion-row>
  <ion-row>
    <ion-col>
      <ion-text>Abreise: <span>{{ departure }}</span></ion-text>
    </ion-col>
  </ion-row>
</template>

<script lang="ts">
export default {
  props: {
    arrival: {
      type: String,
      required: true
    },
    departure: {
      type: String,
      required: true
    }
  }
}
</script>