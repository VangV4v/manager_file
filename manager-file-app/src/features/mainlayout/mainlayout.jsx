import { Box, Container, Typography } from "@mui/material";
import HeaderPage from "./header/header";
import { Route, Routes } from "react-router-dom";
import PublicStorePage from "../public_store/publicstore";
import LoginPage from "../login/login";

function MainLayoutPage() {
    return (
        <>
            <HeaderPage></HeaderPage>
            <Routes>
                <Route path="/login" element={<LoginPage />}></Route>
            </Routes>
        </>
    );
}

export default MainLayoutPage;