<template>
  <ion-row>
    <h1>Ihre Kontaktdaten:</h1>
  </ion-row>
  <ion-row>
    <ion-col class="ion-text-end" size="auto">Vorname:</ion-col>
    <ion-col>{{ customer.firstName }}</ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="ion-text-end" size="auto">Nachname:</ion-col>
    <ion-col>{{ customer.lastName }}</ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="ion-text-end" size="auto">E-Mail:</ion-col>
    <ion-col>{{ customer.eMail }}</ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="ion-text-end" size="auto">Frühstück:</ion-col>
    <ion-col>{{ booking.breakfast ? "Ja" : "Nein" }}</ion-col>
  </ion-row>

  <ion-row v-if="customer.address.street">
    <ion-col class="ion-text-end" size="auto">Straße:</ion-col>
    <ion-col>{{ customer.address.street }}</ion-col>
  </ion-row>
  <ion-row v-if="customer.address.postCode">
    <ion-col class="ion-text-end" size="auto">PLZ:</ion-col>
    <ion-col>{{ customer.address.postCode }}</ion-col>
  </ion-row>
  <ion-row v-if="customer.address.city">
    <ion-col class="ion-text-end" size="auto">Stadt:</ion-col>
    <ion-col>{{ customer.address.city }}</ion-col>
  </ion-row>
  <ion-row v-if="customer.address.country">
    <ion-col class="ion-text-end" size="auto">Land:</ion-col>
    <ion-col>{{ customer.address.country }}</ion-col>
  </ion-row>
</template>

<script lang="ts">
import {useBookingStore} from "@/stores/booking";
import {useCustomerStore} from "@/stores/customer";

export default {
  data() {
    return {
      booking: useBookingStore(),
      customer: useCustomerStore()
    }
  }
}
</script>