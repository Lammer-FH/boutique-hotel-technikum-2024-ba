<template>
  <ion-page>
    <ion-header>
      <ion-grid>
        <ion-row class="ion-justify-content-end">
          <ion-button
              color="danger"
              router-link="/search/period"
              router-direction="back"
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
          <RoomOverview :room="booking.room!"/>
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
import {RouteLocationNormalized} from "vue-router";
import {Address} from "@/stores/dataStructures/CustomerInfo";
import {useCustomerStore} from "@/stores/customer";

export function BookingPageNavigationGuard(to: RouteLocationNormalized, from: RouteLocationNormalized) {
  const booking = useBookingStore();

  if (to.fullPath === "/booking" && !booking.isRoomValid) {
    return "/search/period";
  }

  return true;
}

export default {
  components: {RoomOverview, TheBookingForm},
  data() {
    return {
      booking: useBookingStore(),
      customer: useCustomerStore(),
      router: useIonRouter()
    }
  },
  methods: {
    toOverviewPage(inputs: {
      firstName: string,
      lastName: string,
      eMail: string,
      breakfast: boolean,
      address: Address
    }) {
      this.customer.setName(inputs.firstName, inputs.lastName);
      this.customer.setEMail(inputs.eMail);
      this.customer.setAddress(inputs.address);

      this.booking.setBreakfast(inputs.breakfast);
      this.router.push("/booking-overview");
    }
  }
}
</script>