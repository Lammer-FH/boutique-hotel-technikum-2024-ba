<template>
  <ion-page>
    <ion-content v-if="availableRooms.loaded">
      <RoomOverview v-for="room in availableRooms.rooms" :room="room">
        <ion-button @click="showCalendarFor = room.id">Verfügbarkeit prüfen</ion-button>
      </RoomOverview>
      <BoutiqueCalendar v-if="showCalendarFor" @finished="showCalendarFor = undefined"/>
    </ion-content>

    <ion-content v-else>
      <ion-grid>
        <ion-row class="ion-justify-content-center">
          {{ availableRooms.fetchError ? "Es ist ein Fehler aufgetreten, bitte versuche es später erneut!" : "Zimmer werden geladen..." }}
        </ion-row>
      </ion-grid>
    </ion-content>

    <ion-footer>
      <TheRoomPagination :page-object="availableRooms"/>
    </ion-footer>
  </ion-page>
</template>

<script lang="ts">
import RoomOverview from "@/components/RoomOverview/RoomOverview.vue";
import TheRoomPagination from "@/components/RoomOverview/TheRoomPagination.vue";
import BoutiqueCalendar from "@/components/UI/BoutiqueCalendar.vue";
import {useAvailableRoomsStore} from "@/store/availableRooms";

export default {
  components: {
    BoutiqueCalendar,
    TheRoomPagination,
    RoomOverview
  },
  data() {
    return {
      availableRooms: useAvailableRoomsStore(),
      showCalendarFor: undefined as number | undefined
    }
  },
  created() {
    this.availableRooms.fetchRooms();
  }
}
</script>

<style scoped>
ion-button {
  width: 100%;
}
</style>