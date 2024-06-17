<template>
  <ion-row>
    <ion-text>Anreise: <span>{{ booking.prettyArrival }}</span></ion-text>
  </ion-row>
  <ion-row>
    <ion-text>Abreise: <span>{{ booking.prettyDeparture }}</span></ion-text>
  </ion-row>
</template>

<script lang="ts">
import {useBookingStore} from "@/stores/booking";

export default {
  data() {
    return {
      booking: useBookingStore()
    }
  }
}
</script>