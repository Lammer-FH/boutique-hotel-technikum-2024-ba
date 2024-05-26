<template>
  <ion-col size="12" size-sm="12" size-md="6" size-lg="6" size-xl="6">
    <ion-card>
      <ion-card-header>
        <ion-card-title>{{ title }}</ion-card-title>
      </ion-card-header>
      <ion-card-content>
        {{ text }}
        <slot></slot>
    </ion-card-content>
    </ion-card>
  </ion-col>
</template>

<script setup lang="ts">
import { IonCard, IonCardHeader, IonCardContent, IonCardTitle, IonCol } from '@ionic/vue';
defineProps({
  title: String,
  text: String,
});
</script>