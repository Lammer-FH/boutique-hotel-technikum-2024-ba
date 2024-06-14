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
        <ion-row>
          <ion-text>Anreise: <span>{{ booking.prettyArrival }}</span></ion-text>
        </ion-row>
        <ion-row>
          <ion-text>Abreise: <span>{{ booking.prettyDeparture }}</span></ion-text>
        </ion-row>

        <ion-row v-if="booking.room">
          <RoomOverview :room="booking.room"/>
        </ion-row>

        <ion-row class="ion-justify-content-center">
          <h1>{{ `${overnightStays} ${overnightStays === 1 ? "Nacht" : "Nächte"}: ${formatMoney(overnightStays * booking.room!.price)}` }}</h1>
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
import {useBookingStore} from "@/stores/booking";
import RoomOverview from "@/components/RoomOverview/RoomOverview.vue";
import {formatMoney} from "@/utils/Formatter";
import {RouteLocationNormalized} from "vue-router";
import bookRoom from "@/network/bookRoom";
import ContactData from "@/components/ContactData.vue";

export function BookingOverviewPageNavigationGuard(to: RouteLocationNormalized) {
  const booking = useBookingStore();

  if (to.fullPath === "/booking-overview" && !booking.isBookingValid) {
    return "/search/period";
  }

  return true;
}

export default {
  components: {ContactData, RoomOverview},
  data() {
    return {
      booking: useBookingStore(),
      router: useIonRouter(),
      booked: false
    }
  },
  computed: {
    overnightStays() {
      return this.booking.departure!.getDate() - this.booking.arrival!.getDate();
    }
  },
  methods: {
    formatMoney,
    async tryBooking() {
      try {
        await bookRoom(
            this.booking!.room!.id,
            {
              firstName: this.booking.firstName,
              secondName: this.booking.lastName,
              email: this.booking.eMail,
              birthDate: "1995-01-01"
            },
            this.booking.breakfast,
            {
              start: this.booking.arrival!.toISOString().slice(0, 10),
              end: this.booking.departure!.toISOString().slice(0, 10)
            });

        this.booking.status = "booked";
        this.router.replace("/confirmation");
      } catch (e) {
        console.error(e);
      }
    }
  }
}
</script>