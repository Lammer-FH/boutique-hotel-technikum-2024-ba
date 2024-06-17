import {Room} from "@/network/dtos/Room";

export default interface RoomResponse {
    period: null,
    rooms: Room[],
    pageNumber: number,
    pageSize: number,
    totalPages: number,
    totalElements: number
}