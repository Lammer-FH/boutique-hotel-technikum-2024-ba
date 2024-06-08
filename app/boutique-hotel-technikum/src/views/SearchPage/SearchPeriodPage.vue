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

        <ion-row>
          <ion-col class="error" v-if="periodInvalid">
            Der Anreisetag muss vor dem Abreisetag liegen!
          </ion-col>
        </ion-row>

        <ion-row>
          <ion-col class="label">
            <ion-button @click="searchRooms">Suchen</ion-button>
          </ion-col>
        </ion-row>


        <ion-row>
          <ion-col>
            <RoomOverview v-for="room in availableRooms.rooms" :room="room">
              <ion-button>Buchen</ion-button>
            </RoomOverview>
          </ion-col>
        </ion-row>
      </ion-grid>
    </ion-content>
  </ion-page>
</template>
<script lang="ts">
import BoutiqueHeader from "@/components/UI/TheHeader.vue";
import {IonDatetime} from "@ionic/vue";
import RoomOverview from "@/components/RoomOverview/RoomOverview.vue";
import {useAvailableRoomsByPeriodStore} from "@/store/availableRoomByPeriod";

export default {
  components: {
    RoomOverview,
    BoutiqueHeader,
    IonDatetime
  },
  data() {
    return {
      availableRooms: useAvailableRoomsByPeriodStore()
    }
  },
  computed: {
    years() {
      const thisYear = new Date().getFullYear();
      return [thisYear, thisYear+1, thisYear+2]
    },
    periodInvalid() {
      return new Date(this.availableRooms.arrival) >= new Date(this.availableRooms.departure);
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