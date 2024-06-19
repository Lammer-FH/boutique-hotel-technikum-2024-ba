<template>
  <ion-page>
    <ion-tabs>
      <ion-router-outlet></ion-router-outlet>
      <ion-tab-bar slot="bottom" class="tab-bar">
        <ion-tab-button tab="home" href="/home">
          <ion-icon aria-hidden="true" :icon="icons.home" />
          <ion-label>Home</ion-label>
        </ion-tab-button>

        <ion-tab-button tab="search" href="/search">
          <ion-icon aria-hidden="true" :icon="icons.search" />
          <ion-label>Suche</ion-label>
        </ion-tab-button>

        <ion-tab-button tab="about" href="/about">
          <ion-icon aria-hidden="true" :icon="icons.informationCircle" />
          <ion-label>Über</ion-label>
        </ion-tab-button>

        <ion-tab-button tab="impressum" href="/impressum">
          <ion-icon aria-hidden="true" :icon="icons.help" />
          <ion-label>Impressum</ion-label>
        </ion-tab-button>
      </ion-tab-bar>
    </ion-tabs>
  </ion-page>
</template>

<script lang="ts">
import { IonTabBar, IonTabButton, IonTabs, IonLabel, IonRouterOutlet } from '@ionic/vue';
import { home, informationCircle, help, search } from 'ionicons/icons';

export default {
  name: "Navigation",
  data() {
    return {
      icons: {
        home, informationCircle, help, search
      }
    }
  },
  components: {
    IonTabBar, IonTabButton, IonTabs, IonLabel, IonRouterOutlet
  }
}
</script>
