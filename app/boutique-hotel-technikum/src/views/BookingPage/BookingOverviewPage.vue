<template>
  <ion-page>
    <ion-header>
      <ion-grid>
        <ion-row class="ion-justify-content-end">
          <ion-button
            color="danger"
            @click="back()">
            Zurück
          </ion-button>
        </ion-row>
      </ion-grid>
    </ion-header>
  </ion-page>
</template>

<script lang="ts">
import {useIonRouter} from "@ionic/vue";

export default {
  setup() {
    const router = useIonRouter();
    return {
      router
    }
  },
  methods: {
    back() {
      this.router.navigate("/booking");
    }
  }
}
</script>