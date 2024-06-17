import {Period} from "@/network/dtos/Booking";

export interface AvailabilityResponse {
    roomId: number,
    requestedPeriod: Period,
    booked: Period[]
}