<template>
  <ion-page>
    <ion-header>
      <ion-grid>
        <ion-row>
          Buchungsbestätigung
        </ion-row>
      </ion-grid>
    </ion-header>

    <ion-content>
      <ion-grid>
        <h1>Vielen dank für Ihre Reservierung, wir haben Ihre Buchung erhalten!</h1>
      </ion-grid>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {useBookingStore} from "@/stores/booking";

export default {
  data() {
    return {
      booking: useBookingStore()
    }
  },
  unmounted(){
    this.booking.status = "booking";
    this.booking.room = undefined;
  }
}
</script>