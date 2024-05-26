<template>
  <li>
    <ion-icon :name="icon"/>
    {{ name }}
  </li>
</template>

<script lang="ts">
import {addIcons} from "ionicons";
import {water, wine, tv, thermometer} from "ionicons/icons";

export type Extra = {
  icon: string,
  name: string
}

const extraIcons = {
  "water": water,
  "wine": wine,
  "tv": tv,
  "thermometer": thermometer
}
addIcons(extraIcons);

export default {
  props: {
    icon: {
      type: String,
      required: true,
      validator(value: string) {
        return Object.keys(extraIcons).includes(value);
      }
    },
    name: {
      type: String,
      required: true
    }
  }
}
</script>