import { useReducer } from "react";
import { localStorageConfig } from "../config/localStorageConfig";
import AppContext from "./context";
import rootReducer, { RootState } from "./reducers";
import { IMailState } from "../types/mail";
import { IAuth, IAuthState } from "../types/auth";
import { IEmployeeState } from "../types/employee";
import { IChatState } from "../types/chat";
import { IProfileState } from "../types/profile";

const authStorage = localStorageConfig("acc_info");

const initAuthData: IAuth = {
  id: authStorage.getItem("id"),
  token: authStorage.getItem("token"),
  type: authStorage.getItem("type"),
  username: authStorage.getItem("username"),
  email: authStorage.getItem("email"),
  role: authStorage.getItem("role"),
  status: authStorage.getItem("status"),
};

export const initAuthState: IAuthState = {
  data: initAuthData,
  isLoading: false,
  isSuccess: false,
  isError: false,
  errorMessage: "",
};

export const initProfileState: IProfileState = {
  data: null,
};

export const initEmployeeState: IEmployeeState = {
  data: null,
  isConnected: false,
};

export const initMailState: IMailState = {
  staff: null,
  mailBoxList: [],
  currentMailBox: null,
  currentMail: null,
};

export const initChatState: IChatState = {
  staff: null,
  conversations: [],
  currentConversation: null,
};

const initState: RootState = {
  auth: initAuthState,
  profile: initProfileState,
  employee: initEmployeeState,
  mail: initMailState,
  chat: initChatState,
};

const ContextProvider = (props: any) => {
  const [state, dispatch] = useReducer(rootReducer, initState);
  return (
    <AppContext.Provider value={{ state, dispatch }}>
      {props.children}
    </AppContext.Provider>
  );
};

export default ContextProvider;
