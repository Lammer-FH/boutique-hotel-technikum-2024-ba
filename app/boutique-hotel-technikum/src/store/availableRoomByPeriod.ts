import {Room} from "@/network/Room";
import {defineStore} from "pinia";

const arrival = new Date();
const departure = new Date();
departure.setDate(departure.getDate() + 7);

export const useAvailableRoomsByPeriodStore = defineStore({
    id: "roomsByPeriod",
    state: () => ({
        loaded: false,
        arrival: arrival.toISOString(),
        departure: departure.toISOString(),
        pages: 1,
        currentPage: 1,
        rooms: [] as Room[],
        fetchError: false
    }),
    actions: {
        async setCurrentPage(page: number) {
            this.currentPage = page;
            await this.fetchRooms();
        },
        async fetchRooms() {

        }
    }
})