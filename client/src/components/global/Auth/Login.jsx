import React from 'react'
import authImage from '../../../assets/image/auth-image-1.webp'
import {FaUser} from 'react-icons/fa'
import {Link, useNavigate} from 'react-router-dom';
import {RiErrorWarningFill} from 'react-icons/ri';
import {ROUTE} from "../../../constant/route";

const Login = ({loginFormik, activeError, handleIconMouseEnter, handleIconMouseLeave}) => {
    const navigate = useNavigate();

    return (
        <div className='main-session auth'>
            <div className="auth-wrapper">
                <div className="auth-image">
                    <img src={authImage} alt="auth"/>
                </div>
                <div className="auth-main">
                    <div className="auth-user-icon">
                        <FaUser size={50}/>
                    </div>
                    <form className="auth-form login" onSubmit={loginFormik.handleSubmit}>
                        <h2>Đăng nhập</h2>
                        <div className="auth-field">
                            <input type="text" name="username"
                                   placeholder='Tài khoản'
                                   value={loginFormik.values.username}
                                   onChange={loginFormik.handleChange}
                            />
                            {loginFormik.errors.username && activeError === 0 && (
                                <span className="error-message">{loginFormik.errors.username}</span>
                            )}
                            {loginFormik.errors.username && <span
                                className="error-icon"
                                onMouseEnter={() => handleIconMouseEnter(0)}
                                onMouseLeave={handleIconMouseLeave}
                            >
                <RiErrorWarningFill/>
              </span>}
                        </div>
                        <div className="auth-field">
                            <input type="password" name="password"
                                   placeholder='Mật khẩu'
                                   value={loginFormik.values.password}
                                   onChange={loginFormik.handleChange}
                            />
                            {loginFormik.errors.password && activeError === 1 && (
                                <span className="error-message">{loginFormik.errors.password}</span>
                            )}
                            {loginFormik.errors.password && <span
                                className="error-icon"
                                onMouseEnter={() => handleIconMouseEnter(1)}
                                onMouseLeave={handleIconMouseLeave}
                            >
                <RiErrorWarningFill/>
              </span>}
                        </div>
                        <div className="auth-option forgot-password"
                             onClick={() => {
                                 navigate(ROUTE.FORGOT_PASSWORD)
                             }}
                        >
                            Quên mật khẩu?
                        </div>
                        <input
                            type="submit"
                            value="Đăng nhập"
                            className='auth-submit'
                        />
                    </form>
                    <div className='auth-option'>
                        <span className="option-question">Bạn chưa có tài khoản?</span>
                        <Link to='/register' className='option-action'>Đăng ký</Link>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login
