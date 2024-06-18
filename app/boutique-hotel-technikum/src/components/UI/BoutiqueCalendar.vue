<template>
 <teleport to="body">
  <ion-page v-if="state === 'loading'">
    <ion-content @click="$emit('finished')">
      <div class="center">
        <div class="background">
          <div>Lädt Verfügbarkeit...</div>
          <ion-button @click="$emit('finished')">Zurück</ion-button>
        </div>
      </div>
    </ion-content>
  </ion-page>

  <ion-page v-if="state === 'loaded'">
    <ion-content @click="$emit('finished')">
      <div class="center">
        <ion-datetime
            presentation="date"
            :highlighted-dates="highlightedDates"
            :readonly="true"
            :year-values="yearValues"
            :show-default-buttons="false"
            @click.stop="">
            <ion-buttons slot="buttons">
              <ion-button color="primary" @click="$emit('finished')">Zurück</ion-button>
            </ion-buttons>
          </ion-datetime>
        </div>
      </ion-content>

      <ion-footer v-if="state === 'loaded'">
        <p><span class="day green">1</span>Verfügbar</p>
        <p><span class="day red">2</span>Nicht verfügbar</p>
        <p class="availability">
          Die Verfügbarkeit bezieht sich immer auf die folgende Nacht des entsprechenden Tages!
        </p>
      </ion-footer>
  </ion-page>

  <ion-page v-if="state === 'error'">
    <ion-content @click="$emit('finished')">
      <div class="center">
        <div class="background">
          <div>Ein Fehler beim Laden der Verfügbarkeit ist aufgetreten, bitte versuche es später erneut!</div>
          <ion-button @click="$emit('finished')" expand="block">Zurück</ion-button>
        </div>
      </div>
    </ion-content>
  </ion-page>
 </teleport>
</template>

<script lang="ts">
import {IonButtons, IonDatetime, IonFooter} from "@ionic/vue";
import {getAvailabilities} from "@/network/getAvailabilities";
import {DatePeriod} from "@/network/dtos/Booking";
import {periodToDatePeriod, isBooked} from "@/utils/periodHelper";

export default {
  components: {
    IonDatetime,
    IonButtons,
    IonFooter
  },
  emits: ["finished"],
  props: {
    roomId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      state: "loaded",
      availabilities: [] as DatePeriod[]
    }
  },
  computed: {
    yearValues() {
      const year = new Date().getFullYear();
      return [year, year+1, year+2, year+3]
    }
  },
  methods: {
    highlightedDates(isoString: string) {
      const date = new Date(isoString);
      const yesterday = new Date();
      yesterday.setDate(yesterday.getDate() -1);
      if (date < yesterday) { return; }

      return {
        backgroundColor: isBooked(date, this.availabilities) ? "red" : "green"
      };
    }
  },
  created() {
    this.state = 'loading';
    const start = new Date();
    const end = new Date((start.getFullYear() + 3) + "-12-31" );
    getAvailabilities(this.roomId, start, end)
        .then(availabilityResponse => {
          this.availabilities = availabilityResponse.booked
              .map(p => periodToDatePeriod(p))
              .sort((a, b) => a.start < b.start ? -1 : 1);
          this.state = "loaded";
        })
        .catch(() => {
          this.state = "error"
        });
  }
}
</script>

<style scoped lang="scss">
ion-content {
  --ion-background-color: rgb(0.8, 0.8, 0.8, 0.7);

  .center {
    display: flex;
    width: 100%;
    height: 100%;
    justify-content: center;
    align-items: center;
    align-content: center;
    flex-direction: column;

    .background {
      padding: 20px;
      margin: 20px;
      max-width: 400px;
      border-radius: 10px;
      background-color: var(--ion-background-color-step-150);
      border: 5px solid black;

      ion-button {
        width: 100%;
      }
    }

    ion-button {
      cursor: pointer;
    }
  }
}

ion-footer {
  background-color: var(--ion-background-color-step-150);

  padding: 0.5rem 0.5rem 0;

  p {
    padding-bottom: 0.2rem;
  }

  .day {
    border-radius: 1.5rem;
    padding: 0.5rem 0.8rem;
    margin-right: 0.5rem;
    width: 10px;
    height: 10px;

    &.green {
      background-color: green;
      margin-bottom: 0.2rem;
    }

    &.red {
      background-color: red;
    }
  }
}
</style>