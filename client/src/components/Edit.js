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
        
    }
`;

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
                            <textarea id="about" cols="92" rows="15" tabindex="4" placeholder></textarea>
                        </div>
                    </div>
                </div>
                <div className="subTitle">GitHub Link</div>
                <div className="subBox"></div>
            </MainBody>
        </>
    );
};

export default Edit;
