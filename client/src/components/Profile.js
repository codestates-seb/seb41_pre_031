import React, { useState } from "react";
import styled from "styled-components";

const ProfileBox = styled.div`
    display: flex;
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
const Profile = ({ id }) => {
    const [userData, setUserData] = useState([
        {
            reputation: "779,635",
            reached: "75.2m",
            answers: "29,756",
            questions: 45,
        },
    ]);
    const questionsList = [
        {
            reputation: 87,
            title: "Aggregation in Pandas",
            date: "Dec 14, 2018",
        },
        {
            reputation: 55,
            title: "List with duplicated values and suffix",
            date: "Jul 15, 2017",
        },
        {
            reputation: 33,
            title: "Python - Flatten the list of dictionaries",
            date: "Feb 9, 2018",
        },
        {
            reputation: 22,
            title: "Nested list to dict",
            date: "Apr 5, 2017",
        },
        {
            reputation: 18,
            title: "Extract text with multiple separators",
            date: "Feb 8, 2017",
        },
        {
            reputation: 13,
            title: "Python: Merge list with range list",
            date: "Feb 19, 2016",
        },
        {
            reputation: 13,
            title: "After rename column get keyerror",
            date: "Apr 8, 2017",
        },
        {
            reputation: 12,
            title: "Convert dict of nested lists to list of tuples",
            date: "Jul 21, 2017",
        },
        {
            reputation: 10,
            title: "Max value per diagonal in 2d array",
            date: "Dec 4, 2019",
        },
        {
            reputation: 8,
            title: "Top N values in 2d array with duplicates to mask",
            date: "Apr 30, 2020",
        },
    ]
    return (
        <ProfileBox>
            <ProfileNav>
                <div className="navTitle">Stats</div>
                <div className="statsBox">
                    <div className="statsInBox">
                        {Object.keys(userData[0]).map((data,index) => {
                            return (
                                <div key={index} className="stats">
                                    <div className="statsData">
                                        {userData[0][data]}
                                    </div>
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
                        <div className="aboutData"></div>
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
                                    <div className="filterBtn btnStart">
                                        All
                                    </div>
                                    <div className="filterBtn">Questions</div>
                                    <div className="filterBtn">Answers</div>
                                    <div className="filterBtn btnEnd">
                                        Articles
                                    </div>
                                </div>
                                <div className="filterBtmBox ml8">
                                    <div className="filterBtn btnStart">
                                        Score
                                    </div>
                                    <div className="filterBtn btnEnd">
                                        Newest
                                    </div>
                                </div>
                            </div>
                            <div className="listBox">
                                {questionsList.map(
                                    (data, index) => {
                                        return (
                                            <List key={index}>
                                                <div className="listIn">
                                                    <div className="icon"></div>
                                                    <div className="point">
                                                        {data.reputation}
                                                    </div>
                                                    <div className="listTitle">
                                                        {data.title}
                                                    </div>
                                                    <div className="date">
                                                        {data.date}
                                                    </div>
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
