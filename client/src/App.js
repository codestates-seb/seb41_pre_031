import styled from "styled-components";
import Header from "./components/Header";
import Nav from "./components/Nav";
import Footer from "./components/Footer";
import Users from "./pages/Users";
import { useState } from "react";
import { BREAK_POINT_MOBILE } from "./data/breakpoints";
import UserProfile from "./pages/UserProfile";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainBeforeLogin from "./pages/MainBeforeLogin";
import AllQuestions from "./pages/AllQuestions";
import TopQuestions from "./pages/TopQuestions";
import Login from "./pages/Login";

const Wrap = styled.div`
	display: flex;
	flex-direction: column;
	height: 100%;
`;

const Main = styled.main`
	display: flex;
	flex: 1;
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
		position: sticky;
		height: 133px;
		top: 73px;
	}
	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		display: none;
	}
`;

const Section = styled.div`
	display: flex;
	flex: 1;
	max-width: ${(props) => (props.flag ? "1100px" : "100%")};
	width: ${(props) => (props.flag ? "calc(100% - 164px)" : "100%")};
	padding: ${(props) => (props.flag ? "24px" : "0")};
	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		width: 100%;
	}
`;

function App() {
	const [flag, setFlag] = useState(true); //왼쪽 side nav 유무 조작: 각 page 컴포넌트에서 useEffect 사용하세용
	const [isLogin, setIsLogin] = useState(false); //임시 로그인 여부 판별
	const [isFooter, setIsFooter] = useState(true); //footer 유무 조작

	return (
		<Wrap>
			<Header flag={flag} setFlag={setFlag} />
			<Main className={flag ? "container" : ""}>
				{flag ? (
					<LeftSide>
						<Nav />
					</LeftSide>
				) : null}
				<Section flag={flag}>
					<Routes>
						<Route path="/" element={isLogin ? <TopQuestions /> : <MainBeforeLogin setFlag={setFlag} />} />
						<Route path="/users" element={<Users />} />
						<Route path="/users/profile/:id/*" element={<UserProfile />} />
						<Route path="/questions" element={<AllQuestions setFlag={setFlag} />} />
						<Route path="/login" element={<Login setFlag={setFlag} setIsFooter={setIsFooter} />} />
					</Routes>
				</Section>
			</Main>
			{isFooter ? <Footer /> : null}
		</Wrap>
	);
}
export default App;
