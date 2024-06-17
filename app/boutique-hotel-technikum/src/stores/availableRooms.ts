import {defineStore} from "pinia";
import {Room} from "@/network/dtos/Room";
import {getRooms} from "@/network/getRooms";
import CancellationTokenCreator from "@/utils/CancellationTokenCreator";

const cancellationTokenCreator = new CancellationTokenCreator();

export const useAvailableRoomsStore = defineStore({
    id: "rooms",
    state: () => ({
        loaded: false,
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
            try {
                const token = cancellationTokenCreator.cancelOldAndGetNewToken();
                const roomResponse = await getRooms(this.currentPage);
                if (token.cancelled) { return; }

                this.loaded = true;
                this.pages = roomResponse.totalPages;
                this.rooms = roomResponse.rooms;
            } catch(e) {
                this.fetchError = true;
            }
        }
    }
})