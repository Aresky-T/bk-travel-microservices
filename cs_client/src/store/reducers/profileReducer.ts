import { Reducer } from "react";
import { IProfileState } from "../../types/profile";
import { ActionType } from "../../types/actions";

const profileReducer: Reducer<IProfileState, ActionType> = (state, action) => {
    const { type } = action;
    switch (type) {
        case "PROFILE|ADD_DATA":
            return { ...state, data: action.payload }
        case "PROFILE|REMOVE_DATA":
            return { ...state, data: null }
    }

    return state;
}

export default profileReducer;