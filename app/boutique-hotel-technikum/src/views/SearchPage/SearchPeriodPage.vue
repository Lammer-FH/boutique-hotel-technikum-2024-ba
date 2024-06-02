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
                :year-values="calendarYears"
                v-model="arrivalDate"
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
                :year-values="calendarYears"
                v-model="departureDate"
            >
              <span slot="title">Abreisetag</span>
            </ion-datetime>
          </ion-col>
        </ion-row>

        <ion-row>
          <ion-col class="label">
            <ion-button @click="searchRooms">Suchen</ion-button>
          </ion-col>
        </ion-row>

        <ion-row>
          <ion-col class="error" v-if="!periodIsValid">
            Der Anreisetag muss vor dem Abreisetag liegen!
          </ion-col>
        </ion-row>

        <ion-row>
          <ion-col>
            <RoomOverview v-bind="rooms">
              <ion-button @click="startBooking(rooms)">Buchen</ion-button>
            </RoomOverview>
          </ion-col>
        </ion-row>
      </ion-grid>
    </ion-content>
  </ion-page>
</template>
<script lang="ts">
import {IonDatetime, useIonRouter} from "@ionic/vue";
import RoomOverview from "@/components/RoomOverview/RoomOverview.vue";
import bedImage from "@/assets/img/penthouse/Bed.jpg";
import bathImage from "@/assets/img/penthouse/Bath.jpg";
import minibarImage from "@/assets/img/penthouse/Minibar.jpg";
import toiletImage from "@/assets/img/penthouse/Toilet.jpg";
import {useBookingStore} from "@/stores/booking";
import Room from "@/stores/Room";

export default {
  components: {
    RoomOverview,
    IonDatetime
  },
  setup() {
    const booking = useBookingStore();
    const router = useIonRouter();
    return {
      router,
      booking
    }
  },
  data() {
    return {
      arrivalDate: new Date().toISOString(),
      departureDate: new Date().toISOString(),
      searchArrivalDate: new Date(),
      searchDepartureDate: new Date(),
      rooms: {
        id: 1,
        name: "Penthouse Suite",
        images: [
          {
            src: bedImage,
            alt: "Doppelbett mit hervorragender Aussicht auf die Innenstadt!"
          },
          {
            src: bathImage,
            alt: "Wunderschönes Badezimmer mit Badewanne, Dusche und Whirlpool!"
          },
          {
            src: minibarImage,
            alt: "Minibar mit riesiger Auswahl an Getränken!"
          },
          {
            src: toiletImage,
            alt: "Designer-Toilette mit Waschbecken und Spiegel!"
          }
        ],
        description: "Luxuriöse Suite im obersten Stock des Boutique-Hotel-Technikum.",
        beds: 2,
        extras: [
          {
            icon: "water",
            name: "Badezimmer"
          },
          {
            icon: "wine",
            name: "Minibar"
          },
          {
            icon: "tv",
            name: "Fernseher"
          },
          {
            icon: "thermometer",
            name: "Klimaanlage"
          }
        ],
        price: 1_999
      }
    }
  },
  computed: {
    calendarYears() {
      const thisYear = new Date().getFullYear();
      return [thisYear, thisYear+1, thisYear+2]
    },
    periodIsValid() {
      return new Date(this.arrivalDate) < new Date(this.departureDate);
    }
  },
  methods: {
    searchRooms() {
      if (!this.periodIsValid) {
        return;
      }

      this.searchArrivalDate = new Date(this.arrivalDate);
      this.searchDepartureDate = new Date(this.departureDate);
    },
    startBooking(room: Room) {
      if (this.searchArrivalDate >= this.searchDepartureDate) {
        return;
      }
      this.booking.room = room;
      this.booking.arrival = this.searchArrivalDate;
      this.booking.departure = this.searchDepartureDate;

      this.router.navigate("/booking")
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