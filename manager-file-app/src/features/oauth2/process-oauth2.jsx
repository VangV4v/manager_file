import { useLocation } from "react-router-dom";
import queryString from "querystring";
import { useEffect } from "react";

function ProcessOauth2Page() {

    const location = useLocation();
    const param = queryString.parse(location.search);
    useEffect(() => {

        const authUser = {
            authenticated: true,
            jwt: param.jwt,
            expiration: param.expiration,
            role: "ROLE_USER",
        };
        // console.log(authUser);
        localStorage.setItem("authUserInformation", JSON.stringify(authUser));
        window.location.href = "/home";
    }, [param]);

    return (
        <>

        </>
    );
}

export default ProcessOauth2Page;