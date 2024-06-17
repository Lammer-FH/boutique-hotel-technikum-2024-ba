<template>
  <ion-row>
    <h1>Ihre Kontaktdaten:</h1>
  </ion-row>
  <ion-row>
    <ion-col class="ion-text-end" size="auto">Vorname:</ion-col>
    <ion-col>{{ booking.firstName }}</ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="ion-text-end" size="auto">Nachname:</ion-col>
    <ion-col>{{ booking.lastName }}</ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="ion-text-end" size="auto">E-Mail:</ion-col>
    <ion-col>{{ booking.eMail }}</ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="ion-text-end" size="auto">Frühstück:</ion-col>
    <ion-col>{{ booking.breakfast ? "Ja" : "Nein" }}</ion-col>
  </ion-row>

  <template v-if="booking.isAddressValid">
    <ion-row v-if="booking.address.street">
      <ion-col class="ion-text-end" size="auto">Straße:</ion-col>
      <ion-col>{{ booking.address.street }}</ion-col>
    </ion-row>
    <ion-row v-if="booking.address.postCode">
      <ion-col class="ion-text-end" size="auto">PLZ:</ion-col>
      <ion-col>{{ booking.address.postCode }}</ion-col>
    </ion-row>
    <ion-row v-if="booking.address.city">
      <ion-col class="ion-text-end" size="auto">Stadt:</ion-col>
      <ion-col>{{ booking.address.city }}</ion-col>
    </ion-row>
    <ion-row v-if="booking.address.country">
      <ion-col class="ion-text-end" size="auto">Land:</ion-col>
      <ion-col>{{ booking.address.country }}</ion-col>
    </ion-row>
  </template>
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