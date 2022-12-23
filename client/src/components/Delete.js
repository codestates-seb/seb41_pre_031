import React, { useState } from "react";
import styled from "styled-components";

const MainHeader = styled.div`
    display: flex;
    width: 100%;
    padding-bottom: 16px;
    margin-bottom: 24px;
    align-items: flex-end;
    flex-direction: row;
    border-bottom: 1px solid rgb(214 217 220);
    justify-content: space-between;
    h1 {
        border-bottom: 1px solid rgb(241 242 243);
        color: var(--black);
        font-size: 27px;
    }
`;

const MainBody = styled.div`
    font-size: 15px;
    line-height: 1.5;
    overflow-wrap: break-word;
    p {
        margin-bottom: 16.5px;
    }
    ul {
        list-style-type: disc;
        margin-left: 30px;
    }
    li {
        margin-bottom: 8.25px;
    }
    .checkBox {
        margin-bottom: 24px;
    }
    .inBox {
        display: flex;
        margin: -4px;
        overflow-wrap: break-word;
        cursor: pointer;
    }
    .inputBox {
        margin: 4px;
    }
    label {
        color: red;
        font-size: 13px;
        line-height: 2.2;
    }
    .prDeleteBtn{
        color: var(--white);
        background-color: var(--red);
        font-size: 13px;
        padding: 10.4px;
        border: 1px solid transparent;
        cursor: pointer;
    }
    .prDeleteBtn:hover{
        background-color:#C22E32;
    }
    .prDeleteBtn:disabled{
        opacity: 0.5;
        pointer-events: none;
        text-decoration: none;
    }
`;

const Delete = () => {
    const [ischecked, setIsChecked] = useState(false);

    const changeCheck = (e) => {
        if(e.target.checked){
            setIsChecked(true);
        } else {
            setIsChecked(false);
        }
    }
    return (
        <>
            <MainHeader>
                <h1>Delete Profile</h1>
            </MainHeader>
            <MainBody>
                <p>
                    Before confirming that you would like your profile deleted,
                    we'd like to take a moment to explain the implications of
                    deletion:
                </p>
                <ul>
                    <li>
                        Deletion is irreversible, and you will have no way to
                        regain any of your original content, should this
                        deletion be carried out and you change your mind later
                        on.
                    </li>
                    <li>
                        Your questions and answers will remain on the site, but
                        will be disassociated and anonymized (the author will be
                        listed as "user20811931") and will not indicate your
                        authorship even if you later return to the site.
                    </li>
                </ul>
                <p>
                    Confirming deletion will only delete your profile on Stack
                    Overflow - it will not affect any of your other profiles on
                    the Stack Exchange network. If you want to delete multiple
                    profiles, you'll need to visit each site separately and
                    request deletion of those individual profiles.
                </p>
                <div className="checkBox">
                    <div className="inBox">
                        <div className="inputBox">
                            <input type="checkbox" id="deleteBtn" onChange={e => changeCheck(e)} checked={ischecked}/>
                        </div>
                        <div>
                            <label htmlFor="deleteBtn">
                                위에 언급된 정보를 읽었으며 내 프로필을 삭제하는
                                것의 의미를 이해합니다. 내 프로필 삭제를
                                진행하고 싶다.
                            </label>
                        </div>
                    </div>
                </div>
                <button disabled={!ischecked} onClick={()=> console.log('클릭')} className="prDeleteBtn">Delete profile</button>
            </MainBody>
        </>
    );
};

export default Delete;
