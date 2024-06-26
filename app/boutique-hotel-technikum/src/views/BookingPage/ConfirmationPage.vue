<template>
  <ion-page>
    <template v-if="booking.state === EBookingState.BOOKED">
      <BoutiqueHeader title="Buchungsbestätigung"/>

      <ion-content>
        <ion-grid fixed>
          <ion-row>
            <ion-col><h2>Vielen Dank für Ihre Reservierung, wir haben Ihre Buchung erhalten!</h2></ion-col>
          </ion-row>

          <BookingPeriod :arrival="booking.prettyArrival ?? ''" :departure="booking.prettyDeparture ?? ''"/>

          <ion-row>
            <ion-col size="12" size-sm="12" size-md="6" size-lg="6" size-xl="6">
              <RoomOverview :room="booking.room"/>
            </ion-col>
            <ion-col size="12" size-sm="12" size-md="6" size-lg="6" size-xl="6">

              <ContactData/>
              <ion-row>
                <ion-col><h3>Anfahrt:</h3></ion-col>
              </ion-row>
              <ion-row>
                <ion-col>
                  <ion-text>{{ booking.room?.hotel.directions }}</ion-text>
                </ion-col>
              </ion-row>

              <template v-if="customer.hasAnyAddressInfo">
                <ion-row>
                  <ion-col>
                    <iframe
                        class="embedded-map"
                        width="100%"
                        height="250"
                        style="border:0"
                        referrerpolicy="no-referrer-when-downgrade"
                        :src="`https://www.google.com/maps/embed/v1/directions?key=${apiKey}&origin=${customer.addressToString}&destination=${booking.room?.hotel.address}`">
                    </iframe>
                  </ion-col>
                </ion-row>
              </template>

              <ion-row>
                <ion-col><h3>Kontakt:</h3></ion-col>
              </ion-row>
              <ion-row>
                <ion-col>
                  <span class="bold">Telefon:&nbsp;</span><a :href="telephoneHref()">{{ booking.room?.hotel.contactPhoneNumber }}</a>
                </ion-col>
              </ion-row>
            </ion-col>
          </ion-row>
        </ion-grid>
      </ion-content>
    </template>

    <template v-if="booking.state === EBookingState.ROOM_NOT_AVAILABLE_ERROR">
      <BoutiqueHeader title="Fehler"/>
      <ion-grid>
        <IonRowCol>
          Leider ist das gewünschte Zimmer nicht mehr verfügbar, Sie können sich aber ein neues aussuchen!
        </IonRowCol>
        <IonRowCol>
          <ion-button expand="block" router-link="/search/period">Zurück zur Auswahl</ion-button>
        </IonRowCol>
      </ion-grid>
    </template>
  </ion-page>
</template>

<script lang="ts">
import {EBookingState, useBookingStore} from "@/stores/booking";
import BoutiqueHeader from "@/components/UI/TheHeader.vue";
import BookingPeriod from "@/components/BookingPeriod.vue";
import RoomOverview from "@/components/RoomOverview/RoomOverview.vue";
import ContactData from "@/components/ContactData.vue";
import {eMail} from "@/utils/ContactData";
import {useCustomerStore} from "@/stores/customer";
import IonRowCol from "@/components/UI/IonRowCol.vue";
import {useIonRouter} from "@ionic/vue";
import {useAvailableRoomsByPeriodStore} from "@/stores/availableRoomByPeriod";

export default {
  name: "ConfirmationPage",
  components: {IonRowCol, ContactData, RoomOverview, BookingPeriod, BoutiqueHeader},
  data() {
    return {
      booking: useBookingStore(),
      customer: useCustomerStore(),
      availableRooms: useAvailableRoomsByPeriodStore(),
      router: useIonRouter()
    }
  },
  computed: {
    EBookingState() {
      return EBookingState
    },
    apiKey() {
      return import.meta.env.VITE_API_KEY;
    }
  },
  methods: {
    telephoneHref() {
      return "tel:" + this.booking.room?.hotel.contactPhoneNumber;
    },
    eMail() { return eMail }
  },
  beforeRouteLeave() {
    this.booking.$reset();
    this.availableRooms.$reset();
  }
}
</script>
<style scoped>
.bold {
  font-weight: bold;
}

@media print {
  .tab-bar, .app-header, .embedded-map {
    display: none !important;
  }

  .container {
    padding: 0 !important;
  }
}
</style>