import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import BottomBtn from "../icons/BottomBtn";
import TopBtn from "../icons/TopBtn";

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
  padding-right: 16px;
  grid-column: 2;
  width: auto;
  min-width: 0;
  .domTree {
    width: 100%;
    line-height: 1.5;
    overflow-wrap: break-word;
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
    max-width: 620px;
    margin-bottom: 16px;
    margin-top: 16px;
    align-items: flex-start;
    justify-content: flex-end;
    flex-wrap: wrap;
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
  .owner{
    background-color: #D9EAF7;
  }
`;
const UserProfile = styled.div`
  text-align: left;
  vertical-align: top;
  width: 200px;
  margin: 4px 0px;
  .userInBox {
    padding: 5px 6px 7px 7px;
    color: rgb(106 115 124);
  }
  .date {
    margin-top: 1px;
    margin-bottom: 4px;
    font-size: 12px;
    color: rgb(0 116 204);
  }
  .userImg {
    float: left;
    width: 32px;
    height: 32px;
    border-radius: 1px;
  }
  .userImg img {
    width: 32px;
    height: 32px;
  }
  .userData {
    margin-left: 8px;
    width: calc(100% - 40px);
    float: left;
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
  padding-right: 16px;
  grid-column: 2;
  width: auto;
  min-width: 0;
  .commentBox{
    width: 100%;
    padding-bottom: 10px;
    margin-top: 12px;
    border-top: 1px solid rgb(227 230 232);
  }
  ul li{
    display: contents;
    padding-left: 18px;
  }
  .comment{
    min-width: 0;
    padding: 6px;
    font-size: 13px;
    line-height: 1.4;
    vertical-align: text-top;
  }
  .commentUser{
    display: inline-flex;
    margin: 0 3px;
    align-items: center;
    color: var(--darkblue);
  }
  .date{
    color: var(--darkgray2);
  }
`;

const QuestionBlock = () => {
    const 덩어리 = `<pre class="ql-syntax" spellcheck="false"><span class="hljs-keyword">import</span> <span class="hljs-title class_">React</span> <span class="hljs-keyword">from</span> <span class="hljs-string">'react'</span>;
    <span class="hljs-keyword">import</span> <span class="hljs-title class_">ReactDOM</span> <span class="hljs-keyword">from</span> <span class="hljs-string">'react-dom/client'</span>;
    <span class="hljs-keyword">import</span> <span class="hljs-title class_">App</span> <span class="hljs-keyword">from</span> <span class="hljs-string">'./App'</span>;
    
    <span class="hljs-keyword">const</span> root = <span class="hljs-title class_">ReactDOM</span>.<span class="hljs-title function_">createRoot</span>(<span class="hljs-variable language_">document</span>.<span class="hljs-title function_">getElementById</span>(<span class="hljs-string">'root'</span>));
    root.<span class="hljs-title function_">render</span>(
    &nbsp; <span class="hljs-tag">&lt;<span class="hljs-name">React.StrictMode</span>&gt;</span>
    &nbsp; &nbsp; <span class="hljs-tag">&lt;<span class="hljs-name">App</span> /&gt;</span>
    &nbsp; <span class="hljs-tag">&lt;/<span class="hljs-name">React.StrictMode</span>&gt;</span>
    );
    </pre><p>ㅎㅇ</p><p><strong>ㅂ2ㅂ2</strong></p>`
  return (
    <QuestionBox>
      <div className="layout">
        <QuestionVote>
          <div className="voteBox">
            <button>
              <TopBtn color={"rgb(201, 203, 207)"} />
            </button>
            <div className="voteCount">-3</div>
            <button>
              <BottomBtn color={"rgb(201, 203, 207)"} />
            </button>
          </div>
        </QuestionVote>
        <QuestionPost>
          <div className="domTree" dangerouslySetInnerHTML={{ __html: 덩어리 }}></div>
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
            <UserProfile>
              <div className="userInBox">
                <div className="date">edited 1 hour ago</div>
                <div className="userImg">
                  <img src="https://i.stack.imgur.com/yZM8h.png?s=64&g=1" />
                </div>
                <div className="userData">
                  <Link to="/users/profile/12">Zac Anger</Link>
                  <div className="score">4,999</div>
                </div>
              </div>
            </UserProfile>
            <UserProfile className="owner">
              <div className="userInBox">
                <div className="date">edited 1 hour ago</div>
                <div className="userImg">
                  <img src="https://i.stack.imgur.com/yZM8h.png?s=64&g=1" />
                </div>
                <div className="userData">
                  <Link to="/users/profile/12">Zac Anger</Link>
                  <div className="score">4,999</div>
                </div>
              </div>
            </UserProfile>
          </div>
        </QuestionPost>
        <QuestionComment>
            <div className="commentBox">
                <ul>
                    <li>
                        <div className="comment">
                            <span>Are you sure you want to reset cnt to zero every time you click the button…?</span>
                            <div className="commentUser">-deceze</div>
                            <span className="date">1 hour ago</span>
                        </div>
                    </li>
                    <li>
                        <div className="comment">
                            <span>Are you sure you want to reset cnt to zero every time you click the button…?</span>
                            <div className="commentUser">-deceze</div>
                            <span className="date">1 hour ago</span>
                        </div>
                    </li>
                </ul>
            </div>
        </QuestionComment>
      </div>
    </QuestionBox>
  );
};

export default QuestionBlock;
