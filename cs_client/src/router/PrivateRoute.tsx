import { useEffect } from 'react'
import { useAppContext } from '../store/context';
import { useNavigate } from 'react-router-dom';

const PrivateRoute = (props: any) => {
    const { state } = useAppContext();
    const { token } = state.auth.data;
    const navigate = useNavigate();

    useEffect(() => {
        if (!token) {
            navigate("/")
        }
        // eslint-disable-next-line
    }, [token])

    useEffect(() => {
        document.title = props.title;
    })

    return (
        <>{props.children}</>
    )
}

export default PrivateRoute