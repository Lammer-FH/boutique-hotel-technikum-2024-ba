<template>
  <ion-page>
    <ion-header>
      <ion-grid>
        <ion-row class="ion-justify-content-end">
          <ion-button
            color="danger"
            @click="back()">
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
          <RoomOverview v-bind="booking.room!"/>
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
          <ion-button class="confirm">
            Zahlungspflichtig bestellen
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

export default {
  components: {RoomOverview},
  setup() {
    const booking = useBookingStore();
    const router = useIonRouter();

    return {
      booking,
      router
    }
  },
  computed: {
    validData() {
      return this.booking.arrival && this.booking.departure && this.booking.room && this.booking.firstName && this.booking.lastName && this.booking.eMail && this.booking.breakfast;
    },
    overnightStays() {
      return this.booking.departure!.getDay() -this.booking.arrival!.getDay();
    }
  },
  methods: {
    formatMoney,
    back() {
      this.router.navigate("/booking");
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
ion-button.confirm {
  width: 100%;
}
</style>