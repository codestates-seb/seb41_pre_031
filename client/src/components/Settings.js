import React, { useState } from "react";
import styled from "styled-components";
import { Link, Route, Routes } from "react-router-dom";
import Delete from "./Delete";
import Edit from "./Edit";

const Setting = styled.div`
  display: flex;
  margin-bottom: 48px;
`;
const SettingNav = styled.ul`
  margin-right: 32px;
  li a {
    display: block;
    text-decoration: none;
    color: inherit;
  }
  .tabTitle {
    padding: 6px 12px;
    font-size: 11px;
    color: var(--dark);
    font-weight: bold;
  }
  .tabBtn {
    width: 100%;
    padding: 6px 12px;
    border: none;
    border-radius: 20px;
    background-color: var(--white);
    color: var(--nav-link-color);
  }
  .tabChecked {
    color: var(--white);
    background-color: var(--orange);
  }
  .tabBtn:hover {
    background-color: #e3e6e8;
  }
  .tabBtn.tabChecked:hover {
    background-color: var(--orange);
  }
`;
const SettingMain = styled.div``;

const Settings = ({id}) => {
  const [openTab, setOpenTab] = useState([
    {
      id: 0,
      name: "Edit profile",
      url: "profile/edit/"+id,
    },
    { id: 1, name: "Delete profile", url: "profile/delete/"+id },
  ]);
  const [checked, setChecked] = useState(0);
  function tabHandle(index) {
    setChecked(index);
  }

  return (
    <Setting>
      <SettingNav className="tabBox">
        <li className="tabTitle">PERSONAL INFORMATION</li>
        {openTab.map((data, index) => {
          return (
            <li
              key={index}
              className={checked === index ? "tabBtn tabChecked" : "tabBtn"}
              onClick={() => {
                tabHandle(index);
              }}
            >
              {/* <Link to={`/users/${data.url}`}>{data.name}</Link> */}
              {data.name}
            </li>
          );
        })}
      </SettingNav>
      <SettingMain>
        {/* <Routes>
          <Route path="/users/profile/edit/:id/*" element={<Edit />} />
          <Route path="/users/profile/delete/:id/*" element={<Delete />} />
        </Routes> */}
        {
            checked === 0 ? <Edit/> : <Delete/>
        }
      </SettingMain>
    </Setting>
  );
};

export default Settings;
