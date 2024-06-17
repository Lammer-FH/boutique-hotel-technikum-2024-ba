import {defineStore} from "pinia";
import {Room} from "@/network/dtos/Room";

export enum EBookingState {
    BOOKING,
    BOOKED,
    ERROR
}

export const useBookingStore = defineStore({
    id: 'booking',
    state: () => ({
        state: EBookingState.BOOKING,
        arrival: undefined as Date | undefined,
        departure: undefined as Date | undefined,
        room: undefined as Room | undefined,
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
        }
    },
    actions: {
        setState(state: EBookingState) {
            this.state = state;
        },
        setArrival(date: Date) {
            this.arrival = date;
        },
        setDeparture(date: Date) {
            this.departure = date;
        },
        setRoom(room: Room) {
            this.room = room;
        },
        setBreakfast(breakfast: boolean) {
            this.breakfast = breakfast;
        }
    }
})