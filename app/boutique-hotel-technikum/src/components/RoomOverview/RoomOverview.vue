<template>
  <ion-grid v-if="room">
    <ion-row>
      <div class="container">
        <h1>{{ room.title }}</h1>

        <section v-if="showImages" class="dia">
          <ImageDiaShow :images="imageObject"></ImageDiaShow>
        </section>

        <section>
          <p>{{ room.description }}</p>
          <p>Betten: {{ room.bedAmount }}</p>
          <p>
            Extras:
            <RoomExtra v-for="{name, description} in sortedExtras"
                       :key="name"
                       :icon="getIconByName(name)"
                       :name="description" />
          </p>
          <p class="money">{{ formatMoney(room.price) }} pro Nacht</p>
          <slot></slot>
        </section>
      </div>
    </ion-row>
  </ion-grid>
</template>

<script lang="ts">
import {formatMoney} from "@/utils/Formatter";
import RoomExtra from "@/components/RoomOverview/RoomExtra.vue";
import { type Room } from "@/network/dtos/Room";
import ImageDiaShow from "@/components/UI/ImageDiaShow.vue";
import {PropType} from "vue";
import getIconByName from "@/components/RoomOverview/getIconByName";

export default {
  components: {ImageDiaShow: ImageDiaShow, RoomExtra},
  methods: {getIconByName, formatMoney},
  props: {
    room: {
      type: Object as PropType<Room>,
      required: false
    },
    showImages: {
      type: Boolean,
      required: false,
      default: true
    }
  },
  computed: {
    imageObject() {
      return [{
        src: `${import.meta.env.VITE_BACKEND_URL}/images/rooms/${this.room!.id}.jpg`,
        alt: ''
      }];
    },
    sortedExtras() {
      return this.room!.extras.sort((first, second) => first.description < second.description ? -1 : 1);
    }
  }
}
</script>

<style scoped lang="scss">
ion-row {
  margin: 20px;

  .container {
    background-color: var(--ion-background-color-step-100);
    margin: auto;
    padding: 10px;
    border-radius: 10px;
    width: 100%;
    max-width: 512px;


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
  }
}
</style>