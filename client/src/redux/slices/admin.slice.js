import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getAllToursForAdminApi } from "../../api/admin/tour.api";
import { getAllAccountsApi } from "../../api/admin/account.api";
import { getAllBookingsApi } from "../../api/admin/booking.api";
import { getAllStaffsApi } from "../../api/admin/staff.api";

const initAdminState = {
    tourManager: {
        tourList: {},
        currentTour: null,
        pageNumber: 0,
        totalPages: 1,
        totalElements: 0,
        selectedTourId: null,
        subTourList: [],
        currentSubTour: null,
        tourGuideList: [],
        isReloadCurrentTour: false,
        isReloadTourList: false,
        isReloadSubTourList: false
    },
    touristAttractionManager: {
        touristAttraction: null,
        touristAttractionList: [],
        blogList: [],
        isReloadTouristAttraction: false,
        currentBlog: null,
    },
    staffManager: {
        staffList: [],
        currentStaff: null,
        departmentList: [],
        currentDepartment: null,
        positionList: [],
        currentPosition: null,
    },
    bookingManager: {
        bookingList: [],
        totalPages: 1,
        totalElements: 0,
        selectedBookingId: null,
    },
    accountManager: {
        accountList: [],
        currentAccount: null,
        pageNumber: 0,
        totalPages: 1,
        totalElements: 0,
        isRefetch: false,
    }
};

export const fetchAllToursThunk = createAsyncThunk(
    "admin/fetchAllTours",
    async function (pagination, { rejectWithValue }) {
        try {
            if (!pagination) {
                pagination = {
                    page: 0, size: 5
                }
            }

            const res = await getAllToursForAdminApi(pagination);
            return res.data;
        } catch (error) {
            return rejectWithValue(error)
        }
    }
)

export const fetchAllAccountsThunk = createAsyncThunk(
    "admin/fetchAllAccountsThunk",
    async function (pagination, { rejectWithValue }) {
        try {
            if (!pagination) {
                pagination = {
                    page: 0, size: 5, sort: "username,ASC"
                }
            }

            const res = await getAllAccountsApi(pagination);
            return res.data;
        } catch (error) {
            return rejectWithValue(error)
        }
    }
)

export const fetchAllBookingsThunk = createAsyncThunk(
    "admin/fetchAllBookingsThunk",
    async function (pagination, { rejectWithValue }) {
        try {
            if (!pagination) {
                pagination = {
                    page: 0, size: 5, sort: "bookedTime,DESC"
                }
            }

            const res = await getAllBookingsApi(pagination);
            return res.data;
        } catch (error) {
            return rejectWithValue(error)
        }
    }
)

export const fetchAllStaffsThunk = createAsyncThunk(
    "admin/fetchAllStaffsThunk",
    async function (pagination, { rejectWithValue }) {
        try {
            if (!pagination) {
                pagination = {
                    page: 0, size: 5, sort: "bookedTime,DESC"
                }
            }

            const res = await getAllStaffsApi(pagination);
            return res.data;
        } catch (error) {
            return rejectWithValue(error)
        }
    }
)

