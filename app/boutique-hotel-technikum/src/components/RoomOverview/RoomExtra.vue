<template>
  <li>
      <ion-icon :name="icon"/>
      {{ name }}
  </li>
</template>

<script lang="ts">
import {addIcons} from "ionicons";
import {thermometer, tv, water, wine} from "ionicons/icons";

export type Extra = {
  icon: string,
  name: string
}

export default {
  props: {
    icon: {
      type: String,
      required: true
    },
    name: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped>
li {
  vertical-align: middle;
}
</style>