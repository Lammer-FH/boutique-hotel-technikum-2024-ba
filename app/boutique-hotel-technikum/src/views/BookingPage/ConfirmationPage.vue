<template>
  <ion-page>
    <template v-if="booking.state === EBookingState.BOOKED">
      <BoutiqueHeader title="Buchungsbestätigung"/>

      <ion-content>
        <ion-grid>
          <ion-row>
            <h1>Vielen dank für Ihre Reservierung, wir haben Ihre Buchung erhalten!</h1>
          </ion-row>

         <BookingPeriod/>

          <ion-row>
            <RoomOverview :room="booking.room"/>
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
          <ion-row>E-Mail:&nbsp;<a :href="telephoneHref()">{{ eMail() }}</a></ion-row>
          <ion-row>Telefon:&nbsp;<a :href="eMailHref()">{{ telephone() }}</a></ion-row>
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
import {eMail, eMailHref, telephone, telephoneHref} from "@/utils/ContactData";
import {useCustomerStore} from "@/stores/customer";
import {RouteLocationNormalized} from "vue-router";
import IonRowCol from "@/components/UI/IonRowCol.vue";
import {useIonRouter} from "@ionic/vue";

export function ConfirmationPageNavigationGuard(to: RouteLocationNormalized, from: RouteLocationNormalized) {
  const booking = useBookingStore();
  const customer = useCustomerStore();

  if (to.fullPath === "/confirmation" && (!booking.isRoomValid || !customer.isValid)) {
    return "/search/period";
  }

  return true;
}

export default {
  components: {IonRowCol, ContactData, RoomOverview, BookingPeriod, BoutiqueHeader},
  data() {
    return {
      booking: useBookingStore(),
      customer: useCustomerStore(),
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
    eMailHref() {
      return eMailHref
    },
    telephoneHref() {
      return telephoneHref
    },
    telephone() { return telephone },
    eMail() { return eMail }
  },
  beforeRouteLeave() {
    this.booking.$reset();
  }
}
</script>