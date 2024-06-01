<template>
  <div class="margin">
    <div class="container">
      <ion-header>
        <h1>{{ name }}</h1>
      </ion-header>

      <section class="dia">
        <ImageDiaShow :images="images"></ImageDiaShow>
      </section>

      <section>
        <p>{{ description }}</p>
        <p>Betten: {{ beds }}</p>
        <p>
          Extras:
          <RoomExtra v-for="{name, icon} in extras"
                     :key="name"
                     :icon="icon"
                     :name="name" />
        </p>
        <p class="money">{{ formatMoney(price) }}</p>
        <ion-button @click="showCalendarFor = id">Verfügbarkeit prüfen</ion-button>
      </section>
    </div>
  </div>
  <BoutiqueCalendar v-if="showCalendarFor" @finished="showCalendarFor = undefined"/>
</template>

<script lang="ts">
import {formatMoney} from "@/utils/Formatter";
import RoomExtra, { Extra } from "@/components/RoomOverview/RoomExtra.vue";
import ImageDiaShow, {SlideImageData} from "@/components/UI/ImageDiaShow.vue";
import BoutiqueCalendar from "@/components/UI/BoutiqueCalendar.vue";

export default {
  components: {BoutiqueCalendar, ImageDiaShow: ImageDiaShow, RoomExtra},
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
  },
  data() {
    return {
      showCalendarFor: undefined as number | undefined
    }
  }
}
</script>

<style scoped lang="scss">
.margin {
  margin: 20px;
}

.container {
  background-color: var(--ion-background-color-step-100);
  margin: auto;
  padding: 10px;
  border-radius: 10px;
  max-width: 1000px;


  .dia {
    width: min(100%, 40vh);
    margin: auto;
  }

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