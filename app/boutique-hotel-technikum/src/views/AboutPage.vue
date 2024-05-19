<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>About</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true" >
      <ion-grid fixed>
        <ion-row>
          <ion-col>
            <h1 class="ion-padding">Boutique Hotel Technikum</h1>
          </ion-col>
        </ion-row>

        <ion-row>

          <AboutPageCard 
            title="Lage"
            text="Inmitten der Großstadt Wien, thront das Boutique-Hotel-Technikum Nähe des Stephansdoms im 3. Bezirk. Umgeben von Sehenswürdigkeiten und Shoppingmöglichkeiten der Wiener Innenstadt bietet es vielfältige Möglichkeiten den Tag zu genießen."
          />
          <AboutPageCard 
            title="Architektur"
            text="Die moderne Architektur des Hotels fügt sich harmonisch in die Innenstadt ein. Großzügige Glasfronten erlauben eine atemberaubende Aussicht auf die gesamte Skyline der Großstadt."/>
          <AboutPageCard 
            title="Zimmer"
            text=" Die luxuriösen Zimmer und Suiten des Hotels Sternenhimmel bieten höchsten Komfort und Eleganz. Ausgestattet mit hochwertigen Materialien und modernen Annehmlichkeiten garantieren sie einen unvergesslichen Aufenthalt. Alle Zimmer verfügen über einen Balkon oder eine Terrasse mit Panoramablick auf die Stadt."
          />
          <AboutPageCard 
            title="Lage"
            text="Inmitten der Großstadt Wien, thront das Boutique-Hotel-Technikum Nähe des Stephansdoms im 3. Bezirk. Umgeben von Sehenswürdigkeiten und Shoppingmöglichkeiten der Wiener Innenstadt bietet es vielfältige Möglichkeiten den Tag zu genießen."
          />
          <AboutPageCard title="Ausstattung">
            Das Boutique-Hotel-Technikum bietet seinen Gästen eine Vielzahl von Annehmlichkeiten und Aktivitäten. Dazu gehören:
            <ul>
              <li>Infinity-Pool mit atemberaubendem Blick</li>
              <li>Spa-Bereich mit vielfältigen Anwendungen und Massagens</li>
              <li>Fitnesscenter mit modernsten Geräten</li>
              <li>Zwei Restaurants mit gehobener Küche</li>
              <li>Bar mit großer Auswahl an Cocktails und Getränken</li>
              <li>Lounge mit Kamin und Bibliothek</li>
              <li>Geschäft mit Souvenirs und regionalen Produkten</li>
            </ul>
          </AboutPageCard>
          <AboutPageCard 
            title="Aktivitäten"
            text="In der Umgebung des Boutique-Hotel-Technikum gibt es zahlreiche Möglichkeiten für Aktivitäten in der Innenstadt. Shoppen, der Besuch von Sehenswürdigkeiten wie den Stephansdom, die Hofburg, das Schloss Schönbrunn oder Museen sind nur einige der Aktivitäten, die Gäste genießen können. Das Hotel bietet auch geführte Städtetrips und andere Aktivitäten an."
          />
          <AboutPageCard 
            title="Zielgruppe"
            text="Das Hotel ist ideal geeignet für Gäste, die einen luxuriösen und erholsamen Urlaub in der Wiener Innenstadt suchen. Paare, Familien und Geschäftsreisende sind gleichermaßen willkommen."
          />
          <AboutPageCard 
            title="Preis"
            text="Die Preise für die Zimmer variieren je nach Saison und Zimmerkategorie. In der Hauptsaison beginnen die Preise bei etwa 200 € pro Nacht."
          />

        </ion-row>
      </ion-grid>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/vue';
import AboutPageCard from '@/components/AboutPageCard.vue';

</script>
