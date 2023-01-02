import Title from "../components/Title";
import { useState, useEffect } from "react";
import styled from "styled-components";
import dummyQuestions from "../data/dummyQuestions";
import Question from "../components/Question";
import Pagination from "../components/Pagination";
import { Link } from "react-router-dom";
import axios from "axios";

const Container = styled.div`
	.content {
		margin-bottom: 4rem;
	}
	width: 100%;
`;

const TopContainer = styled.div`
	margin: 0 0 12px 0;
	padding: 24px 16px 0 16px;
	display: flex;
	justify-content: space-between;

	.askBtn {
		flex: none;
		button {
			cursor: pointer;
		}
	}
`;

const QuestionContainer = styled.div`
	padding: 0px;

	.questionCount {
		padding: 12px 16px;
		line-height: var(--line-height-sm);
		border-bottom: 1px solid var(--lightgray2);
		font-size: var(--font-head3-size);
	}
`;

const PageContainer = styled.div`
	margin: 6.2rem 0 1.3rem 1.6rem;
	display: flex;
	justify-content: space-between;
`;

const Button = styled.button`
	margin: 0 0.2rem;
	font-size: var(--font-button-size);
	border: 1px solid var(--lightgray2);
	padding: 0px 9px;
	height: 27px;
	color: #3b4045;

	&[aria-current] {
		background: var(--orange);
		border: 1px solid var(--orange);
		color: var(--white);
		&:hover {
			background: var(--orange);
		}
	}

	&:hover {
		background: var(--lightgray2);
		cursor: pointer;
	}
`;

const PerpageText = styled.span`
	padding: 0 0.8rem;
	font-size: var(--font-button-size);
`;

const AllQuestions = ({ setFlag, setIsFooter }) => {
	useEffect(() => {
		setFlag(true);
		setIsFooter(true);
	}, []);

	const [data, setData] = useState(dummyQuestions);
	// 현재 페이지
	const [page, setPage] = useState(1);
	// 보여줄 최대 게시글 수
	const [limit, setLimit] = useState(15);

	useEffect(() => {
		axios.get("http://34.64.176.88:8080/questions/?page=1&size=10").then((res) => {
			setData(res.data.data);
			// console.log(res.data.data);
		});
	}, [data]);

	// 첫 게시물의 index
	const offset = (page - 1) * limit;

	return (
		<>
			<Container>
				<div className="content">
					<TopContainer>
						<Title title="All Questions" />
						<Link to="/questions/ask" className="askBtn">
							<button className="btnPrimary">Ask Question</button>
						</Link>
					</TopContainer>
					<QuestionContainer>
						<div className="questionCount">{data.length} questions</div>
						<ul>
							{data
								.slice(offset, offset + limit)
								.reverse()
								.map((el) => (
									<Question key={el.questionId} question={el} />
								))}
						</ul>
					</QuestionContainer>
				</div>
				<PageContainer>
					<Pagination total={data.length} limit={limit} page={page} setPage={setPage} />
					<span>
						<Button
							onClick={() => {
								setLimit(15);
								window.scrollTo({ top: 0 });
							}}
							aria-current={limit === 15 ? "page" : null}
						>
							15
						</Button>
						<Button
							onClick={() => {
								setLimit(30);
								window.scrollTo({ top: 0 });
							}}
							aria-current={limit === 30 ? "page" : null}
						>
							30
						</Button>
						<Button
							onClick={() => {
								setLimit(50);
								window.scrollTo({ top: 0 });
							}}
							aria-current={limit === 50 ? "page" : null}
						>
							50
						</Button>
						<PerpageText>per page</PerpageText>
					</span>
				</PageContainer>
			</Container>
		</>
	);
};

export default AllQuestions;
