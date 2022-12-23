import styled from "styled-components";
import Header from "./components/Header";
import Nav from "./components/Nav";
import { useState } from "react";
import { BREAK_POINT_MOBILE } from "./data/breakpoints";
import UserProfile from "./pages/UserProfile";
import { Routes, Route } from "react-router";

const Wrap = styled.div`
    display: flex;
    flex-direction: column;
    height: 100%;
    footer {
        width: 100%;
        height: 240px;
        background: #333;
    }
`;

const Section = styled.div`
    display: flex;
    flex: 1;
    max-width: ${(props) => (props.flag ? "1100px" : "1854px")};
    width: ${(props) => (props.flag ? "calc(100% - 164px)" : "100%")};
    padding: 24px;
    @media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
        width: 100%;
    }
`;

const Main = styled.main`
    display: flex;
    flex: 1;
    /* flex-wrap: nowrap; */
    overflow: auto;
    padding-top: 50px;
`;

const LeftSide = styled.aside`
    display: flex;
    flex: none;
    width: 164px;
    padding-top: 24px;
    border-right: 1px solid rgb(214 217 220);
    nav {
        width: 100%;
    }
    @media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
        display: none;
    }
`;

function App() {
    const [flag, setFlag] = useState(true);
    //flag : nav 유무 조작
    return (
        <Wrap>
            <Header />
            <Main className="container">
                {flag ? (
                    <LeftSide>
                        <Nav />
                    </LeftSide>
                ) : null}
                <Section flag={flag}>
                    <Routes>
                        <Route
                            path="/users/profile/:id/*"
                            element={<UserProfile />}
                        />
                    </Routes>
                </Section>
            </Main>
        </Wrap>
    );
}
export default App;
