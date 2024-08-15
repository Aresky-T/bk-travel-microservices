import React, { useEffect } from "react";
import NavbarContainer from "../containers/navbar";
import { Outlet } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import { useAppContext } from "../store/context";
import { getStompClient } from "../socket";

const AppLayout = () => {
    const { state, dispatch } = useAppContext();
    const { employee } = state;

    useEffect(() => {
        const data = employee.data;
        if (data) {
            const client = getStompClient({
                onConnect: function () {
                    client.subscribe(`/topic/chat/employee-status/${data.id}`, function (message) {
                        if (JSON.parse(message.body) === "ONLINE") {
                            dispatch && dispatch({ type: "EMPLOYEE|HANDLE_CONNECT_SOCKET" });
                        }
                    });

                    client.publish({
                        destination: `/app/chat/update-status/employee/${data.id}/ONLINE`
                    });
                },
                onWebSocketError: function () {
                    dispatch && dispatch({ type: "EMPLOYEE|HANDLE_DISCONNECT_SOCKET" });
                    toast.error("Máy chủ đã mất kết nối");
                }
            });
            client.activate();
        }
    }, [employee.data, dispatch])

    useEffect(() => {
        return (() => {
            if (employee.data) {
                const employeeId = employee.data.id;
                const client = getStompClient({
                    onConnect: () => {
                        client.publish({
                            destination: `/app/chat/update-status/employee/${employeeId}/OFFLINE`
                        });
                    }
                });
            }
        })
        //eslint-disable-next-line
    }, [])

    return (
        <div className="App">
            <NavbarContainer />
            <Outlet />
            <ToastContainer
                position="bottom-right"
                style={{
                    fontSize: 15
                }}
            />
        </div>
    )
}

export default AppLayout