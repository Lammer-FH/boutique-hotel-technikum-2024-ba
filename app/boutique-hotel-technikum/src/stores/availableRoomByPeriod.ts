import {Room} from "@/network/dtos/Room";
import {defineStore} from "pinia";
import CancellationTokenCreator from "@/utils/CancellationTokenCreator";
import {getRoomsByPeriod} from "@/network/getRooms";

const arrival = new Date();
const departure = new Date();
departure.setDate(departure.getDate() + 7);

const cancellationTokenCreator = new CancellationTokenCreator();

export enum ERoomSearchState {
    Idle,
    Loading,
    Loaded,
    Error
}

export const useAvailableRoomsByPeriodStore = defineStore({
    id: "roomsByPeriod",
    state: () => ({
        state: ERoomSearchState.Idle,
        arrival: arrival.toISOString(),
        departure: departure.toISOString(),
        pages: 1,
        currentPage: 1,
        rooms: [] as Room[]
    }),
    actions: {
        async setCurrentPage(page: number) {
            this.currentPage = page;
            await this.fetchRooms();
        },
        async fetchRooms() {
            try  {
                this.state = ERoomSearchState.Loading;
                const token = cancellationTokenCreator.cancelOldAndGetNewToken();
                const roomResponse = await getRoomsByPeriod(this.currentPage, new Date(this.arrival), new Date(this.departure));
                if (token.cancelled) { return; }

                this.state = ERoomSearchState.Loaded;
                this.pages = roomResponse.totalPages;
                this.rooms = roomResponse.rooms;
            } catch (e) {
                this.state = ERoomSearchState.Error;
            }
        },
        setState(state: ERoomSearchState) {
            this.state = state;
        },
        setArrival(arrival: string) {
            this.arrival = arrival;
        },
        setDeparture(departure: string) {
            this.departure = departure
        }
    }
})