import "./App.css";
import styled from "styled-components";
import { useState } from "react";
import UserProfile from "./pages/UserProfile";
import { Routes, Route } from "react-router";

const Section = styled.div`
  border-left: ${(props) =>
    props.flag ? "1px solid rgb(214 217 220)" : "0px"};
  max-width: ${(props) => (props.flag ? "1100px" : "1854px")};
  width: ${(props) => (props.flag ? "calc(100% - 164px)" : "100%")};
  height: 100%;
  padding: 24px;

  @media screen and (max-width: 640px) {
    width: 100%;
  }
`;

function App() {
  const [flag, setFlag] = useState(true);
  //flag : nav 유무 조작
  return (
    <>
      <input></input>
      {/* 헤더 */}
      <main>
        {flag ? <aside></aside> : null}
        <Section flag={flag}>
          <Routes>
            <Route path="/users/:id" element={<UserProfile />} />
            <Route path="/users/*" element={<UserProfile />} />
          </Routes>
        </Section>
      </main>
      {/* 푸터 */}
    </>
  );
}

export default App;
