<template>
  <ion-row>
    <ion-col>
      <ion-text>Anreise: <span>{{ booking.prettyArrival }}</span></ion-text>
    </ion-col>
  </ion-row>
  <ion-row>
    <ion-col>
      <ion-text>Abreise: <span>{{ booking.prettyDeparture }}</span></ion-text>
    </ion-col>
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