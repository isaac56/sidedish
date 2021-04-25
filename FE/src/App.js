import React, { useState } from "react";
import styled from "styled-components";
import Main from "./Components/Main/Main";
import Header from "./Components/Header/Header";
import All from "./Components/All/All";
import Best from "./Components/Best/Best";

const App = () => {
  const [modal, setModal] = useState(false);
  const [ModalData, setModalData] = useState([]);

  const URL =
    "https://h3rb9c0ugl.execute-api.ap-northeast-2.amazonaws.com/develop/baminchan/";

  return (
    <div>
      <Header />
      <Best URL={URL} />
      <Main
        URL={URL}
        modal={modal}
        setModal={setModal}
        ModalData={ModalData}
        setModalData={setModalData}
      />
      <All
        URL={URL}
        modal={modal}
        setModal={setModal}
        ModalData={ModalData}
        setModalData={setModalData}
      />
    </div>
  );
};

export default App;
