import React from 'react';

function ForgotPassword({ email, handleForgotPassword, handleChangeEmail }) {
    return (
        <div className="main-session forgot-password-container">
            <section className="forgot-password--main">
                <h1 className="forgot-password__title">
                    Lấy lại mật khẩu
                </h1>
                <div className="forgot-password__field">
                    {/* <label>Địa chỉ email</label> */}
                    <input type="text" name="email"
                        placeholder="Địa chỉ email"
                        value={email || ""}
                        onChange={handleChangeEmail}
                    />
                </div>
                <div className="forgot-password__submit-btn">
                    <button
                        onClick={handleForgotPassword}
                    >Gửi
                    </button>
                </div>
                <div className="forgot-password__instruction">
                    <span>(*) Lưu ý: Hãy nhập <b>địa chỉ email</b> mà bạn dùng để đăng ký tài khoản. Chúng tôi sẽ gửi cho bạn một email với mật khẩu mới của bạn.
                        Sau đó sử dụng mật khẩu mới này để đăng nhập và sử dụng tính năng đổi mật khẩu để tăng mức độ bảo mật cho tài khoản của bạn.
                    </span>
                </div>
            </section>
        </div>
    );
}

export default ForgotPassword;