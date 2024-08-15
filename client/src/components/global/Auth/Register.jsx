import React from 'react'
import authImage from '../../../assets/image/auth-image-1.webp'
import {FaUser} from 'react-icons/fa'
import {Link} from 'react-router-dom'
import {RiErrorWarningFill} from 'react-icons/ri';

const Register = ({registerFormik, activeError, handleIconMouseEnter, handleIconMouseLeave}) => {

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
                    <form className="auth-form register" onSubmit={registerFormik.handleSubmit}>
                        <h2>Đăng ký</h2>
                        <div className="auth-field">
                            <input type="email"
                                   name="email"
                                   placeholder='Email'
                                   value={registerFormik.values.email}
                                   onChange={registerFormik.handleChange}
                            />
                            {registerFormik.errors.email && activeError === 0 && (
                                <span className="error-message">{registerFormik.errors.email}</span>
                            )}
                            {registerFormik.errors.email && <span
                                className="error-icon"
                                onMouseEnter={() => handleIconMouseEnter(0)}
                                onMouseLeave={handleIconMouseLeave}
                            >
                <RiErrorWarningFill/>
              </span>}
                        </div>
                        <div className="auth-field">
                            <input type="text"
                                   name="username"
                                   placeholder='Tài khoản'
                                   value={registerFormik.values.username}
                                   onChange={registerFormik.handleChange}
                            />
                            {registerFormik.errors.username && activeError === 1 && (
                                <span className="error-message">{registerFormik.errors.username}</span>
                            )}
                            {registerFormik.errors.username &&
                                <span
                                    className="error-icon"
                                    onMouseEnter={() => handleIconMouseEnter(1)}
                                    onMouseLeave={handleIconMouseLeave}
                                >
                  <RiErrorWarningFill/>
                </span>}
                        </div>
                        <div className="auth-field">
                            <input type="password"
                                   name="password"
                                   placeholder='Mật khẩu'
                                   value={registerFormik.values.password}
                                   onChange={registerFormik.handleChange}
                            />
                            {registerFormik.errors.password && activeError === 2 && (
                                <span className="error-message">{registerFormik.errors.password}</span>
                            )}
                            {registerFormik.errors.password &&
                                <span
                                    className="error-icon"
                                    onMouseEnter={() => handleIconMouseEnter(2)}
                                    onMouseLeave={handleIconMouseLeave}
                                >
                  <RiErrorWarningFill/>
                </span>}
                        </div>
                        <div className="auth-field">
                            <input type="password"
                                   name="confirmPassword"
                                   placeholder='Xác nhận lại mật khẩu'
                                   value={registerFormik.values.confirmPassword}
                                   onChange={registerFormik.handleChange}
                            />
                            {registerFormik.errors.confirmPassword && activeError === 3 && (
                                <span className="error-message">{registerFormik.errors.confirmPassword}</span>
                            )}
                            {registerFormik.errors.confirmPassword &&
                                <span
                                    className="error-icon"
                                    onMouseEnter={() => handleIconMouseEnter(3)}
                                    onMouseLeave={handleIconMouseLeave}
                                >
                  <RiErrorWarningFill/>
                </span>}
                        </div>


                        <input type="submit" value="Đăng ký" className='auth-submit'/>
                    </form>
                    <div className='auth-option'>
                        <span className="option-question">Bạn đã có tài khoản?</span>
                        <Link to='/login' className='option-action'>Đăng nhập</Link>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Register