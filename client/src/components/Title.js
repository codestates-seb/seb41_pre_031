import styled from "styled-components";

const TitleStyle = styled.h1`
    margin: 0 1.2rem 1.2rem 0;
    font-size: var(--font-head1-size);
`;
const Title = ({ title }) => {
    return <TitleStyle>{title}</TitleStyle>;
};

export default Title;
