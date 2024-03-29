import styled from "styled-components";

const IconSvg = styled.svg`
	width: 26px;
	height: 26px;
	color: ${(props) => props.color};
	path {
		fill: currentColor;
	}
	.path2 {
		opacity: 0.5;
	}
`;

const IconArrowSignUp = ({ color }) => {
	return (
		<IconSvg>
			<path d="M12 .7a2 2 0 013 0l8.5 9.6a1 1 0 01-.7 1.7H4.2a1 1 0 01-.7-1.7L12 .7z"></path>
			<path className="path2" d="M20.6 16H6.4l7.1 8 7-8zM15 25.3a2 2 0 01-3 0l-8.5-9.6a1 1 0 01.7-1.7h18.6a1 1 0 01.7 1.7L15 25.3z"></path>
		</IconSvg>
	);
};
export default IconArrowSignUp;
