import Title from "../components/Title";
import { useState } from "react";
import styled from "styled-components";
import dummyQuestions from "../data/dummyData";
import Question from "../components/Question";

const Container = styled.div`
    padding: 2.4rem 1.6rem;
`;

const TopContainer = styled.div`
    margin: 0 0 1.2rem;
    display: flex;
    justify-content: space-between;
`;

const QuestionContainer = styled.ul`
    padding: 0px;

    .borderLine {
        padding-bottom: 1.2rem;
        /* border-bottom: 1px solid hsl(210, 8%, 90%); */
        border-bottom: 1px solid var(--lightgray);
    }
`;

const TopQuestions = () => {
    const [data, setData] = useState(dummyQuestions);

    return (
        <>
            <Container>
                <TopContainer>
                  <span>
                    <Title title="Top Questions" />
                  </span>
                  <span>
                  <button className="btnPrimary">Ask Question</button>
                  </span>
                </TopContainer>
                <QuestionContainer>
                    <div className="borderLine"></div>
                    <div>
                        {data
                            .slice(0, 10)
                            .reverse()
                            .map((el) => (
                                <Question key={el.questionId} question={el} />
                            ))}
                    </div>
                </QuestionContainer>
            </Container>
            {/* Footer */}
        </>
    );
};

export default TopQuestions;
