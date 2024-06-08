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
                v-model="availableRooms.arrival"
            >
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
                v-model="availableRooms.departure"
            >
              <span slot="title">Abreisetag</span>
            </ion-datetime>
          </ion-col>
        </ion-row>

        <ion-row v-if="periodInvalid">
          <ion-col class="error">
            Der Anreisetag muss vor dem Abreisetag liegen!
          </ion-col>
        </ion-row>

        <ion-row>
          <ion-col>
            <ion-button @click="searchRooms">Suchen</ion-button>
          </ion-col>
        </ion-row>

        <IonRowCol v-if="availableRooms.state === ERoomSearchState.Loaded" v-for="room in availableRooms.rooms">
          <RoomOverview :room="room">
            <ion-button>Buchen</ion-button>
          </RoomOverview>
        </IonRowCol>

        <IonRowCol v-if="noAvailableRooms">
            Leider sind keien Freien Zimmer während dieses Zeitraumes mehr verfügbar...
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
import {IonDatetime} from "@ionic/vue";
import RoomOverview from "@/components/RoomOverview/RoomOverview.vue";
import {ERoomSearchState, useAvailableRoomsByPeriodStore} from "@/store/availableRoomByPeriod";
import TheRoomPagination from "@/components/RoomOverview/TheRoomPagination.vue";

export default {
  components: {
    RoomOverview,
    BoutiqueHeader,
    IonDatetime,
    TheRoomPagination
  },
  data() {
    return {
      availableRooms: useAvailableRoomsByPeriodStore()
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
      return new Date(this.availableRooms.arrival) >= new Date(this.availableRooms.departure);
    },
    noAvailableRooms() {
      return this.availableRooms.state === ERoomSearchState.Loaded && this.availableRooms.rooms.length <= 0;
    }
  },
  methods: {
    searchRooms() {
      if (this.periodInvalid) {
        return;
      }

      this.availableRooms.setCurrentPage(1);
    }
  }
}
</script>

<style scoped>
ion-button {
  width: 100%;
}

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