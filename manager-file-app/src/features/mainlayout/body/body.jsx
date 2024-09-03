import { Route, Routes } from "react-router-dom";
import PublicStorePage from "../../public_store/publicstore";
import LoginPage from "../../login/login";

function BodyPage() {
    return (
        <>
            <Routes>
                <Route path='/public-store/' element={<PublicStorePage />}></Route>
                <Route path='/login/' element={<LoginPage />}></Route>
            </Routes>
        </>
    );
}

export default BodyPage;