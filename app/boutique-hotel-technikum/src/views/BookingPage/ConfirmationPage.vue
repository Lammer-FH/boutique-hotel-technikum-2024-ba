<template>
  <ion-page>
    <BoutiqueHeader title="Buchungsbestätigung"/>

    <ion-content>
      <ion-grid>
        <ion-row>
          <h1>Vielen dank für Ihre Reservierung, wir haben Ihre Buchung erhalten!</h1>
        </ion-row>

        <BookingPeriod/>

        <ion-row v-if="booking.room">
          <RoomOverview :room="booking.room" :showImages="false"/>
        </ion-row>

        <ContactData/>

        <template v-if="customer.hasAnyAddressInfo">
          <ion-row>
            <h1>Anfahrt:</h1>
          </ion-row>

          <ion-row>
            <iframe
                width="450"
                height="250"
                style="border:0"
                referrerpolicy="no-referrer-when-downgrade"
                :src="`https://www.google.com/maps/embed/v1/directions?key=${apiKey}&origin=${customer.addressToString}&destination=1200+Wien`">
            </iframe>
          </ion-row>
        </template>

        <ion-row><h1>Kontakt:</h1></ion-row>
        <ion-row>E-Mail: <a :href="telephoneHref()">{{ eMail() }}</a></ion-row>
        <ion-row>Telefon: <a :href="eMailHref()">{{ telephone() }}</a></ion-row>
      </ion-grid>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {EBookingState, useBookingStore} from "@/stores/booking";
import BoutiqueHeader from "@/components/UI/TheHeader.vue";
import BookingPeriod from "@/components/BookingPeriod.vue";
import RoomOverview from "@/components/RoomOverview/RoomOverview.vue";
import ContactData from "@/components/ContactData.vue";
import {eMail, eMailHref, telephone, telephoneHref} from "@/utils/ContactData";
import {useCustomerStore} from "@/stores/customer";

export default {
  components: {ContactData, RoomOverview, BookingPeriod, BoutiqueHeader},
  data() {
    return {
      booking: useBookingStore(),
      customer: useCustomerStore()
    }
  },
  computed: {
    apiKey() {
      return import.meta.env.VITE_API_KEY;
    }
  },
  methods: {
    eMailHref() {
      return eMailHref
    },
    telephoneHref() {
      return telephoneHref
    },
    telephone() { return telephone },
    eMail() { return eMail }
  },
  unmounted(){
    this.booking.status = EBookingState.BOOKING;
    this.booking.room = undefined;
  }
}
</script>