<template>
  <ion-col size="12" size-sm="12" size-md="6" size-lg="6" size-xl="6">
    <ion-card>
      <ion-card-header>
        <ion-card-title>{{ title }}</ion-card-title>
      </ion-card-header>
      <ion-card-content>
        <template v-if="text">{{ text }}</template>
        <slot v-else></slot>
    </ion-card-content>
    </ion-card>
  </ion-col>
</template>

<script lang="ts">
import { IonCard, IonCardHeader, IonCardContent, IonCardTitle } from '@ionic/vue';

export default {
  components: {
    IonCard, IonCardHeader, IonCardContent, IonCardTitle
  },
  props: {
    title: {
      type: String,
      required: true
    },
    text: {
      type: String,
      required: false
    }
  }
}
</script>