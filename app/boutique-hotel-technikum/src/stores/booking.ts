import {defineStore} from "pinia";
import Room from "./Room"

export const useBookingStore = defineStore({
    id: 'booking',
    state: () => ({
        arrival: undefined as Date | undefined,
        departure: undefined as Date | undefined,
        room: undefined as Room | undefined,
        firstName: "",
        lastName: "",
        eMail: "",
        breakfast: true
    }),
    getters: {
        prettyArrival: (state) => {
            return state.arrival?.toLocaleDateString()
        },
        prettyDeparture: (state) => {
            return state.departure?.toLocaleDateString();
        }
    }
})