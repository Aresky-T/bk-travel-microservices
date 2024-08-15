import { IConversation } from "../types/chat";

export function findDifferentElements<T extends IConversation>(arrayA: T[], arrayB: T[]): T[] {
    const uniqueElements: T[] = [];
    const arr: number[] = [];

    for (const element of arrayA) {
        arr.push(element.id);
    }

    for(const element of arrayB){
        if(arr.includes(element.id)){
            uniqueElements.push(element);
        }
    }

    return uniqueElements;
}