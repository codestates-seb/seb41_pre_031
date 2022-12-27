import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

const UserBox = styled.div`
  padding: 5px 6px 7px 7px;
  color: rgb(106 115 124);
  .imgBox {
    float: left;
    width: 48px;
    height: 48px;
  }
  .userDetails {
    margin-left: 9px;
    width: calc(100% - 64px);
    line-height: 1.3;
    float: left;
  }
  .userTags {
    clear: both;
    font-size: 12px;
    margin-left: 57px;
  }
  .userName {
    display: inline-block;
    font-size: 15px;
    color: rgb(0 116 204);
    text-decoration: none;
    cursor: pointer;
  }
  .userLocation {
    font-size: 12px;
    margin-bottom: 2px;
    display: block;
    color: rgb(106 115 124);
  }
  .userPoint {
    line-height: 1;
    margin-bottom: 4px;
  }
  .pointNum {
    font-weight: bold;
    font-size: 12px;
    margin-right: 2px;
  }
`;

const User = () => {
  return (
    <UserBox>
      <div className="imgBox">
        <Link to="/users/profile/12">
          <img
            src="https://i.stack.imgur.com/hMDvl.jpg?s=96&g=1"
            width="48"
            height="48"
          />
        </Link>
      </div>
      <div className="userDetails">
        <Link className="userName" to="/users/profile/12">
          jezrael
        </Link>
        <span className="userLocation">Bratislava, Slovakia</span>
        <div className="userPoint">
          <span className="pointNum">1545</span>
        </div>
      </div>
      <div className="userTags"></div>
    </UserBox>
  );
};

export default User;
