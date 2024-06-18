<template>
  <ion-row>
    <ion-col><h3>Ihre Kontaktdaten:</h3></ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="bold" size="auto">Vorname:</ion-col>
    <ion-col>{{ customer.firstName }}</ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="bold" size="auto">Nachname:</ion-col>
    <ion-col>{{ customer.lastName }}</ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="bold" size="auto">E-Mail:</ion-col>
    <ion-col>{{ customer.eMail }}</ion-col>
  </ion-row>
  <ion-row>
    <ion-col class="bold" size="auto">Frühstück:</ion-col>
    <ion-col>{{ booking.breakfast ? "Ja" : "Nein" }}</ion-col>
  </ion-row>

  <ion-row v-if="customer.address.street">
    <ion-col class="bold" size="auto">Straße:</ion-col>
    <ion-col>{{ customer.address.street }}</ion-col>
  </ion-row>
  <ion-row v-if="customer.address.postCode">
    <ion-col class="bold" size="auto">PLZ:</ion-col>
    <ion-col>{{ customer.address.postCode }}</ion-col>
  </ion-row>
  <ion-row v-if="customer.address.city">
    <ion-col class="bold" size="auto">Stadt:</ion-col>
    <ion-col>{{ customer.address.city }}</ion-col>
  </ion-row>
  <ion-row v-if="customer.address.country">
    <ion-col class="bold" size="auto">Land:</ion-col>
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

<style scoped>
.bold {
  font-weight: bold;
}
</style>