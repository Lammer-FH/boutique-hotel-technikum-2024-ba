<template>
  <ion-page>
    <BoutiqueHeader title="Zimmer Suchen"/>

    <ion-content>
      <RoomOverview v-bind="rooms"/>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import RoomOverview from "@/components/RoomOverview/RoomOverview.vue";
import bathImage from "@/assets/img/penthouse/Bath.jpg";
import bedImage from "@/assets/img/penthouse/Bed.jpg";
import minibarImage from "@/assets/img/penthouse/Minibar.jpg";
import toiletImage from "@/assets/img/penthouse/Toilet.jpg";

export default {
  components: {
    RoomOverview
  },
  data() {
    return {
      rooms: {
        id: 1,
        name: "Penthouse Suite",
        images: [
          {
            src: bathImage,
            alt: "Wunderschönes Badezimmer mit Badewanne, Dusche und Whirlpool!"
          },
          {
            src: bedImage,
            alt: "Doppelbett mit hervorragender Aussicht auf die Innenstadt!"
          },
          {
            src: minibarImage,
            alt: "Minibar mit riesiger Auswahl an Getränken!"
          },
          {
            src: toiletImage,
            alt: "Designer-Toilette mit Waschbecken und Spiegel!"
          }
        ],
        description: "Luxuriöse Suite im obersten Stock des Boutique-Hotel-Technikum.",
        beds: 2,
        extras: [
          {
            icon: "water",
            name: "Badezimmer"
          },
          {
            icon: "wine",
            name: "Minibar"
          },
          {
            icon: "tv",
            name: "Fernseher"
          },
          {
            icon: "thermometer",
            name: "Klimaanlage"
          }
        ],
        price: 1_999
      }
    }
  }
}
</script>