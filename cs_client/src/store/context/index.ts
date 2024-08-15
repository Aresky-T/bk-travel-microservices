import { initMailState, initEmployeeState, initAuthState, initChatState, initProfileState } from './../index';
import { createContext, useContext } from "react"
import { RootState } from "../reducers"
import { ActionType } from '../../types/actions';

type ContextType = {
    state: RootState,
    dispatch?: React.Dispatch<ActionType>
}

const AppContext = createContext<ContextType>({
    state: {
        auth: {
            ...initAuthState
        },
        profile: {
            ...initProfileState
        },
        employee: {
            ...initEmployeeState
        },
        mail: {
            ...initMailState
        },
        chat: {
            ...initChatState
        }
    }
});

export const useAppContext = () => {
    return useContext(AppContext);
}

export default AppContext