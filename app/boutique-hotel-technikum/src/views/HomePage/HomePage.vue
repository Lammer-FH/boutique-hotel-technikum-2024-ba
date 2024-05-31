<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Boutique Hotel Technikum</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Home</ion-title>
        </ion-toolbar>
      </ion-header>

      <ion-grid fixed>
        <ion-row>
          <ion-col>
            <ion-img
              :src="homeImage"
              alt=""
            />
          </ion-col>
        </ion-row>

        <ion-row>
          <ion-col>
            <h2>Luxus und Traumlage inmitten von Wien</h2>
            <p>Außergewöhnliche Unterkünfte mit atemberaubendem Blick über die Skyline von Wien</p>
          </ion-col>
        </ion-row>

        <ion-row>
          <HomePageCard v-for="card in homePageCards" :key="card.text" v-bind="card" />
        </ion-row>
      </ion-grid>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import HomePageCard from '@/components/HomePageCard.vue';
import homeImage from '@/assets/img/Boutique-Hotel-Technikum-Croped.jpg'
import HomePageCardText from "@/views/HomePage/HomePageCards";

export default {
  data() {
    return {
      homeImage,
      homePageCards: HomePageCardText
    }
  },
  components: { HomePageCard }
}
</script>
