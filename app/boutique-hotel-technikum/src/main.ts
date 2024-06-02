import { createApp } from 'vue'
import App from './App.vue'
import router from './router';

import {
  IonButton,
  IonCol,
  IonContent, IonFooter,
  IonGrid,
  IonHeader, IonIcon,
  IonicVue,
  IonImg,
  IonPage,
  IonRow, IonText,
  IonTitle,
  IonToolbar
} from '@ionic/vue';

/* Core CSS required for Ionic components to work properly */
import '@ionic/vue/css/core.css';

/* Basic CSS for apps built with Ionic */
import '@ionic/vue/css/normalize.css';
import '@ionic/vue/css/structure.css';
import '@ionic/vue/css/typography.css';

/* Optional CSS utils that can be commented out */
import '@ionic/vue/css/padding.css';
import '@ionic/vue/css/float-elements.css';
import '@ionic/vue/css/text-alignment.css';
import '@ionic/vue/css/text-transformation.css';
import '@ionic/vue/css/flex-utils.css';
import '@ionic/vue/css/display.css';

/**
 * Ionic Dark Mode
 * -----------------------------------------------------
 * For more info, please see:
 * https://ionicframework.com/docs/theming/dark-mode
 */

/* @import '@ionic/vue/css/palettes/dark.always.css'; */
/* @import '@ionic/vue/css/palettes/dark.class.css'; */
import '@ionic/vue/css/palettes/dark.system.css';

/* Theme variables */
import './theme/variables.css';
import BoutiqueHeader from "@/components/UI/TheHeader.vue";
import {createPinia} from "pinia";

const app = createApp(App)
  .use(IonicVue)
  .use(createPinia())
  .use(router);

app.component("IonPage", IonPage)
    .component("IonHeader", IonHeader)
    .component("IonFooter", IonFooter)
    .component("IonTitle", IonTitle)
    .component("IonContent", IonContent)
    .component("IonToolbar", IonToolbar)
    .component("IonImg", IonImg)
    .component("IonIcon", IonIcon)
    .component("IonGrid", IonGrid)
    .component("IonRow", IonRow)
    .component("IonCol", IonCol)
    .component("IonButton", IonButton)
    .component("IonText", IonText);

app.component("BoutiqueHeader", BoutiqueHeader);

router.isReady().then(() => {
  app.mount('#app');
});
