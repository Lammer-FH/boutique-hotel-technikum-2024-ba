<template>
  <ion-page>
    <ion-header>
      <ion-grid>
        <ion-row class="ion-justify-content-end">
          <ion-button
              color="danger"
              @click="cancel()"
          >Abbrechen</ion-button>
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
          <RoomOverview v-bind="booking.room!">
            <ion-button class="change-room">Zeitraum/Zimmer ändern</ion-button>
          </RoomOverview>
        </ion-row>
      </ion-grid>

      <TheBookingForm @confirmed="toOverviewPage"/>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {useBookingStore} from "@/stores/booking";
import {useIonRouter} from "@ionic/vue";
import RoomOverview from "@/components/RoomOverview/RoomOverview.vue";
import TheBookingForm from "@/views/BookingPage/TheBookingForm.vue";

export default {
  components: {RoomOverview, TheBookingForm},
  setup() {
    const booking = useBookingStore();
    const router = useIonRouter();
    return {
      router,
      booking
    }
  },
  computed: {
    validData() {
      return this.booking.arrival && this.booking.departure && this.booking.room;
    }
  },
  methods: {
    cancel() {
      this.router.navigate("/search/period");
    },
    toOverviewPage(inputs: {
      firstName: string,
      lastName: string,
      eMail: string,
      breakfast: "yes" | "no"
    }) {
      this.booking.firstName = inputs.firstName;
      this.booking.lastName = inputs.lastName;
      this.booking.eMail = inputs.eMail;
      this.booking.breakfast = inputs.breakfast === "yes";

      this.router.navigate("/booking-overview");
    }
  },
  beforeCreate() {
    if (!this.validData) {
      this.router.navigate("/search/period");
    }
  }
}
</script>

<style scoped>
ion-button.change-room {
  width: 100%;
}
</style>