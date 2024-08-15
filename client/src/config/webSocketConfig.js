import { Client, Stomp, } from "@stomp/stompjs";

const socketUrl = "ws://localhost:8087/ws"
const socket = () => new WebSocket(socketUrl);

export default function getStompClient() {
    return new Promise((resolve, reject) => {
        const client = Stomp.over(socket);
        client.configure({
            reconnectDelay: 5000,
        })
        client.activate();
        client.connect({}, () => {
            resolve(client);
        })
    })
}

export function getStompClient2() {
    return new Promise((resolve, reject) => {
        const client = new Client({
            brokerURL: socketUrl,
            reconnectDelay: 3000,
            onConnect: () => {
                resolve(client);
            },
        });
        client.activate();
    })
}