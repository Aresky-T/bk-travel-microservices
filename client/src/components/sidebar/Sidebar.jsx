import { useLocation, useNavigate } from 'react-router-dom'
import { useDispatch } from 'react-redux'

const Sidebar = ({ handleChangeShowSidebar, publicLinks }) => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const location = useLocation();

    return (
        <div className='sidebar'>
            <div className="sidebar-wrapper">
                <div className="sidebar-title">MENU</div>
                {publicLinks.map(item => (
                    <div className={location.pathname === item.path ? "sidebar-item active" : "sidebar-item"}
                        onClick={() => {
                            if (item.title === 'logout') {
                                item.action(dispatch);
                            }
                            navigate(item.path);
                            handleChangeShowSidebar();
                        }}
                        key={item.name}
                    >
                        {item.name}
                    </div>
                ))}
            </div>
        </div>
    )
}

export default Sidebar