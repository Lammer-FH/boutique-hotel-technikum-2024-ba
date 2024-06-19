import { createRouter, createWebHistory } from '@ionic/vue-router';
import {createMemoryHistory, NavigationGuardNext, RouteLocationNormalized, RouteRecordRaw} from 'vue-router';
import TabsPage from '../views/TheNavigation.vue'
import {useBookingStore} from "@/stores/booking";
import {useCustomerStore} from "@/stores/customer";

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
        redirect: 'search/period',
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
        component: () => import('@/views/BookingPage/BookingPage.vue'),
        meta: { needsValidRoom: true, to: "/search/period" }
      },
      {
        path: 'booking-overview',
        component: () => import('@/views/BookingPage/BookingOverviewPage.vue'),
        meta: { needsValidRoom: true, needsValidCustomer: true, to: "/search/period" }
      },
      {
        path: 'confirmation',
        component: () => import('@/views/BookingPage/ConfirmationPage.vue'),
        meta: { needsValidRoom: true, needsValidCustomer: true, to: "/search/period" }
      },
      {
        path: 'about',
        component: () => import('@/views/AboutPage.vue')
      },
      {
        path: 'impressum',
        component: () => import('@/views/ImpressumPage.vue')
      },
      {
        path: '/:notFound(.*)',
        redirect: '/home'
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

router.beforeEach((to: RouteLocationNormalized, from: RouteLocationNormalized, next: NavigationGuardNext) => {
  const booking = useBookingStore();
  const customer = useCustomerStore();

  if (to.meta.needsValidRoom && !booking.isRoomValid) {
    next(to.meta.to ?? "/home");
    return;
  }
  if (to.meta.needsValidCustomer && !customer.isValid) {
    next(to.meta.to ?? "/home");
    return;
  }

  next();
});

export default router
