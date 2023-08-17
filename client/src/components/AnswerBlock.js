import axios from "axios";
import React, { useEffect, useState } from "react";
import styled from "styled-components";
import QuestionBlock from "./QuestionBlock";
import TextEdit from "./TextEdit";
import { answerData, userAnComment } from "../API/ServerAPI";
import { ROOT_URL } from "./../data/rootUrl";

const AnswerBox = styled.div`
	width: 100%;
	border-bottom: 1px solid rgb(227 230 232);
	padding: 16px 0;
	.title {
		margin: 10px 0px 8px;
	}
	.title h2 {
		font-size: 19px;
	}
	.answer {
		margin: 16px 0px;
		border-bottom: 1px solid rgb(227 230 232);
	}
	.yourAnswer {
		font-size: 19px;
		font-weight: 400;
		padding-top: 20px;
		margin-bottom: 19px;
	}
	.btnPrimary {
		font-size: 13px;
		margin: 0px 2px;
		padding: 10.4px;
	}
`;

const AnswerBlock = ({ id }) => {
	const [content, setContent] = useState("");
	const [answersData, setAnswesrData] = useState();

	useEffect(() => {
		const question = answerData(id);
		question.then((res) => {
			setAnswesrData(res);
		});
	}, []);

	const answer = () => {
		const token = window.localStorage.getItem("loginToken");
		const data = JSON.stringify({
			answerBody: content,
		});
		var config = {
			method: "post",
			url: `${ROOT_URL}/questions/${id}/answers/post`,
			headers: {
				"Authorization": token,
				"Content-Type": "application/json",
			},
			data: data,
		};
		axios(config)
			.then(function (response) {
				window.location.replace(`/questions/${id}`);
				console.log(JSON.stringify(response.data));
			})
			.catch(function (error) {
				console.log(error);
			});
	};

	return (
		<AnswerBox>
			{answersData !== undefined ? (
				<div className="title">
					<h2>{answersData && answersData.length} Answers</h2>
				</div>
			) : null}
			{answersData &&
				answersData.map((data, index) => {
					return (
						<div className="answer" key={index}>
							<QuestionBlock type={"answer"} data={data} />
						</div>
					);
				})}
			<h2 className="yourAnswer">Your Answer</h2>
			<div className="editBox">
				<TextEdit setContent={setContent} />
			</div>
			<button className="btnPrimary" onClick={answer}>
				Post Your Answer
			</button>
		</AnswerBox>
	);
};

export default AnswerBlock;
