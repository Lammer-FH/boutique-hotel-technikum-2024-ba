import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import TabsPage from '../views/TheNavigation.vue'
import {BookingPageNavigationGuard} from "@/views/BookingPage/BookingPage.vue";
import {beforeEach} from "vitest";
import {BookingOverviewPageNavigationGuard} from "@/views/BookingPage/BookingOverviewPage/BookingOverviewPage.vue";

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
            redirect: 'search/room'
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
        component: () => import('@/views/BookingPage/BookingOverviewPage/BookingOverviewPage.vue')
      },
      {
        path: 'confirmation',
        component: () => import('@/views/ConfirmationPage.vue')
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

export default router
