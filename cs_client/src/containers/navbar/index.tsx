import { IconType } from 'react-icons'
import { LuMail } from 'react-icons/lu'
import { RiMessengerLine } from 'react-icons/ri'
import { PATHS } from '../../constants/paths'
import { Link, useLocation } from 'react-router-dom'
import Navbar from '../../components/navbar'

type MenuItemType = {
    name: string,
    title: string,
    path: string,
    icon: IconType,
    isSelected: boolean,
}

type MenuType = {
    [key: string]: MenuItemType
}

const menu: MenuType = {
    chat: {
        name: 'chat',
        title: 'Cuộc hội thoại',
        path: PATHS.CHAT,
        icon: RiMessengerLine,
        isSelected: false,
    },
    mail: {
        name: 'mail',
        title: 'Hộp thư',
        path: PATHS.MAIL,
        icon: LuMail,
        isSelected: false
    }
}

const NavbarContainer = () => {
    const { pathname } = useLocation();

    const renderLinks = () => {
        let links: Map<string, MenuItemType> = new Map();
        for (const menuItemName in menu) {
            const item = menu[menuItemName];
            links.set(menuItemName, item);
        }

        return Array.from(links.values()).map(link => (
            <li className={link.path === pathname ? 'menu_item selected' : 'menu_item'} key={link.title}>
                <Link to={link.path} title={link.title}><link.icon /></Link>
            </li>
        ));
    }

    return (
        <Navbar
            renderLinks={renderLinks}
        />
    )
}

export default NavbarContainer