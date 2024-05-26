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
              src="/src/assets/img/boutique-hotel-technikum-1-croped.jpg"
              alt=""
            ></ion-img>
          </ion-col>
        </ion-row>

        <ion-row>
          <ion-col>
            <h2>Luxus und Traumlage inmitten von Wien</h2>
            <p>Außergewöhnliche Unterkünfte mit atemberaubendem Blick über die Skyline von Wien</p>
          </ion-col>
        </ion-row>

        <ion-row>
          <HomePageCard 
            imageSrc="/src/assets/img/boutique-hotel-technikum-2.jpg"
            imageAlt=""
            text="Moderne Architektur mit Glasfront für einen einzigartigen Panoramablick"
          />
          <HomePageCard 
            imageSrc="/src/assets/img/boutique-hotel-technikum-3.jpg"
            imageAlt=""
            text="Hochwertige Zimmer und Suiten"
          />
          <HomePageCard 
            imageSrc="/src/assets/img/boutique-hotel-technikum-4.jpg"
            imageAlt=""
            text="Infinity-Pool, Spa, Fitnesscenter, Gourmetrestaurants und einer Bar"
          />
          <HomePageCard 
            imageSrc="/src/assets/img/boutique-hotel-technikum-5.jpg"
            imageAlt=""
            text="Unzählige Aktivitäten in der Umgebung"
          />
          <HomePageCard 
            imageSrc="/src/assets/img/boutique-hotel-technikum-6.jpg"
            imageAlt=""
            text="Perfekt für Paare, Familien und Geschäftsreisende"
          />
        </ion-row>
      </ion-grid>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonHeader, IonToolbar, IonTitle, IonContent, IonImg, IonIcon, IonButton, IonButtons, IonCol, IonRow, IonGrid } from '@ionic/vue';
import { ellipsisHorizontal } from 'ionicons/icons';


import HomePageCard from '@/components/HomePageCard.vue';
</script>
