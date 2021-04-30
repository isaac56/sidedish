import React, { useState, useEffect, useRef } from "react";
import Carousel from "../Carousel/Carousel";
import CarouselButton from "../Carousel/CarouselButton";
import styled from "styled-components";
import axios from "axios";
import PopUpModal from "../PopUpModal/PopUpModal";

const Side = () => {
  const [Side, setSide] = useState([]);
  const [ModalData, setModalData] = useState([]);
  const [modal, setModal] = useState(false);
  const sideImageRef = useRef();
  const sideRef = useRef();

  useEffect(() => {
    const fetchData = async () => {
      const sideData = await axios
        .get("/products/side")
        .then((res) => res.data.data.items);
      setSide(sideData);
    };
    fetchData();
  }, []); // eslint-disable-line

  const sideSlide = (e) => {
    e.target.classList.contains("Left")
      ? sideRef.current.Slider(e, 1)
      : sideRef.current.Slider(e, -1);
  };

  const PopUpCarousel = () => {
    for (const i of Side) {
      if (i.detailHash === ModalData[0]) {
        return (
          <PopUpModal
            MainTitle={"정성이 담긴 뜨끈한 국물요리"}
            Food={Side}
            setModal={setModal}
            ModalData={ModalData}
            setModalData={setModalData}
          />
        );
      }
    }
    return null;
  };

  return (
    <div>
      {modal && PopUpCarousel()}
      <CarouselSlide>
        <CarouselButton Name={"Left"} Slide={sideSlide} />
        <Carousel
          MainTitle={"식탁을 풍성하게 하는 정갈한 밑반찬"}
          Food={Side}
          setFood={setSide}
          Ref={sideImageRef}
          setModal={setModal}
          ref={sideRef}
          setModalData={setModalData}
        />
        <CarouselButton Name={"Right"} Slide={sideSlide} />
      </CarouselSlide>
    </div>
  );
};

const CarouselSlide = styled.div`
  margin-top: 77px;
  padding: 0 35px;
  display: flex;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export default Side;
