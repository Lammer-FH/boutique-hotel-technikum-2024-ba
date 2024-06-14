import axios from "axios";
import {Customer, Period} from "@/network/Booking";

export default async function bookRoom(roomId: number, customer: Customer, breakfast: boolean, period: Period) {
    await axios.post(`${import.meta.env.VITE_BACKEND_URL}/api/v1/bookings`,
        {
            roomId,
            customer,
            breakfast,
            reservationPeriod: period
        })
}