import {Extra} from "@/components/RoomOverview/RoomExtra.vue";
import {SlideImageData} from "@/components/UI/ImageDiaShow.vue";

export default interface Room {
    id: number,
    name: string,
    images: SlideImageData[],
    description: string,
    beds: number,
    extras: Extra[],
    price: number
}