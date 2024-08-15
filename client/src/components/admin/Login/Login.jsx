import {Link} from "react-router-dom";
import React from "react";

const Login = ({authFormik}) => {
    return (
        <>
            <form className='login-form'
                  onSubmit={authFormik.handleSubmit}
            >
                <h2 className="form-title">LOGIN ADMIN</h2>
                <input type="text"
                       name="username"
                       className="form-field-ad"
                       placeholder='Username'
                       value={authFormik.values.username}
                       onChange={authFormik.handleChange}

                />
                <input type="password"
                       name="password"
                       className="form-field-ad"
                       placeholder='Password'
                       value={authFormik.values.password}
                       onChange={authFormik.handleChange}
                />
                <Link className='forgot-password-admin'>Forgot password?</Link>
                <input type="submit" value="Login" className='login-submit-admin' />
            </form>
        </>
    )
}

export default Login;