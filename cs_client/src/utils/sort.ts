export function sortByTime(dateString1: string, dateString2: string) {
    const date1 = new Date(dateString1);
    const date2 = new Date(dateString2);
    return date1.getTime() - date2.getTime();
}