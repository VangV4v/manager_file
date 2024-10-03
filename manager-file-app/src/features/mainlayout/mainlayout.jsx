import HeaderPage from "./header/header";
import { Route, Routes } from "react-router-dom";
import PublicStorePage from "../public_store/publicstore";
import LoginPage from "../login/login";
import HomePage from "../homepage/homepage";
import ProcessOauth2Page from "../oauth2/process-oauth2";
import OpenFolderPage from "../open-folder/open-folder";
import UploadFilePage from "../uploadfile/upload-file";
import ForgotPasswordPage from "../forgot-password/forgot-password";

function MainLayoutPage() {
    return (
        <>
            <HeaderPage></HeaderPage>
            <Routes>
                <Route path="/public-store" element={<PublicStorePage />}></Route>
                <Route path="/login" element={<LoginPage />}></Route>
                <Route path="/home" element={<HomePage />}></Route>
                <Route path="/open-folder" element={<OpenFolderPage />}></Route>
                <Route path="/upload-file" element={<UploadFilePage />}></Route>
                <Route path="/process-oauth2" element={<ProcessOauth2Page />}></Route>
                <Route path="/forgot-password" element={<ForgotPasswordPage />}></Route>
            </Routes>
        </>
    );
}

export default MainLayoutPage;