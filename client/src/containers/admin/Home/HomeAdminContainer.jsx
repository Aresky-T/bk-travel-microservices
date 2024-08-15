import {ROUTE} from "../../../constant/route";
import HomeAdmin from "../../../components/admin/Home/HomeAdmin";

const HomeAdminContainer = () => {

    const menu = [
        {
            title: "Tours Manager",
            path: ROUTE.TOUR_MANAGER
        },
        {
            title: "Tourist Attraction Manager",
            path: ROUTE.TOURIST_ATTRACTION_MANAGER
        },
        {
            title: "Staffs Manager",
            path: ROUTE.STAFF_MANAGER
        },
        {
            title: "Booking Manager",
            path: ROUTE.BOOKING_MANAGER
        },
        {
            title: "Accounts Manager",
            path: ROUTE.ACCOUNT_MANAGER
        }
    ]

    return (
        <>
            <HomeAdmin menu={menu}/>
        </>
    )
}

export default HomeAdminContainer;