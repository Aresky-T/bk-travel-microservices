import { Client } from '@stomp/stompjs';

export function getStompClient(options) {
    return new Promise((resolve, reject) => {
        const client = new Client({
            brokerURL: "ws://localhost:8080/ws",
            reconnectDelay: 3000,
            onConnect: () => {
                resolve(client);
            },
        });
        client.activate();
    })
}