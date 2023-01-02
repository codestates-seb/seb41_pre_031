import React, { useEffect, useRef, useState } from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import BottomBtn from "../icons/BottomBtn";
import TopBtn from "../icons/TopBtn";
import { BREAK_POINT_TABLET } from "./../data/breakpoints";
import { userAnComment } from "../API/ServerAPI";
import axios from "axios";

const QuestionBox = styled.div`
  clear: both;
  .layout {
    display: grid;
    grid-template-columns: max-content 1fr;
  }
`;
const QuestionVote = styled.div`
  width: auto;
  padding-right: 16px;
  vertical-align: top;
  grid-column: 1;
  .voteBox {
    display: flex;
    align-items: stretch;
    justify-content: center;
    flex-direction: column;
    margin: -2px;
    color: var(--gray);
  }
  .voteBox button {
    height: 36px;
    margin: 2px;
    cursor: pointer;
    position: relative;
    padding: 0px;
  }
  .voteCount {
    margin: 2px;
    display: flex;
    font-size: 21px;
    align-items: center;
    flex-direction: column;
    color: var(--gray);
  }
`;
const QuestionPost = styled.div`
  vertical-align: top;
  grid-column: 2;
  width: auto;
  min-width: 0;
  .domTree {
    width: 100%;
    line-height: 1.5;
    overflow-wrap: break-word;

    pre {
      padding: 12px;
      margin-bottom: 18px;
      border-radius: var(--border-radius-md);
      background: #f6f6f6;
      overflow-x: scroll;
    }
    p {
      margin-bottom: 16px;
    }
  }
  .tagBox {
    margin-bottom: 12px;
    margin-top: 24px;
  }
  .tagOutBox {
    margin-bottom: 10px;
    clear: both;
    display: flex;
    flex-direction: column;
    margin-top: -2px;
  }
  .tagInBox {
    margin: 2px 0;
    position: relative;
    display: flex;
    flex-wrap: wrap;
  }
  .tagList {
    display: inline;
    list-style: none;
    margin-bottom: 13px;
  }
  .tagList li {
    display: inline;
    margin-right: 4px;
  }
  .tagList li a {
    display: inline-block;
    padding: 4.8px 6px;
    margin: 2px 2px 2px 0;
    line-height: 1;
    white-space: nowrap;
    text-align: center;
    cursor: pointer;
    border: 1px solid rgb(0 0 0 / 0%);
    border-radius: 3px;
    font-size: 12px;
    color: rgb(57 115 157);
    background-color: rgb(225 236 244);
  }
  .profileBox {
    display: flex;
    padding-top: 4px;
    margin-bottom: 16px;
    margin-top: 16px;
  }
  .editBox {
    margin: 4px 16px 4px 0;
    width: 96px;
    flex: 1 auto;
  }
  .editInBox {
    padding-top: 2px;
  }
  .editList {
    display: flex;
    flex-wrap: wrap;
    margin: -4px;
  }
  .edit {
    margin: 4px;
    cursor: pointer;
    color: rgb(106 115 124);
    font-size: 13px;
  }
`;
const UserProfile = styled.div`
  text-align: left;
  vertical-align: top;
  width: 200px;
  margin: 4px 0px;
  border-radius: var(--border-radius-sm);
  color: var(--caption-color);

  &.owner {
    background-color: #d9eaf7;
  }

  .userInBox {
    padding: 5px 6px 7px 7px;
    color: rgb(106 115 124);
  }
  .userInBox:before,
  .userInBox:after {
    content: "";
    display: table;
  }
  .userInBox:after {
    clear: both;
  }
  .date {
    margin-top: 1px;
    margin-bottom: 4px;
    font-size: 12px;
  }
  .userImg {
    float: left;
    width: 32px;
    height: 32px;
    border-radius: 1px;
  }
  .userImg img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
  .userData {
    float: left;
    margin-left: 8px;
    width: calc(100% - 40px);
    line-height: 17px;
    word-wrap: break-word;
  }
  .userData a {
    color: rgb(0 116 204);
    cursor: pointer;
    font-size: 13px;
  }
  .score {
    font-weight: bold;
    font-size: 12px;
    margin-right: 2px;
  }
`;
const QuestionComment = styled.div`
  grid-column: 2;
  width: auto;
  min-width: 0;
  .commentBox {
    width: 100%;
    padding-bottom: 10px;
    margin-top: 12px;
    border-top: 1px solid rgb(227 230 232);
  }
  ul li {
    display: contents;
    padding-left: 18px;
  }
  .comment {
    min-width: 0;
    padding: 6px;
    border-bottom: 1px solid var(--lightgray2);
    font-size: 13px;
    line-height: 1.4;
    vertical-align: text-top;
  }
  .commentUser {
    display: inline-flex;
    margin: 0 3px;
    align-items: center;
    color: var(--darkblue);
  }
  .date {
    color: var(--darkgray2);
  }
  .writeBox {
    display: flex;
    width: 100%;
    gap: 10px;
  }
  textarea {
    width: 100%;
    min-height: 80px;
    resize: vertical;
  }
  .commentWrite {
    margin: 20px 0px;
  }
  .commentWrite p {
    cursor: pointer;
    font-size: 13px;
    color: #838c95;
    padding: 0px 3px 2px 3px;
    opacity: 0.6;
    margin-bottom: 6px;
  }
  .commentWrite p:hover {
    color: var(--blue);
  }
  .btnPrimary {
    font-size: 13px;
    background-color: #333333;
    padding: 10px;
    width: 120px;
  }

  @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
    .writeBox {
      flex-direction: column;
    }
  }
`;

