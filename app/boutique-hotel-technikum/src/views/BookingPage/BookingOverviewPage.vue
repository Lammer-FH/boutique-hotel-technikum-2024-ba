<template>
  <ion-page>

    <ion-header>
      <ion-grid>
        <ion-row class="ion-justify-content-end">
          <ion-button
            color="danger"
            router-link="/booking"
            router-direction="back">
            Zurück
          </ion-button>
        </ion-row>
        <ion-row>
          <h1>Buchungsübersicht</h1>
        </ion-row>
      </ion-grid>
    </ion-header>

    <ion-content>
      <ion-grid>
        <BookingPeriod/>

        <ion-row v-if="booking.room">
          <RoomOverview :room="booking.room"/>
        </ion-row>

        <ion-row class="ion-justify-content-center">
          <h1>{{ priceText }}</h1>
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

export function BookingOverviewPageNavigationGuard(to: RouteLocationNormalized) {
  const booking = useBookingStore();
  const customer = useCustomerStore();

  if (to.fullPath === "/booking-overview" && (!booking.isRoomValid || !customer.isValid)) {
    return "/search/period";
  }

  return true;
}

export default {
  components: {BookingPeriod, ContactData, RoomOverview},
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
        this.router.replace("/confirmation");
      } catch (e) {
        console.error(e);
      }
    }
  }
}
</script>