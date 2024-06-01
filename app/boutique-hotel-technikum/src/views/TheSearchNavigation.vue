<template>
  <ion-page>
    <ion-tabs>
      <ion-header>
        <ion-tab-bar slot="bottom">
          <ion-tab-button tab="room" href="/search/room">
            <ion-label>Zimmer</ion-label>
          </ion-tab-button>
          <ion-tab-button tab="period" href="/search/period">
            <ion-label>Zeitraum</ion-label>
          </ion-tab-button>
        </ion-tab-bar>
      </ion-header>

      <ion-router-outlet></ion-router-outlet>
    </ion-tabs>
  </ion-page>
</template>

<script lang="ts">
import { IonTabButton, IonTabs, IonLabel, IonPage, IonRouterOutlet, IonTabBar } from '@ionic/vue';

export default {
  components: {
    IonTabButton, IonTabs, IonLabel, IonPage, IonRouterOutlet, IonTabBar
  }
}
</script>

<style scoped>
ion-tab-bar {
  padding: 10px;
}

ion-tab-button {
  font-size: 1.2rem;
  font-weight: bolder;
  padding: 0px;
  color: white;
  border: solid 2px rgb(33, 95, 154);

  &.tab-selected {
    --background: rgb(33, 95, 154);
  }
}
</style>