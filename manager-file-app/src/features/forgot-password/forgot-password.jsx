import { Base64 } from "js-base64";
import { useForm } from "react-hook-form";
import { useLocation, useNavigate } from "react-router-dom";
import queryString from 'querystring';

import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import forgotPasswordAPI from "../../api/forgot-password-api";

const schema = yup
    .object({
        oldPassword: yup.string().required("Please enter your old password"),
        newPassword: yup.string().required("Please enter your new password").oneOf([yup.ref("oldPassword")], "Same"),
    })
    .required()

function ForgotPasswordPage(props) {

    const location = useLocation();
    const { register, handleSubmit } = useForm({
        resolver: yupResolver(schema)
    });
    const email = Base64.decode(queryString.parse(location.search).email);

    const handleSubmitForm = (data) => {

        const newData = {
            "email": email,
            "password": data.newPassword,
        };
        forgotPasswordAPI.updatePassword(newData);
    };

    return (
        <div className="formbold-main-wrapper">
            <div className="formbold-form-wrapper">
                <div className="formbold-form-title">
                    <h3>Forgot Password</h3>
                </div>
                <form onSubmit={handleSubmit(handleSubmitForm)}>
                    <input
                        type="password"
                        name="oldPassword"
                        id="oldPassword"
                        placeholder="Old Password"
                        {...register("oldPassword")}
                        className="formbold-form-input"
                    />
                    <input
                        type="password"
                        name="newPassword"
                        id="newPassword"
                        placeholder="New Password"
                        {...register("newPassword")}
                        className="formbold-form-input"
                    />
                    <button className="formbold-btn">
                        Update Password
                    </button>
                </form>
            </div>
        </div>
    );
}

export default ForgotPasswordPage;