const QuestionBlock = ({ type, data, comment, quId }) => {
  const [answerComment, setAnswerComment] = useState();
//   const [answerID, setAnswerID] = useState();

  useEffect(() => {
    const comment2 = userAnComment(data && data.answersId);
    comment2.then((res) => {
      setAnswerComment(res);
    });
    // setAnswerID(data && data.answersId);
  }, []);
  const commentInput = useRef();
  const [showOption, setShowOption] = useState(false);
  const handleToggleOption = () => setShowOption((prev) => !prev);
  const handleClickOutSide = (e) => {
    if (showOption && !commentInput.current.contains(e.target)) {
      setShowOption(false);
    }
  };

  useEffect(() => {
    if (showOption) document.addEventListener("mousedown", handleClickOutSide);
    return () => {
      document.removeEventListener("mousedown", handleClickOutSide);
    };
  });
  const [commentText, setCommentText] = useState("");
  const commentPost = () => {
    if (type === "question") {
      console.log("질문에 댓글");
      try {
        var data = JSON.stringify({
          commentBody: commentText,
        });
        const token = window.localStorage.getItem("loginToken");

        var config = {
          method: "post",
          url: `http://prepro31.iptime.org:8080/questions/${quId}/comments`,
          headers: {
            Authorization: token,
            "Content-Type": "application/json",
          },
          data: data,
        };

        axios(config)
          .then(function (response) {
            window.location.replace(`/questions/${quId}`);
            console.log(JSON.stringify(response.data));
          })
          .catch(function (error) {
            console.log(error);
          });
      } catch (error) {
        console.error(error.response.data);
      }
    } else {
      console.log("답글에 댓글");
      try {
        var data = JSON.stringify({
          commentBody: commentText,
        });
        const token = window.localStorage.getItem("loginToken");

        // const config = {
        //   method: "post",
        //   url: `http://prepro31.iptime.org:8080/questions/${answerID}/comments`,
        //   headers: {
        //     Authorization: token,
        //     "Content-Type": "application/json",
        //   },
        //   data: data,
        // };
        axios(config)
          .then(function (res) {
            window.location.replace(`/questions/${quId}`);
            console.log("성공");
          })
          .catch(function (error) {
            console.log(error);
          });
      } catch (error) {
        console.error(error.response.data);
      }
    }
  };
  return (
    <QuestionBox>
      <div className="layout">
        <QuestionVote>
          <div className="voteBox">
            <button>
              <TopBtn color={"rgb(201, 203, 207)"} />
            </button>
            <div className="voteCount">
              {type === "question"
                ? data && data.questionLikes
                : data && data.answersLikes}
            </div>
            <button>
              <BottomBtn color={"rgb(201, 203, 207)"} />
            </button>
          </div>
        </QuestionVote>
        <QuestionPost>
          <div
            className="domTree"
            dangerouslySetInnerHTML={{
              __html:
                type === "question"
                  ? data && data.questionBody
                  : data && data.answersBody,
            }}
          ></div>
          <div className="tagBox">
            <div className="tagOutBox">
              <div className="tagInBox">
                <ul className="tagList"></ul>
              </div>
            </div>
          </div>
          <div className="profileBox">
            <div className="editBox">
              <div className="editInBox">
                <div className="editList">
                  <div className="edit">Edit</div>
                </div>
              </div>
            </div>
            {/* 수정 기능 넣을 시 수정 */}
            {/* <UserProfile>
							<div className="userInBox">
								<div className="date">edited {data && data.questionLastDate}</div>
								<div className="userImg">
									<img src="https://i.stack.imgur.com/yZM8h.png?s=64&g=1" />
								</div>
								<div className="userData">
									<Link to="/users/profile/12">Zac Anger2</Link>
									<div className="score">4,999</div>
								</div>
							</div>
						</UserProfile> */}
            <UserProfile className="owner">
              <div className="userInBox">
                <div className="date">
                  asked{" "}
                  {type === "question"
                    ? data && data.questionRegDate
                    : data && data.answersRegDate}
                </div>
                <div className="userImg">
                  <img src="https://i.stack.imgur.com/yZM8h.png?s=64&g=1" />
                </div>
                <div className="userData">
                  <Link to={`/users/profile/${data && data.user[0].memberId}`}>
                    {data && data.user[0].memberName}
                  </Link>
                  <div className="score">{data && data.userprofile.point}</div>
                </div>
              </div>
            </UserProfile>
          </div>
        </QuestionPost>
        <QuestionComment>
          <div className="commentBox">
            <ul>
              {type === "question"
                ? comment &&
                  comment.map((data, index) => {
                    return (
                      <li key={index}>
                        <div className="comment">
                          <span>{data && data.body}</span>
                          <Link to={`/users/profile/${data && data.userId}`}>
                            <div className="commentUser">
                              -{data && data.name}
                            </div>
                          </Link>
                          <span className="date">{data && data.date}</span>
                        </div>
                      </li>
                    );
                  })
                : answerComment &&
                  answerComment.map((data, index) => {
                    return (
                      <li key={index}>
                        <div className="comment">
                          <span>{data && data.body}</span>
                          <Link to={`/users/profile/${data && data.userId}`}>
                            <div className="commentUser">
                              -{data && data.name}
                            </div>
                          </Link>
                          <span className="date">{data && data.date}</span>
                        </div>
                      </li>
                    );
                  })}
            </ul>
          </div>
          <div className="commentWrite" ref={commentInput}>
            <p onClick={handleToggleOption}>Add a comment</p>
            {showOption ? (
              <div className="writeBox">
                <textarea
                  value={commentText}
                  onChange={(e) => setCommentText(e.target.value)}
                ></textarea>
                <button className="btnPrimary" onClick={commentPost}>
                  Add Comment
                </button>
              </div>
            ) : null}
          </div>
        </QuestionComment>
      </div>
    </QuestionBox>
  );
};

export default QuestionBlock;
