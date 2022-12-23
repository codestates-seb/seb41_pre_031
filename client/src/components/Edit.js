import React from "react";
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
const MainBody = styled.form`
    .subTitle {
        font-size: 21px;
        margin-bottom: 8px;
    }
    .subBox {
        margin-bottom: 48px;
        padding: 24px;
        border-radius: 5px;
        border: 1px solid var(--lightgray2);
    }
    .fromBox {
        display: flex;
        flex-direction: column;
        margin: -6px 0;
    }
    .inputBox {
        display: flex;
        align-items: flex-end;
        flex-wrap: wrap;
        margin: -8px;
    }
    .fromItem {
        margin: 4px 0;
        display: flex;
        flex-direction: column;
    }
    label {
        display: block;
    }
    input[type="text"] {
        display: block;
        width: 100%;
        max-width: 421px;
        padding: 7.8px;
        border: 1px solid rgb(186 191 196);
        margin: 8px 0;
    }
    textarea{
        padding: 10px;
        margin: 7px 0 0;
        height: 200px;
        line-height: 1.3;
        width: 100%;
    }
    .linkBox{
        display: flex;
        flex-direction: column;
        margin: -2px 0;
    }
    .linkInputBox{
        display: flex;
        position: relative;
        margin: 2px 0;
    }
    .linkInputBox input{
        padding: 7.8px 9.1px 7.8px 32px;
    }
`;

const BtnBox = styled.div`
    padding: 10px 0 15px 0;
    .btnBox{
        display: flex;
        flex-wrap: wrap;
        margin: -4px;
    }
    button{
        cursor: pointer;
    }
    .link{
        cursor: pointer;
        padding: 10.4px;
        margin: 4px 4px 4px 6px;
    }
    .link:hover{
        color: var(--link-color);
    }
`

const Edit = () => {
    return (
        <>
            <MainHeader>
                <h1>Edit your profile</h1>
            </MainHeader>
            <MainBody>
                <div className="subTitle">Public information</div>
                <div className="subBox">
                    <div className="fromBox">
                        <div className="fromItem">
                            <label htmlFor="name">Display name</label>
                            <input
                                type="text"
                                id="name"
                                maxLength="30"
                                tabIndex="1"
                            />
                        </div>
                        <div className="fromItem">
                            <label htmlFor="location">Location</label>
                            <input
                                type="text"
                                id="location"
                                maxLength="100"
                                tabIndex="3"
                            />
                        </div>
                        <div className="fromItem">
                            <label htmlFor="title">Title</label>
                            <input
                                type="text"
                                id="title"
                                maxLength="225"
                                tabIndex="3"
                                placeholder="No title has been set"
                            />
                        </div>
                        <div className="fromItem">
                            <label htmlFor="about">About me</label>
                            <textarea id="about" cols="92" rows="15" tabIndex="4"></textarea>
                        </div>
                    </div>
                </div>
                <div className="subTitle">GitHub Link</div>
                <div className="subBox">
                    <div className="linkBox">
                        <label>GitHub link</label>
                        <div className="linkInputBox">
                            <input type="text" maxLength="200"/> 
                        </div>
                    </div>
                </div>
            </MainBody>
            <BtnBox>
                <div className="btnBox">
                    <button className="btnPrimary">Save profile</button>
                    <a className="link">Cancel</a>
                </div>
            </BtnBox>
        </>
    );
};

export default Edit;
