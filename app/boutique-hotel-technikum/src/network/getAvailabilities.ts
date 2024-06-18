import {AvailabilityResponse} from "@/network/dtos/AvailabilityResponse";
import axios from "axios";

export async function getAvailabilities(roomId: number, start: Date, end: Date) {
    return (await axios.get<AvailabilityResponse>(
        `${import.meta.env.VITE_BACKEND_URL}/api/v1/rooms/${roomId}/booked`,
        {
            params: {
                start: start.toISOString().slice(0, 10),
                end: end.toISOString().slice(0, 10)
            }
        })).data;
}