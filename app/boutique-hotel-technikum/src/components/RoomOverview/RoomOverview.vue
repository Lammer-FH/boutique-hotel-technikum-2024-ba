<template>
  <ion-header>
    <h1>{{ name }}</h1>

    <section>
      <p>{{ description }}</p>
      <p>{{ `Betten: ${beds}` }}</p>
      <p>
        Extras:
        <RoomExtras :extras="extras"/>
      </p>
      <p>{{ formatMoney(price) }}</p>
    </section>
  </ion-header>
</template>

<script lang="ts">
import {formatMoney} from "@/utils/Formatter";

export default {
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
      type: Array,
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
      type: Array,
      required: false
    },
    price: {
      type: Number,
      required: true
    }
  },
  data() {

  }
}
</script>