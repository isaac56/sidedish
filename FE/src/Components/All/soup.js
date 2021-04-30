import React, { useState, useEffect, useRef } from "react";
import Carousel from "../Carousel/Carousel";
import CarouselButton from "../Carousel/CarouselButton";
import styled from "styled-components";
import axios from "axios";
import PopUpModal from "../PopUpModal/PopUpModal";

const Soup = () => {
  const [Soup, setSoup] = useState([]);
  const [ModalData, setModalData] = useState([]);
  const [modal, setModal] = useState(false);
  const soupImageRef = useRef();
  const soupRef = useRef();

  useEffect(() => {
    const fetchData = async () => {
      const soupData = await axios
        .get("/products/soup")
        .then((res) => res.data.data.items);
      setSoup(soupData);
    };
    fetchData();
  }, []); // eslint-disable-line

  const soupSlide = (e) => {
    e.target.classList.contains("Left")
      ? soupRef.current.Slider(e, 1)
      : soupRef.current.Slider(e, -1);
  };

  const PopUpCarousel = () => {
    for (const i of Soup) {
      if (i.detailHash === ModalData[0]) {
        return (
          <PopUpModal
            MainTitle={"정성이 담긴 뜨끈한 국물요리"}
            Food={Soup}
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
        <CarouselButton Name={"Left"} Slide={soupSlide} />
        <Carousel
          MainTitle={"정성이 담긴 뜨끈한 국물요리"}
          Food={Soup}
          setFood={setSoup}
          Ref={soupImageRef}
          setModal={setModal}
          ref={soupRef}
          setModalData={setModalData}
        />
        <CarouselButton Name={"Right"} Slide={soupSlide} />
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

export default Soup;
