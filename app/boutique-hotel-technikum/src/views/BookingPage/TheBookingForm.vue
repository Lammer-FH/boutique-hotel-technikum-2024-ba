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
          v-model="data.firstName"></ion-input>
    </BookingFormRow>

    <BookingFormRow description="Nachname:">
      <ion-input
          :class="{'ion-touched': true, 'ion-invalid': !valid.lastName}"
          placeholder="Mustermann"
          type="text"
          error-text="Bitte Nachnamen eingeben!"
          v-model="data.lastName"></ion-input>
    </BookingFormRow>

    <BookingFormRow description="E-Mail:">
      <ion-input
          :class="{'ion-touched': true, 'ion-invalid': !valid.eMail}"
          placeholder="max.mustermann@email.com"
          type="email"
          error-text="Die E-Mail ist nicht korrekt oder nicht vorhanden!"
          v-model="data.eMail"></ion-input>
    </BookingFormRow>

    <BookingFormRow description="E-Mail wiederholen:">
      <ion-input
          :class="{'ion-touched': true, 'ion-invalid': !valid.eMailRepeat}"
          placeholder="max.mustermann@email.com"
          type="email"
          error-text="Die E-Mail stimmt nicht mit der vorherigen überein!"
          v-model="data.eMailRepeat"></ion-input>
    </BookingFormRow>

    <BookingFormRow
        description="Frühstück:">
      <ion-radio-group v-model="data.breakfast" :allow-empty-selection="false">
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
        v-model="data.address.street"></ion-input>
    </BookingFormRow>

    <BookingFormRow
        description="PLZ:">
      <ion-input
          class="ion-touched"
          placeholder="1234"
          type="number"
          v-model="data.address.postCode"></ion-input>
    </BookingFormRow>

    <BookingFormRow
        description="Stadt:">
      <ion-input
          class="ion-touched"
          placeholder="Musterstadt"
          type="text"
          v-model="data.address.city"></ion-input>
    </BookingFormRow>

    <BookingFormRow
        description="Land:">
      <ion-input
          class="ion-touched"
          placeholder="Musterland"
          type="text"
          v-model="data.address.country"></ion-input>
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
import { useCustomerFormData } from "@/stores/customerFormData";

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
      data: useCustomerFormData()
    }
  },
  methods: {
    tryGoToOverview() {
      this.valid.firstName = this.data.firstName.length > 0;
      this.valid.lastName = this.data.lastName.length > 0;
      this.valid.eMail = !!this.validateEmail(this.data.eMail);
      this.valid.eMailRepeat = this.data.eMail === this.data.eMailRepeat;

      for (const valid of Object.values(this.valid)) {
        if (!valid) {
          return
        }
      }

      this.$emit("confirmed", this.data);
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