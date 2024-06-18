import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import TabsPage from '../views/TheNavigation.vue'
import {BookingPageNavigationGuard} from "@/views/BookingPage/BookingPage.vue";
import {BookingOverviewPageNavigationGuard} from "@/views/BookingPage/BookingOverviewPage.vue";
import {ConfirmationPageNavigationGuard} from "@/views/BookingPage/ConfirmationPage.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: '',
    redirect: '/home'
  },
  {
    path: '/',
    component: TabsPage,
    children: [
      {
        path: 'home',
        component: () => import('@/views/HomePage/HomePage.vue')
      },
      {
        path: 'search',
        redirect: 'search/room',
        component: () => import('@/views/TheSearchNavigation.vue'),
        children: [
          {
            path: '',
            redirect: 'search/period'
          },
          {
            path: 'room',
            component: () => import('@/views/SearchPage/SearchRoomPage.vue')
          },
          {
            path: 'period',
            component: () => import('@/views/SearchPage/SearchPeriodPage.vue')
          }
        ]
      },
      {
        path: 'booking',
        component: () => import('@/views/BookingPage/BookingPage.vue')
      },
      {
        path: 'booking-overview',
        component: () => import('@/views/BookingPage/BookingOverviewPage.vue')
      },
      {
        path: 'confirmation',
        component: () => import('@/views/BookingPage/ConfirmationPage.vue')
      },
      {
        path: 'about',
        component: () => import('@/views/AboutPage.vue')
      },
      {
        path: 'impressum',
        component: () => import('@/views/ImpressumPage.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})
router.beforeEach(BookingPageNavigationGuard);
router.beforeEach(BookingOverviewPageNavigationGuard);
router.beforeEach(ConfirmationPageNavigationGuard);

export default router
