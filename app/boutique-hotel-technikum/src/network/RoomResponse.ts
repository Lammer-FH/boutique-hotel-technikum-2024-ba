import {Room} from "@/network/Room";

export default interface RoomResponse {
    period: null, //TODO
    rooms: Room[],
    pageNumber: number,
    pageSize: number,
    totalPages: number,
    totalElements: number
}