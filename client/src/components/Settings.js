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

const Settings = () => {
  const [openTab, setOpenTab] = useState([
    {
      id: 0,
      name: "Edit profile",
      url: "edit/12",
    },
    { id: 1, name: "Delete profile", url: "delete/12" },
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
              <Link to={`/users/${data.url}`}>{data.name}</Link>
            </li>
          );
        })}
      </SettingNav>
      <SettingMain>
        <Routes>
          <Route path="/users/edit/:id" element={<Edit />} />
          <Route path="/users/delete/:id" element={<Delete />} />
        </Routes>
      </SettingMain>
    </Setting>
  );
};

export default Settings;
