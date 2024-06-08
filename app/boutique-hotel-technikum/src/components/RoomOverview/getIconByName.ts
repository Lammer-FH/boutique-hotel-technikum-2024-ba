import {addIcons} from "ionicons";
import {rainy, telescope, thermometer, tv, water, wifi, wine} from "ionicons/icons";

addIcons({
    "water": water,
    "wine": wine,
    "tv": tv,
    "thermometer": thermometer,
    "wifi": wifi,
    "rainy": rainy,
    "telescope": telescope
})

const map = new Map<string, string>([
    ["BATHROOM", "water"],
    ["MINIBAR", "wine"],
    ["TV", "tv"],
    ["HVAC", "thermometer"],
    ["WIFI", "wifi"],
    ["BATHTUB", "rainy"],
    ["VIEW", "telescope"]
]);

export default function getIconByName(name: string) {
    return map.get(name) ?? "water";
}