import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import TabsPage from '../views/TheNavigation.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/',
    component: TabsPage,
    children: [
      {
        path: '',
        redirect: '/home'
      },
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
        component: () => import('@/views/BookingPage/BookingOverviewPage/BookingPage.vue')
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

export default router
