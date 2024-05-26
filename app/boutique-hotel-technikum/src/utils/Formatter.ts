const moneyFormatter = new Intl.NumberFormat("de-DE", {
    style: "currency",
    currency: "EUR",
    maximumFractionDigits: 0
});
export function formatMoney(amount: number) {
    return moneyFormatter.format(amount);
}