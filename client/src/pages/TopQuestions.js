import Title from "../components/Title";
import { useState, useEffect } from "react";
import styled from "styled-components";
import dummyQuestions from "../data/dummyQuestions";
import Question from "../components/Question";
import axios from "axios";

const Container = styled.div`
	width: 100%;
	.content {
		margin-bottom: 4rem;
	}
`;

const TopContainer = styled.div`
	margin: 0 0 1.2rem;
	padding: 2rem 0 0 1.6rem;
	display: flex;
	justify-content: space-between;

	.askBtn {
		margin-right: 16px;
	}
`;

const QuestionContainer = styled.div`
	padding: 0px;

	.borderLine {
		padding-bottom: 1.2rem;
		border-bottom: 1px solid var(--lightgray2);
	}
`;

const TopQuestions = ({ setFlag, setIsFooter }) => {
	const [data, setData] = useState(dummyQuestions);
	useEffect(() => {
		setFlag(true);
		setIsFooter(true);
	}, []);

    useEffect(() => {           
        axios
            .get('http://prepro31.iptime.org:8080/questions/?page=1&size=10')
            .then(res => {
                setData(res.data.data);
            // console.log(res.data.data);
        });
    })

    return (
        <>
            <Container>
                <div className="content">
                    <TopContainer>
                        <span>
                            <Title title="Top Questions" />
                        </span>
                        <span className="askBtn">
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
                                    <Question
                                        key={el.questionId}
                                        question={el}
                                        page="TopQuestion"
                                    />
                                ))}
                        </div>
                    </QuestionContainer>
                </div>
            </Container>
            {/* Footer */}
        </>
    );
};

export default TopQuestions;
