import axios from "axios";
import Guard from "../utils/Guard"
import RoomResponse from "@/network/RoomResponse";

export async function getRooms(page: number, pageSize: number = 5): Promise<RoomResponse> {
    Guard.against.less(page, 1);
    Guard.against.less(pageSize, 1)

    return (await axios.get<RoomResponse>(
        `${import.meta.env.VITE_BACKEND_URL}/api/v1/rooms`,
        {
            params: {
                page,
                pageSize
            }
        }
    )).data;
}

export async function getRoomsByPeriod(page: number, start: Date, end: Date, pageSize: number = 5) {
    Guard.against.less(page, 1);
    Guard.against.less(pageSize, 1);

    return (await axios.get<RoomResponse>(
        `${import.meta.env.VITE_BACKEND_URL}/api/v1/rooms/available`,
        {
            params: {
                start: start.toISOString().slice(0, 10),
                end: end.toISOString().slice(0, 10),
                page,
                pageSize
            }
        }
    )).data;
}