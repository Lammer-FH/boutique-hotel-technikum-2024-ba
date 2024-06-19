<template>
  <ion-page>
    <BoutiqueHeader title="Impressum"/>

    <ion-content :fullscreen="true" class="ion-padding">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Impressum</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-grid fixed>
        <ion-row>
          <ion-col>
            <h1>Boutique Hotel Technikum</h1>
            <ul>
              <li><b>Anschrift:</b><br>Hotelgasse 1, 1030 Wien, Österreich</li>
              <li><b>Telefon:</b><br><a :href="telephoneHref()">{{ telephone() }}</a></li>
              <li><b>E-Mail:</b><br><a :href="eMailHref()">{{ eMail() }}</a></li>
              <li><b>Website:</b><br><a href="https://boutique-hotel.at">boutique-hotel.at</a></li>
              <li><b>Geschäftsführer:</b><br>Mag. Dr. Bou Tique</li>
              <li><b>Unternehmensgegenstand:</b><br>Beherbergungsbetrieb</li>
              <li><b>Firmenbuchnummer:</b><br>FN 123456A</li>
              <li><b>Umsatzsteuer-Identifikationsnummer:</b><br>AT123456789A</li>
              <li><b>GISA-Zahl:</b><br>123456789</li>
              <li><b>Zuständige Behörde:</b><br>Magistrat der Stadt Wien</li>
            </ul>

          <h2>Haftungsausschluss:</h2>
          <p>
            Die Inhalte dieser Website wurden mit größtmöglicher Sorgfalt erstellt. Das Boutique-Hotel-Technikum übernimmt jedoch keine Gewähr für die Richtigkeit, Vollständigkeit und Aktualität der bereitgestellten Inhalte. Die Nutzung der Website erfolgt auf eigene Gefahr. Namentlich gekennzeichnete Beiträge geben die Meinung des jeweiligen Autors und nicht immer die Meinung des Boutique-Hotels-Technikum wieder.
          </p>

          <h2>Copyright:</h2>
          <p>
            Alle Inhalte dieser Website, insbesondere Texte, Bilder, Grafiken und Design, sind urheberrechtlich geschützt. Die Vervielfältigung, Verbreitung, Bearbeitung, Weitergabe oder sonstige Nutzung dieser Inhalte bedarf der vorherigen schriftlichen Genehmigung des Boutique Hotels Technikum.
          </p>

          <h2>Datenschutz:</h2>
          <p>
            Das Boutique Hotel Technikum nimmt den Schutz Ihrer persönlichen Daten sehr ernst. Die Verarbeitung personenbezogener Daten erfolgt im Einklang mit den datenschutzrechtlichen Bestimmungen, insbesondere der Europäischen Datenschutz-Grundverordnung (DS-GVO).
          </p>

          <h2>Links:</h2>
          <p>
            Diese Website enthält Links zu Websites Dritter. Das Boutique Hotel Technikum übernimmt keine Verantwortung für die Inhalte dieser Websites.
          </p>

          <h2>Rechtswirksamkeit:</h2>
          <p>
            Dieser Haftungsausschluss ist Teil der Website des Boutique Hotels Technikum. Sofern einzelne Teile oder Formulierungen dieses Textes unwirksam sein oder werden sollten, bleiben die übrigen Teile des Textes in ihrer Wirkung unberührt.
          </p>
          </ion-col>
        </ion-row>
      </ion-grid>
        
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { IonToolbar } from '@ionic/vue';
import BoutiqueHeader from "@/components/UI/TheHeader.vue";
import {eMail, eMailHref, telephone, telephoneHref} from "@/utils/ContactData";

export default {
  name: "Impressum",
  components: {
    BoutiqueHeader,
    IonToolbar
  },
  methods: {
    eMailHref() { return eMailHref },
    telephoneHref() { return telephoneHref },
    eMail() { return eMail },
    telephone() { return telephone }
  }
}
</script>
