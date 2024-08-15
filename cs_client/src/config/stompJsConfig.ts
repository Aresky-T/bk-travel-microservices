import { Client } from "@stomp/stompjs"

export function getStompClient() {
    return new Promise<Client>((resolve, reject) => {
        const client = new Client({
            brokerURL: "ws://localhost:8080/ws",
            reconnectDelay: 3000,
            onConnect: () => {
                resolve(client);
            },
            onWebSocketError: (event) => {

            },
            onStompError: (frame) => {

            }
        });
        client.activate();
    })
}