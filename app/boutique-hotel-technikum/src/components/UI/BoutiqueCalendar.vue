<template>
  <teleport to="body">
    <ion-page>
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

        <ion-footer>
          <p>
            <span class="day green">1</span>Verfügbar
          </p>

          <p>
            <span class="day red">2</span>Nicht verfügbar
          </p>

          <p class="availability">
            Die Verfügbarkeit bezieht sich immer auf die folgende Nacht des entsprechenden Tages!
          </p>
        </ion-footer>
    </ion-page>
  </teleport>
</template>

<script lang="ts">
import {IonButtons, IonDatetime, IonFooter, IonText} from "@ionic/vue";

export default {
  components: {
    IonDatetime,
    IonButtons,
    IonText,
    IonFooter
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
ion-content {
  --ion-background-color: rgb(0.8, 0.8, 0.8, 0.7);

  .center {
    display: flex;
    width: 100%;
    height: 100%;
    justify-content: center;
    align-items: center;
    align-content: center;

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

/*ion-footer {
  background-color: var(--ion-background-color-step-100);
  padding: 0 0.5rem 0.5rem 0.5rem;
  width: 100%;

  .day {
    display: inline-block;
    border-radius: 1.5rem;
    padding: 0.5rem 0.8rem;
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
}*/
</style>