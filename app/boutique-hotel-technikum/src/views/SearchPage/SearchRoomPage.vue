<template>
  <ion-page>
    <ion-content v-if="availableRooms.loaded">
      <RoomOverview v-for="room in availableRooms.rooms" :room="room" :key="room.id">
        <ion-button @click="showCalendarFor = room.id" expand="block">Verfügbarkeit prüfen</ion-button>
      </RoomOverview>
      <BoutiqueCalendar v-if="showCalendarFor" :room-id="showCalendarFor" @finished="showCalendarFor = undefined"/>
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
import {useAvailableRoomsStore} from "@/stores/availableRooms";

export default {
  name: "SearchRoomPage",
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
  },
  beforeRouteLeave() {
    this.showCalendarFor = undefined;
  }
}
</script>