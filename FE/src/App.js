import React, { useState } from "react";
import Main from "./Components/Main/Main";
import Header from "./Components/Header/Header";
import All from "./Components/All/All";
import Best from "./Components/Best/Best";

const App = () => {
  // const [ModalData, setModalData] = useState([]);
  const [loginModal, setLoginModal] = useState(false);

  return (
    <div>
      <Header loginModal={loginModal} setLoginModal={setLoginModal} />
      <Best />
      <Main />
      <All />
    </div>
  );
};

export default App;
