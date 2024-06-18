<template>
  <ion-page>
    <BoutiqueHeader>
      <ion-title>Buchungsübersicht</ion-title>
      <ion-buttons slot="end">
        <ion-button 
              color="danger"
              router-link="/booking"
              router-direction="back"
          >Zurück</ion-button>
      </ion-buttons>
    </BoutiqueHeader>

    <ion-content>
      <ion-grid fixed>
        <BookingPeriod/>

        <ion-row v-if="booking.room">
          <RoomOverview :room="booking.room"/>
        </ion-row>

        <ion-row class="ion-justify-content-center">
          <ion-col><h2>{{ priceText }}</h2></ion-col>
        </ion-row>

        <ContactData/>

        <ion-button class="confirm" @click="tryBooking" expand="block">
          Zahlungspflichtig buchen
        </ion-button>
      </ion-grid>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {useIonRouter} from "@ionic/vue";
import {EBookingState, useBookingStore} from "@/stores/booking";
import RoomOverview from "@/components/RoomOverview/RoomOverview.vue";
import {formatMoney} from "@/utils/Formatter";
import {RouteLocationNormalized} from "vue-router";
import bookRoom from "@/network/bookRoom";
import ContactData from "@/components/ContactData.vue";
import BookingPeriod from "@/components/BookingPeriod.vue";
import {useCustomerStore} from "@/stores/customer";
import BoutiqueHeader from "@/components/UI/TheHeader.vue";

export default {
  components: {BookingPeriod, ContactData, RoomOverview, BoutiqueHeader},
  data() {
    return {
      booking: useBookingStore(),
      customer: useCustomerStore(),
      router: useIonRouter(),
      booked: false
    }
  },
  computed: {
    overnightStays() {
      return this.booking.departure!.getDate() - this.booking.arrival!.getDate();
    },
    priceText() {
      const total = this.overnightStays * this.booking.room!.price;
      return `${this.overnightStays} ${this.overnightStays === 1 ? "Nacht" : "Nächte"}: ${formatMoney(total)}`
    }
  },
  methods: {
    async tryBooking() {
      try {
        await bookRoom(
            this.booking!.room!.id,
            {
              firstName: this.customer.firstName,
              secondName: this.customer.lastName,
              email: this.customer.eMail,
              birthDate: "1995-01-01"
            },
            this.booking.breakfast,
            {
              start: this.booking.arrival!.toISOString().slice(0, 10),
              end: this.booking.departure!.toISOString().slice(0, 10)
            });

        this.booking.setState(EBookingState.BOOKED);
      } catch (error) {
        if ((error as any)?.response?.status === 409) {
          this.booking.setState(EBookingState.ROOM_NOT_AVAILABLE_ERROR);
        } else {
          this.booking.setState(EBookingState.ERROR);
        }
      }
      this.router.replace("/confirmation");
    }
  }
}
</script>