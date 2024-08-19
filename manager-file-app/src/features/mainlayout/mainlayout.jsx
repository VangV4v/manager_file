import BodyPage from "./body/body";
import HeaderPage from "./header/header";
import { Layout } from "antd";
import { Content } from "antd/es/layout/layout";

function MainLayoutPage() {
    return (
        <>
            <Layout>
                <HeaderPage></HeaderPage>
                <Content>
                    <BodyPage></BodyPage>
                </Content>
            </Layout>
        </>
    );
}

export default MainLayoutPage;