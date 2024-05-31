<template>
  <ion-col size="12" size-sm="6" size-md="4" size-lg="4" size-xl="4">
    <ion-card>
      <ion-img
        :src="imageSrc"
        :alt="imageAlt"
      ></ion-img>
      <ion-card-content>
        {{ text }}
      </ion-card-content>
    </ion-card>
  </ion-col>
</template>

<script lang="ts">
import { IonCardContent, IonCard } from '@ionic/vue';

export default {
  components: {
    IonCardContent,
    IonCard
  },
  props: {
    imageSrc: {
      type: String,
      required: true
    },
    imageAlt: {
      type: String,
      required: true
    },
    text: {
      type: String,
      required: true
    }
  }
}
</script>