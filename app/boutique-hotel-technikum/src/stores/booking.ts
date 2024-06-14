import {defineStore} from "pinia";
import {Room} from "@/network/Room";

export const useBookingStore = defineStore({
    id: 'booking',
    state: () => ({
        status: "booking" as "booking" | "booked",
        arrival: undefined as Date | undefined,
        departure: undefined as Date | undefined,
        room: undefined as Room | undefined,
        firstName: "",
        lastName: "",
        eMail: "",
        breakfast: true
    }),
    getters: {
        prettyArrival(state) {
            return state.arrival?.toLocaleDateString()
        },
        prettyDeparture(state) {
            return state.departure?.toLocaleDateString();
        },
        isRoomValid(state): boolean {
            return !!(state.arrival && state.departure && state.room);
        },
        isBookingValid(state): boolean {
            return this.isRoomValid && !!(state.firstName && state.lastName && state.eMail);
        }
    }
})