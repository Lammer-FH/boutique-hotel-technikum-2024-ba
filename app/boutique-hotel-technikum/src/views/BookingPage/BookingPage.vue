<template>
  <ion-page>
    <BoutiqueHeader>
      <ion-title>Buchungsübersicht</ion-title>
      <ion-buttons slot="end">
        <ion-button 
              color="danger"
              router-link="/search/period"
              router-direction="back"
          >Abbrechen</ion-button>
      </ion-buttons>
    </BoutiqueHeader>

    <ion-content>
      <ion-grid fixed>
        <BookingPeriod/>
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
import {onIonViewWillEnter, useIonRouter} from "@ionic/vue";
import RoomOverview from "@/components/RoomOverview/RoomOverview.vue";
import TheBookingForm from "@/views/BookingPage/TheBookingForm.vue";
import {RouteLocationNormalized} from "vue-router";
import {Address} from "@/stores/dataStructures/CustomerInfo";
import {useCustomerStore} from "@/stores/customer";
import BookingPeriod from "@/components/BookingPeriod.vue";
import BoutiqueHeader from "@/components/UI/TheHeader.vue";

export function BookingPageNavigationGuard(to: RouteLocationNormalized, from: RouteLocationNormalized) {
  const booking = useBookingStore();

  if (to.fullPath === "/booking" && !booking.isRoomValid) {
    return "/search/period";
  }

  return true;
}

export default {
  components: {BookingPeriod, RoomOverview, TheBookingForm, BoutiqueHeader},
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