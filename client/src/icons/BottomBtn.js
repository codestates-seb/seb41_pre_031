import styled from "styled-components";

const BottomBtnSvg = styled.svg`
	width: 36px;
	height: 36px;
	path {
		fill: ${(props) => props.color};
	}
`;

const BottomBtn = ({ color }) => {
	return (
		<BottomBtnSvg color={color} viewBox="0 0 36 36">
			<path d="M2 11h32L18 27 2 11Z"></path>
		</BottomBtnSvg>
	);
};
export default BottomBtn;
