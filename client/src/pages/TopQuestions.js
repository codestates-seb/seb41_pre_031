import Title from "../components/Title";
import { useState, useEffect } from "react";
import styled from "styled-components";
import dummyQuestions from "../data/dummyQuestions";
import Question from "../components/Question";
import axios from "axios";
import { Link } from "react-router-dom";

const Container = styled.div`
	width: 100%;
	.content {
		margin-bottom: 4rem;
	}
`;

const TopContainer = styled.div`
	margin: 0 0 1.2rem 1.2rem;
	padding: 2rem 0 0 1.6rem;
	display: flex;
	justify-content: space-between;

	.btnPrimary {
		display: block;
		height: 100%;
		border-radius: 3px;
		line-height: var(--line-height-sm);
		font-size: var(--font-button-size);
		padding: 10px;
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
		<Container>
			<div className="content">
				<TopContainer>
					<Title title="Top Questions" />
					<Link to="/questions/ask" className="btnPrimary">
						Ask Question
					</Link>
				</TopContainer>
				<QuestionContainer>
					<div className="borderLine"></div>
					<ul>
						{data
							.slice(data.length - 11, data.length)
							.reverse()
							.map((el) => (
								<Question key={el.questionId} question={el} page="TopQuestion" />
							))}
					</ul>
				</QuestionContainer>
			</div>
		</Container>
	);
};

export default TopQuestions;
