<template>
  <ion-page>
    <BoutiqueHeader title="Freie Zimmer suchen"/>
    <ion-content>
      <ion-grid>
        <ion-row>
          <ion-col>
            <ion-datetime
                ref="arrival"
                :min="new Date().toISOString()"
                :prefer-wheel="true"
                presentation="date"
                :year-values="years"
                v-model="searchArrival">
              <span slot="title">Anreisetag</span>
            </ion-datetime>
          </ion-col>
          <ion-col>
            <ion-datetime
                ref="departure"
                :min="new Date().toISOString()"
                :prefer-wheel="true"
                presentation="date"
                :year-values="years"
                v-model="searchDeparture">
              <span slot="title">Abreisetag</span>
            </ion-datetime>
          </ion-col>
        </ion-row>

        <ion-row v-if="periodInvalid">
          <ion-col class="error">
            Der Anreisetag muss vor dem Abreisetag liegen!
          </ion-col>
        </ion-row>

        <IonRowCol>
            <ion-button @click="searchRooms" expand="block">Suchen</ion-button>
        </IonRowCol>

        <IonRowCol v-if="availableRooms.state === ERoomSearchState.Loaded" v-for="room in availableRooms.rooms">
          <RoomOverview :room="room">
            <ion-button router-direction="forward" @click="goToBooking(room)" expand="block">Buchen</ion-button>
          </RoomOverview>
        </IonRowCol>

        <IonRowCol v-if="noAvailableRooms">
          Leider sind während dieses Zeitraums keine freien Zimmer mehr verfügbar...
        </IonRowCol>

        <IonRowCol v-if="availableRooms.state === ERoomSearchState.Loading">
          Suche nach freien Zimmern...
        </IonRowCol>

        <IonRowCol v-if="availableRooms.state === ERoomSearchState.Error">
            Ein Fehler während der Suche nach freien Zimmern ist aufgetreten, bitte versuche es später erneut!
        </IonRowCol>
      </ion-grid>
    </ion-content>

    <ion-footer>
      <TheRoomPagination :page-object="availableRooms"/>
    </ion-footer>
  </ion-page>
</template>

<script lang="ts">
import BoutiqueHeader from "@/components/UI/TheHeader.vue";
import {IonDatetime, useIonRouter} from "@ionic/vue";
import RoomOverview from "@/components/RoomOverview/RoomOverview.vue";
import {ERoomSearchState, useAvailableRoomsByPeriodStore} from "@/stores/availableRoomByPeriod";
import TheRoomPagination from "@/components/RoomOverview/TheRoomPagination.vue";
import {Room} from "@/network/dtos/Room";
import {useBookingStore} from "@/stores/booking";
import router from "@/router";

export default {
  components: {
    RoomOverview,
    BoutiqueHeader,
    IonDatetime,
    TheRoomPagination
  },
  data() {
    return {
      availableRooms: useAvailableRoomsByPeriodStore(),
      booking: useBookingStore(),
      searchArrival: useAvailableRoomsByPeriodStore().arrival,
      searchDeparture: useAvailableRoomsByPeriodStore().departure,
      router: useIonRouter()
    }
  },
  computed: {
    ERoomSearchState() {
      return ERoomSearchState
    },
    years() {
      const thisYear = new Date().getFullYear();
      return [thisYear, thisYear+1, thisYear+2]
    },
    periodInvalid() {
      return new Date(this.searchArrival) >= new Date(this.searchDeparture);
    },
    noAvailableRooms() {
      return this.availableRooms.state === ERoomSearchState.Loaded && this.availableRooms.rooms.length <= 0;
    }
  },
  methods: {
    searchRooms() {
      if (this.periodInvalid) { return; }

      this.availableRooms.setArrival(this.searchArrival);
      this.availableRooms.setDeparture(this.searchDeparture);
      this.availableRooms.setCurrentPage(1);
    },
    goToBooking(room: Room) {
      this.booking.setArrival(new Date(this.availableRooms.arrival));
      this.booking.setDeparture(new Date(this.availableRooms.departure));
      this.booking.setRoom(room);
      router.push("/booking");
    }
  }
}
</script>

<style scoped>
ion-col {
  display: flex;
  align-items: center;
  justify-content: center;
}

ion-col.error {
  background-color: rgb(255, 204, 204);
  color: black;
  font-size: 1.3rem;
  border: solid black 3px;
  border-radius: 1rem;
}
</style>