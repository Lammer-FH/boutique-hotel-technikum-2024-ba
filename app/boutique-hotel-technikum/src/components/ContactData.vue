<template>
  <ion-row>
    <h1>Ihre Kontaktdaten:</h1>
  </ion-row>
  <ion-row>
    <ion-col class="ion-text-end">Vorname:</ion-col>
    <ion-col>{{ booking.firstName }}</ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="ion-text-end">Nachname:</ion-col>
    <ion-col>{{ booking.lastName }}</ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="ion-text-end">E-Mail:</ion-col>
    <ion-col>{{ booking.eMail }}</ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="ion-text-end">Frühstück:</ion-col>
    <ion-col>{{ booking.breakfast ? "Ja" : "Nein" }}</ion-col>
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

