import HeaderPage from "./header/header";
import { Route, Routes } from "react-router-dom";
import PublicStorePage from "../public_store/publicstore";
import LoginPage from "../login/login";
import HomePage from "../homepage/homepage";
import ProcessOauth2Page from "../oauth2/process-oauth2";

function MainLayoutPage() {
    return (
        <>
            <HeaderPage></HeaderPage>
            <Routes>
                <Route path="/public-store" element={<PublicStorePage />}></Route>
                <Route path="/login" element={<LoginPage />}></Route>
                <Route path="/home" element={<HomePage />}></Route>
                <Route path="/process-oauth2" element={<ProcessOauth2Page />}></Route>
            </Routes>
        </>
    );
}

export default MainLayoutPage;