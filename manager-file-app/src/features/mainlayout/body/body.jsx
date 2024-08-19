import { Route, Routes } from "react-router-dom";
import PublicStorePage from "../../public_store/publicstore";

function BodyPage() {
    return (
        <>
            <Routes>
                <Route path='/public-store/' element={<PublicStorePage />}></Route>
            </Routes>
        </>
    );
}

export default BodyPage;