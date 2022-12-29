import styled from "styled-components";
import { createGlobalStyle } from "styled-components";
import { useEffect } from "react";
import { BREAK_POINT_MOBILE, BREAK_POINT_TABLET } from "../data/breakpoints";
import MainSection01 from "../components/MainSection01";
import MainSection02 from "../components/MainSection02";
import MainSection03 from "../components/MainSection03";
import MainSection04 from "../components/MainSection04";
import MainSection05 from "../components/MainSection05";
import MainSection06 from "../components/MainSection06";

const MainGlobalStyle = createGlobalStyle`
.onlyViewPC{
	display:block;
}
.onlyViewMobile{
	display:none;
}
@media screen and (max-width: ${BREAK_POINT_TABLET}px) {
	.onlyViewPC{
		display:none;
	}
	.onlyViewMobile{
		display:block;
	}
}
@media screen and (max-width: ${BREAK_POINT_MOBILE}px) {
	.onlyViewPC{
		display:none;
	}
	.onlyViewMobile{
		display:block;
	}
}
`;

const MainWrap = styled.div`
	width: 100%;
	background: var(--white3);
`;

const MainBeforeLogin = ({ setFlag, setIsFooter }) => {
	useEffect(() => {
		setFlag(false);
		setIsFooter(true);
	}, []);

	return (
		<MainWrap>
			<MainGlobalStyle />
			<MainSection01 />
			<MainSection02 />
			<MainSection03 />
			<MainSection04 />
			<MainSection05 />
			<MainSection06 />
		</MainWrap>
	);
};
export default MainBeforeLogin;
