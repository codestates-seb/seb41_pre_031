import styled from "styled-components";

const IconSvg = styled.svg`
	width: 14px;
	height: 14px;
	color: ${(props) => props.color};
	path {
		fill: currentColor;
	}
`;

const IconLinkLogin = ({ color }) => {
	return (
		<IconSvg aria-hidden="true" viewBox="0 0 14 14" color={color}>
			<path d="M5 1H3a2 2 0 0 0-2 2v8c0 1.1.9 2 2 2h8a2 2 0 0 0 2-2V9h-2v2H3V3h2V1Zm2 0h6v6h-2V4.5L6.5 9 5 7.5 9.5 3H7V1Z"></path>
		</IconSvg>
	);
};
export default IconLinkLogin;
