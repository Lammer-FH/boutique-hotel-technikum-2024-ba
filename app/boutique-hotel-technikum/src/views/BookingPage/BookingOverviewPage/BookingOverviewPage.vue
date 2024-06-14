<template>
  <ion-page>
    <ion-header>
      <ion-grid>
        <ion-row class="ion-justify-content-end">
          <ion-button
            color="danger"
            router-link="/booking">
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

        <ion-row>
          <RoomOverview v-if="booking.room" :room="booking.room"/>
        </ion-row>

        <ion-row class="ion-justify-content-center">
          <h1>{{ `${overnightStays} ${overnightStays === 1 ? "Nacht" : "Nächte"}: ${formatMoney(overnightStays * booking.room!.price)}` }}</h1>
        </ion-row>

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

        <ion-row>
          <ion-button class="confirm" @click="tryBooking">
            Zahlungspflichtig buchen
          </ion-button>
        </ion-row>
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

export function BookingOverviewPageNavigationGuard(to: RouteLocationNormalized) {
  const booking = useBookingStore();

  if (to.fullPath === "/booking-overview" && !booking.isBookingValid) {
    return "/search/period";
  }

  return true;
}

export default {
  components: {RoomOverview},
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

        this.router.push("/home");
      } catch (e) {
        console.error(e);
      }
    }
  },
  unmounted() {
    if (!this.booked) {
      return;
    }
    this.booking.room = undefined;
  }
}
</script>

<style scoped>
ion-button.confirm {
  width: 100%;
}
</style>