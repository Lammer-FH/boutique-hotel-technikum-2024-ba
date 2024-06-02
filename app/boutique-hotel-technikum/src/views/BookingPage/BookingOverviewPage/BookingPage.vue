<template>
  <ion-page>
    <ion-header>
      <ion-grid>
        <ion-row class="ion-justify-content-end">
          <ion-button
              color="danger"
              @click="cancel()"
          >{{ validData ? "Abbrechen" : "Zurück zur Raumübersicht"}}</ion-button>
        </ion-row>
        <ion-row>
          <h1 v-if="validData">Buchungsübersicht</h1>
          <h1 v-else>Fehler: Keine Buchungsdaten vorhanden, Wähle zuerst im Such-Tab eine Zimmer zum Buchen aus!</h1>
        </ion-row>
      </ion-grid>
    </ion-header>

    <ion-content v-if="validData">
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

      <TheBookingForm/>
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
    }
  }
}
</script>

<style scoped>
ion-button.change-room {
  width: 100%;
}
</style>