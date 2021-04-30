import React, { useState } from "react";
import styled from "styled-components";
import {
  BeforeRight,
  AfterRight,
  BeforeLeft,
  AfterLeft,
} from "../../Svg/Button";

const CarouselButton = ({ PopUp, Name, Slide, count }) => {
  const [Right, setRight] = useState(BeforeRight);
  const [Left, setLeft] = useState(BeforeLeft);

  const MouseEnter = (e) => {
    e.target.classList.contains("Left")
      ? setLeft(AfterLeft)
      : setRight(AfterRight);
  };

  const MouseLeave = (e) => {
    e.target.classList.contains("Left")
      ? setLeft(BeforeLeft)
      : setRight(BeforeRight);
  };

  return (
    <Button
      count={count}
      PopUp={PopUp}
      className={Name}
      onMouseEnter={MouseEnter}
      onMouseLeave={MouseLeave}
      onClick={Slide}
    >
      {Name === "Left" ? Left : Right}
    </Button>
  );
};

const Button = styled.button`
  pointer-events: ${({ count, className }) =>
    count === 0 && className === "Left"
      ? `none`
      : count === 1 && className === "Right"
      ? `none`
      : null};
  margin: ${({ PopUp }) => (PopUp ? `0px` : `170px 0 0 0`)};
  z-index: ${({ PopUp }) => (PopUp ? `3` : `1`)};
  background-color: transparent;
  border: none;
  outline: none;
  height: 50px;
  &:active {
    transform: translateY(2px);
  }
`;
export default CarouselButton;
