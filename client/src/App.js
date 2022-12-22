import styled from "styled-components";
import Header from "./components/Header";
import { useState } from "react";
import { BREAK_POINT_MOBILE } from "./data/breakpoints";

const Section = styled.div`
	max-width: ${(props) => (props.flag ? "1100px" : "1854px")};
	width: ${(props) => (props.flag ? "calc(100% - 164px)" : "100%")};
	height: 100%;
	padding: 24px;

	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		width: 100%;
	}
`;

const Main = styled.main`
	display: flex;
	padding-top: 50px;
`;

const LeftSide = styled.aside`
	width: 164px;
	height: 100%;
	border-right: 1px solid rgb(214 217 220);
	@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
		display: none;
	}
`;

function App() {
	const [flag, setFlag] = useState(true);
	//flag : nav 유무 조작
	return (
		<>
			<Header />
			<main className="container">
				{flag ? <LeftSide></LeftSide> : null}
				<Section flag={flag}></Section>
			</main>
			{/* 푸터 */}
		</>
	);
}
export default App;
