<template>
  <ion-grid>
    <ion-row>
      <h1>Ihre Kontaktdaten</h1>
    </ion-row>

    <BookingFormRow
        description="Vorname:"
        :helper-text="valid.firstName">
      <ion-input
          :class="{'ion-touched': true, 'ion-invalid': !valid.firstName}"
          placeholder="Max"
          type="text"
          error-text="Bitte Vornamen eingeben!"
          v-model="inputs.firstName"></ion-input>
    </BookingFormRow>

    <BookingFormRow description="Nachname:">
      <ion-input
          :class="{'ion-touched': true, 'ion-invalid': !valid.lastName}"
          placeholder="Mustermann"
          type="text"
          error-text="Bitte Nachnamen eingeben!"
          v-model="inputs.lastName"></ion-input>
    </BookingFormRow>

    <BookingFormRow description="E-Mail:">
      <ion-input
          :class="{'ion-touched': true, 'ion-invalid': !valid.eMail}"
          placeholder="max.mustermann@email.com"
          type="email"
          error-text="Die E-Mail ist nicht korrekt oder nicht vorhanden!"
          v-model="inputs.eMail"></ion-input>
    </BookingFormRow>

    <BookingFormRow description="E-Mail wiederholen:">
      <ion-input
          :class="{'ion-touched': true, 'ion-invalid': !valid.eMailRepeat}"
          placeholder="max.mustermann@email.com"
          type="email"
          error-text="Die E-Mail stimmt nicht mit der vorherigen überein!"
          v-model="inputs.eMailRepeat"></ion-input>
    </BookingFormRow>

    <BookingFormRow
        description="Frühstück:">
      <ion-radio-group v-model="inputs.breakfast" :allow-empty-selection="false">
        <ion-radio :value="true">Ja</ion-radio>
        <ion-radio :value="false" class="no">Nein</ion-radio>
      </ion-radio-group >
    </BookingFormRow>

    <ion-row><h1>Adresse für Anfahrt (optional)</h1></ion-row>
    <BookingFormRow
      description="Straße:">
      <ion-input
        class="ion-touched"
        placeholder="Muster-Straße 10"
        type="text"
        v-model="inputs.address.street"></ion-input>
    </BookingFormRow>

    <BookingFormRow
        description="PLZ:">
      <ion-input
          class="ion-touched"
          placeholder="1234"
          type="number"
          v-model="inputs.address.postCode"></ion-input>
    </BookingFormRow>

    <BookingFormRow
        description="Stadt:">
      <ion-input
          class="ion-touched"
          placeholder="Musterstadt"
          type="text"
          v-model="inputs.address.city"></ion-input>
    </BookingFormRow>

    <BookingFormRow
        description="Land:">
      <ion-input
          class="ion-touched"
          placeholder="Musterland"
          type="text"
          v-model="inputs.address.country"></ion-input>
    </BookingFormRow>

    <ion-row>
      <ion-col>
        <ion-button @click="tryGoToOverview" expand="block">Weiter</ion-button>
      </ion-col>
    </ion-row>
  </ion-grid>
</template>

<script lang="ts">
import { IonInput, IonRadio, IonRadioGroup } from '@ionic/vue';
import BookingFormRow from "@/views/BookingPage/BookingFormRow.vue";

export default {
  components: {
    BookingFormRow,
    IonInput,
    IonRadioGroup,
    IonRadio
  },
  emits: ["confirmed"],
  data() {
    return {
      valid: {
        firstName: true,
        lastName: true,
        eMail: true,
        eMailRepeat: true
      },
      inputs: {
        firstName: "",
        lastName: "",
        eMail: "",
        eMailRepeat: "",
        breakfast: true,
        address: {
          street: "",
          postCode: undefined as number | undefined,
          city: "",
          country: ""
        }
      }
    }
  },
  methods: {
    tryGoToOverview() {
      this.valid.firstName = this.inputs.firstName.length > 0;
      this.valid.lastName = this.inputs.lastName.length > 0;
      this.valid.eMail = !!this.validateEmail(this.inputs.eMail);
      this.valid.eMailRepeat = this.inputs.eMail === this.inputs.eMailRepeat;

      for (const valid of Object.values(this.valid)) {
        if (!valid) {
          return
        }
      }

      this.$emit("confirmed", this.inputs);
    },
    validateEmail(email: string) {
      return email.match(
          /^(?=.{1,254}$)(?=.{1,64}@)[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
      );
    }
  }
}
</script>

<style scoped>
ion-input {
  --padding-start: 0.5rem;
  --padding-end: 0.5rem;
  --background: var(--ion-background-color-step-100);
}

ion-radio.no {
  margin-left: 1rem;
}
</style>