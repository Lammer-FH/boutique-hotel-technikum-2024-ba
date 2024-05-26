<template>
  <div class="container">
    <ion-header>
      <h1>{{ name }}</h1>
    </ion-header>
    <section>
      <ImageDiaShow :images="images"></ImageDiaShow>
      <p>{{ description }}</p>
      <p>{{ `Betten: ${beds}` }}</p>
      <p>
        Extras:
        <RoomExtra v-for="{name, icon} in extras"
                   :key="name"
                   :icon="icon"
                   :name="name" />
      </p>
      <p class="money">{{ formatMoney(price) }}</p>
      <ion-button>Verfügbarkeit prüfen</ion-button>
    </section>
  </div>
</template>

<script lang="ts">
import {formatMoney} from "@/utils/Formatter";
import RoomExtra, { Extra } from "@/components/RoomOverview/RoomExtra.vue";
import ImageDiaShow, {SlideImageData} from "@/components/UI/ImageDiaShow.vue";

export default {
  components: {ImageDiaShow: ImageDiaShow, RoomExtra},
  methods: {formatMoney},
  props: {
    id: {
      type: Number,
      required: true
    },
    name: {
      type: String,
      required: true
    },
    images: {
      type: Array<SlideImageData>,
      required: true
    },
    description: {
      type: String,
      required: true
    },
    beds: {
      type: Number,
      required: true
    },
    extras: {
      type: Array<Extra>,
      required: false
    },
    price: {
      type: Number,
      required: true
    }
  }
}
</script>

<style scoped lang="scss">
.container {
  background-color: var(--ion-background-color-step-100);
  margin: 20px;
  padding: 10px;
  border-radius: 10px;

  h1 {
    margin-top: 0;
  }

  .money {
    text-align: center;
  }

  ion-button {
    width: 100%;
  }
}
</style>