import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import styled from "styled-components";
import AnswerBlock from "../components/AnswerBlock";
import QuestionBlock from "../components/QuestionBlock";
import { questionData, userQuComment } from "../API/ServerAPI";

const DetailBox = styled.div`
  width: 100%;
  padding: 24px;
`;

const DetailHeader = styled.div`
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-between;
  h1 {
    overflow-wrap: break-word;
    margin-bottom: 8px;
    flex: 1 auto;
    line-height: var(--line-height-md);
    font-size: 27px;
    color: rgb(59 64 69);
  }
  .btnPrimary {
    margin-left: 12px;
    font-size: 13px;
    padding: 10px;
    width: 102px;
    height: 38px;
  }
`;
const DetailDate = styled.div`
  display: flex;
  padding-bottom: 8px;
  margin-bottom: 16px;
  flex-wrap: wrap;
  border-bottom: 1px solid var(--lightgray2);
  .DateBox {
    white-space: nowrap;
    margin-bottom: 8px;
    margin-right: 16px;
    font-size: 13px;
  }
  .DateBox2 {
    white-space: nowrap;
    margin-bottom: 8px;
    font-size: 13px;
  }
  span {
    color: var(--gray);
    margin-right: 2px;
  }
`;
const DetailBody = styled.div`
  width: 100%;
  float: left;
`;

const QuestionsDetail = ({ setFlag, setIsFooter }) => {
  let { id } = useParams();
  const [questionDatas, setQuestionDatas] = useState();
  const [questionComment, setQuestionComment] = useState();
  useEffect(() => {
    setFlag(true);
    setIsFooter(true);

    const question = questionData(id);
    question.then((res) => {
      setQuestionDatas(res);
    });

    const comment = userQuComment(id);
    comment.then(res => {
        setQuestionComment(res);
    })
  }, []);

  return (
    <DetailBox>
      <DetailHeader>
        <h1>{questionDatas && questionDatas.questionTitle}</h1>
        <div>
          <Link to="/questions/ask">
            <button className="btnPrimary">Ask Question</button>
          </Link>
        </div>
      </DetailHeader>
      <DetailDate>
        <div className="DateBox">
          <span>Asked</span>
          <time>{questionDatas && questionDatas.questionRegDate}</time>
        </div>
        <div className="DateBox">
          <span>Modified</span>
          <time>{questionDatas && questionDatas.questionLastDate}</time>
        </div>
        <div className="DateBox2">
          <span>Viewed</span>
          <time>{questionDatas && questionDatas.questionView} times</time>
        </div>
      </DetailDate>
      <DetailBody>
        <QuestionBlock type={"question"} data={questionDatas} comment={questionComment} quId={id}/>
        <AnswerBlock id={id}/>
      </DetailBody>
    </DetailBox>
  );
};

export default QuestionsDetail;
