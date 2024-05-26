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
            src: "",
            alt: ""
          },
          {
            src: "",
            alt: ""
          }
        ],
        description: "Luxuriöse Suite im obersten Stock des Boutique-Hotel-Technikum.",
        beds: 2,
        extras: [],
        price: 1_999,

      }
    }
  }
}
</script>