<template>
  <teleport to="body">
    <div @click="$emit('finished')">

      <div>
        <ion-datetime
            presentation="date"
            :highlighted-dates="highlightedDates"
            :readonly="true"
            :year-values="yearValues"
            @click.stop="">
        </ion-datetime>

        <ion-text class="description">
          <ion-text class="day green">1</ion-text>
          Verfügbar für nächste Nacht<br>

          <ion-text class="day red">2</ion-text>
          Nicht verfügbar
          <ion-button color="primary" @click="$emit('finished')">Zurück</ion-button>
        </ion-text>
      </div>

    </div>
  </teleport>
</template>

<script lang="ts">
import {IonButtons, IonDatetime, IonText} from "@ionic/vue";

export default {
  components: {
    IonDatetime,
    IonButtons,
    IonText
  },
  emits: ["finished"],
  computed: {
    yearValues() {
      const year = new Date().getFullYear();

      return [year, year+1, year+2, year+3]
    }
  },
  methods: {
    highlightedDates(isoString: string) {
      //TODO Use the data from the backand to whether the Day is available or not
      const date = new Date(isoString);
      if (date < new Date()) {
        return;
      }
      return Math.random() < 0.5 ? {
        backgroundColor: 'green'
      } : {
        backgroundColor: 'red'
      };
    }
  }
}
</script>

<style scoped lang="scss">
div {
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0,0,0,0.8);
}

.day {
  display: inline-block;
  border-radius: 1.5rem;
  padding: 0.5rem 0.8rem;

  &.green {
    background-color: green;
    margin-bottom: 0.2rem;
  }

  &.red {
    background-color: red;
  }
}

.description {
  background-color: var(--ion-background-color-step-100);
  padding: 0 0.5rem 0.5rem 0.5rem;
  width: 21.875rem;
}
ion-button {
  width: 100%;
}
</style>