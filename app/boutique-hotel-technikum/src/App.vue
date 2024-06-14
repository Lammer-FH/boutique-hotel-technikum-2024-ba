<template>
  <ion-app>
    <ion-router-outlet />
  </ion-app>
</template>

<script lang="ts">
import { IonApp, IonRouterOutlet } from '@ionic/vue';
import {useBookingStore} from "@/stores/booking";

export default {
  components: { IonApp, IonRouterOutlet }
}
</script>
