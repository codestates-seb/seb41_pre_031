import axios from "axios";
import React, { useState } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import { BREAK_POINT_TABLET } from "./../data/breakpoints";

const ProfileBox = styled.div`
  display: flex;
  @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
    flex-wrap: wrap;
  }
`;

const ProfileNav = styled.div`
  flex-basis: calc(25% - 24px);
  margin: 12px;
  flex-shrink: 0;
  .navTitle {
    font-size: 21px;
    margin-bottom: 8px;
  }
  .statsBox {
    color: var(--gray);
    border: 1px solid rgb(159 166 173);
    padding: 12px;
    border-radius: 4px;
  }
  .statsInBox {
    display: flex;
    flex-wrap: wrap;
    margin: -8px;
  }
  .stats {
    margin: 8px;
    flex-basis: calc(50% - 16px);
  }
  .statsData {
    font-size: 17px;
    color: var(--black);
    margin-bottom: 5px;
  }
  @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
    flex-basis: 100%;
    margin: 0 0 24px 0;
    .stats {
      flex: 1 1 auto;
    }
  }
`;
const ProfileMain = styled.div`
  margin: 12px;
  flex-grow: 1;
  .inBox {
    display: grid;
    gap: 24px 24px;
  }
  .aboutBox {
    overflow-x: auto;
  }
  .title {
    font-size: 21px;
    margin-bottom: 8px;
  }
  .aboutData {
    max-height: 316px;
  }
  .btnBox {
    display: flex;
    justify-content: center;
  }
  .btn {
    font-size: 11px;
    padding: 6.6px;
    border: 1px solid rgb(159 166 173);
    border-radius: 3px;
    color: var(--gray);
  }
  .listHeader {
    display: flex;
    align-items: flex-end;
    justify-content: space-between;
    flex-wrap: wrap;
  }
  .text {
    font-size: 13px;
    color: var(--gray);
  }
  .filterBox {
    display: flex;
    margin-bottom: 8px;
    justify-content: flex-end;
  }
  .filterBtmBox {
    display: flex;
    flex-wrap: wrap;
    margin-bottom: 1px;
  }
  .filterBtn {
    cursor: pointer;
    border: 1px solid rgb(159 166 173);
    padding: 6.6px;
    margin-right: -1px;
    margin-bottom: -1px;
    font-size: 11px;
    color: var(--gray);
  }
  .btnStart {
    border-top-left-radius: 3px;
    border-bottom-left-radius: 3px;
  }
  .btnEnd {
    border-top-right-radius: 3px;
    border-bottom-right-radius: 3px;
  }
  .ml8 {
    margin-left: 8px;
  }
  .listBox {
    border-radius: 5px;
    border: 1px solid rgb(214 217 220);
    width: 100%;
  }
  @media screen and (max-width: ${BREAK_POINT_TABLET}px) {
    flex-basis: 100%;
    margin: 0;
  }
`;
const List = styled.div`
  padding: 12px;
  border-bottom: 1px solid rgb(227 230 232);
  .listIn {
    display: flex;
    align-items: center;
    margin: 0 -6px;
  }
  .icon {
    width: 18px;
    height: 18px;
    margin: 0 6px;
    color: rgb(94 186 125);
  }
  .point {
    min-width: 38px;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    vertical-align: middle;
    border-radius: 3px;
    margin: 0 6px;
    padding: 0 6px;
    background-color: rgb(94 186 125);
    border: 1px solid transparent;
    color: white;
    font-size: 12px;
    line-height: 2;
  }
  .listTitle {
    cursor: pointer;
    margin: 0 6px;
    padding-right: 12px;
    flex-grow: 1;
    font-size: 15px;
    color: var(--darkblue);
  }
  .date {
    margin: 0 6px;
    color: rgb(106 115 124);
    margin-left: auto;
  }
`;
const Profile = ({ userData }) => {
  console.log(userData.myanswers);
  const [userDataList, setUserDataList] = useState([
    {
      reputation: userData.point,
      reached: userData.profileView,
      answers: userData.myanswers.length,
      questions: userData.myquestions.length,
    },
  ]);
  const [title, setTitle] = useState("");
  return (
    <ProfileBox>
      <ProfileNav>
        <div className="navTitle">Stats</div>
        <div className="statsBox">
          <div className="statsInBox">
            {Object.keys(userDataList[0]).map((data, index) => {
              return (
                <div key={index} className="stats">
                  <div className="statsData">{userDataList[0][data]}</div>
                  {data}
                </div>
              );
            })}
          </div>
        </div>
      </ProfileNav>
      <ProfileMain>
        <div className="inBox">
          <div className="aboutBox">
            <div className="title">About</div>
            <div className="aboutData">{userData.about}</div>
            <div className="btnBox">
              <button className="btn">Read more</button>
            </div>
          </div>
          <div>
            <div className="listHeader">
              <div className="title">
                Top questions
                <div className="text">
                  View all questions, answers, and articles
                </div>
              </div>
              <div className="filterBox">
                <div className="filterBtmBox">
                  <div className="filterBtn btnStart">All</div>
                  <div className="filterBtn">Questions</div>
                  <div className="filterBtn btnEnd">Answers</div>
                </div>
                <div className="filterBtmBox ml8">
                  <div className="filterBtn btnStart">Score</div>
                  <div className="filterBtn btnEnd">Newest</div>
                </div>
              </div>
              <div className="listBox">
                {[...userData.myquestions, ...userData.myanswers].map(
                  (data, index) => {
                    const date = new Date(data.RegDate);
                    return (
                      <List key={index}>
                        <div className="listIn">
                          <div className="icon"></div>
                          <div className="point">30</div>
                          <Link to={`/questions/${data.questionId}`}>
                            <div className="listTitle">{data.title}</div>
                          </Link>
                          <div className="date">{date.toDateString()}</div>
                        </div>
                      </List>
                    );
                  }
                )}
              </div>
            </div>
          </div>
        </div>
      </ProfileMain>
    </ProfileBox>
  );
};

export default Profile;