const adminSlice = createSlice({
    name: "admin",
    initialState: { ...initAdminState },
    reducers: {
        onUpdateTourManager: (state, action) => {
            state.tourManager = action.payload;
        },
        onUpdateStaffManager: (state, action) => {
            state.staffManager = action.payload;
        },

        //! For touristAttraction
        onUpdateAdminTouristAttraction: (state, action) => {
            state.touristAttractionManager.touristAttraction = action.payload;
        },
        onUpdateAdminTouristAttractionBlogList: (state, action) => {
            state.touristAttractionManager.blogList = action.payload;
        },
        onUpdateAdminCurrentBlog: (state, action) => {
            state.touristAttractionManager.currentBlog = action.payload;
        },
        onUpdateAdminIsReloadTouristAttraction: (state, action) => {
            state.touristAttractionManager.isReloadTouristAttraction = action.payload;
        },

        //! For tour
        onUpdateAdminTourList: (state, action) => {
            state.tourManager.tourList = action.payload;
        },
        onUpdateAdminSubTourList: (state, action) => {
            state.tourManager.subTourList = action.payload;
        },
        onUpdateAdminCurrentTour: (state, action) => {
            const tour = action.payload;
            state.tourManager.currentTour = tour;
            state.tourManager.selectedTourId = tour ? tour.id : null;
        },
        onUpdateAdminSelectedTourId: (state, action) => {
            state.tourManager.selectedTourId = action.payload;
        },
        onUpdateAdminCurrentSubTour: (state, action) => {
            state.tourManager.currentSubTour = action.payload;
        },
        onUpdateAdminTourGuideList: (state, action) => {
            state.tourManager.tourGuideList = action.payload;
        },
        onReloadAdminTourListStart: (state, action) => {
            state.tourManager.isReloadTourList = true;
        },
        onReloadAdminTourListEnd: (state, action) => {
            state.tourManager.isReloadTourList = false;
        },
        onReloadAdminCurrentTourStart: (state, action) => {
            state.tourManager.isReloadCurrentTour = true;
        },
        onReloadAdminCurrentTourEnd: (state, action) => {
            state.tourManager.isReloadCurrentTour = false;
        },
        onReloadAdminSubTourListStart: (state, action) => {
            state.tourManager.isReloadSubTourList = true;
        },
        onReloadAdminSubTourListEnd: (state, action) => {
            state.tourManager.isReloadSubTourList = false;
        },

        //! For booking
        onUpdateAdminSelectedBookingId: (state, action) => {
            state.bookingManager.selectedBookingId = action.payload;
        },

        //! For account
        onUpdateAdminAccountList: (state, action) => {
            state.accountManager.accountList = action.payload;
        },
        onUpdateAdminCurrentAccount: (state, action) => {
            state.accountManager.currentAccount = action.payload
        },
        onRefetchAdminAccountList: (state, action) => {
            state.accountManager.isRefetch = true;
        },
        onResetAminState: (state, action) => {
            state = { ...initAdminState }
        },

        //! For staff
        onUpdateAdminCurrentStaff: (state, action) => {
            state.staffManager.currentStaff = action.payload;
        }
    }, extraReducers: (builder) => {

        //! for tour state
        builder.addCase(fetchAllToursThunk.fulfilled, (state, action) => {
            const data = action.payload;
            state.tourManager.tourList = data.content;
            state.tourManager.pageNumber = data.pageable.pageNumber;
            state.tourManager.totalPages = data.totalPages;
            state.tourManager.totalElements = data.totalElements;
            state.tourManager.isRefetch = false;
        });

        builder.addCase(fetchAllToursThunk.rejected, (state, action) => {
        });

        //! for account state
        builder.addCase(fetchAllAccountsThunk.fulfilled, (state, action) => {
            const data = action.payload;
            state.accountManager.accountList = data.content;
            state.accountManager.pageNumber = data.pageable.pageNumber;
            state.accountManager.totalPages = data.totalPages;
            state.accountManager.totalElements = data.totalElements;
            state.accountManager.isRefetch = false;
        });

        builder.addCase(fetchAllAccountsThunk.rejected, (state, action) => {
        });

        //! for booking state
        builder.addCase(fetchAllBookingsThunk.fulfilled, (state, action) => {
            const data = action.payload;
            state.bookingManager.bookingList = data.content;
            state.bookingManager.totalPages = data.totalPages;
            state.bookingManager.totalElements = data.totalElements;
        });

        builder.addCase(fetchAllBookingsThunk.rejected, (state, action) => { });

        //! for staff state
        builder.addCase(fetchAllStaffsThunk.fulfilled, (state, action) => {
            const data = action.payload;
            state.staffManager.staffList = data.content;
            state.staffManager.totalPages = data.totalPages;
            state.staffManager.totalElements = data.totalElements;
        });

        builder.addCase(fetchAllStaffsThunk.rejected, (state, action) => { });
    }
})


export const {
    onUpdateTourManager,
    onUpdateStaffManager,
    onUpdateAdminTourList,
    onUpdateAdminSubTourList,
    onUpdateAdminCurrentTour,
    onUpdateAdminSelectedTourId,
    onUpdateAdminCurrentSubTour,
    onUpdateAdminTourGuideList,
    onReloadAdminTourListStart,
    onReloadAdminTourListEnd,
    onReloadAdminCurrentTourStart,
    onReloadAdminCurrentTourEnd,
    onReloadAdminSubTourListStart,
    onReloadAdminSubTourListEnd,
    onResetAminState,
    onRefetchAdminAccountList,
    onUpdateAdminAccountList,
    onUpdateAdminCurrentAccount,
    onUpdateAdminCurrentBlog,
    onUpdateAdminIsReloadTouristAttraction,
    onUpdateAdminSelectedBookingId,
    onUpdateAdminTouristAttraction,
    onUpdateAdminTouristAttractionBlogList,
    onUpdateAdminCurrentStaff,
} = adminSlice.actions;
export default adminSlice.reducer;