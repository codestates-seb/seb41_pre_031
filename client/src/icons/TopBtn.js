import styled from "styled-components";

const TopBtnSvg = styled.svg`
	width: 36px;
	height: 36px;
	path {
		fill: ${(props) => props.color};
	}
`;

const TopBtn = ({ color }) => {
	return (
		<TopBtnSvg color={color} viewBox="0 0 36 36">
			<path d="M2 25h32L18 9 2 25Z"></path>
		</TopBtnSvg>
	);
};
export default TopBtn;
