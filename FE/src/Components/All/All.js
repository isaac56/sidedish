import React, { useState } from "react";
import styled from "styled-components";
import Side from "./side";
import Soup from "./soup";

const All = () => {
  const [rander, setRander] = useState(false);

  const randerImage = () => {
    rander ? setRander(false) : setRander(true);
  };

  return (
    <AllBox>
      {rander && (
        <div>
          <Soup />
          <Side />
        </div>
      )}
      <PlusButton onClick={randerImage}>
        {rander ? "끄기" : "모든 카테고리 보기"}
      </PlusButton>
    </AllBox>
  );
};

const AllBox = styled.div``;

const PlusButton = styled.button`
  margin-top: 120px;
  width: 1440px;
  height: 100px;
  background-color: #f5f5f7;

  font-style: normal;
  font-weight: bold;
  font-size: 18px;
  line-height: 26px;
  &:active {
    transform: translateY(2px);
  }
`;

export default All;
