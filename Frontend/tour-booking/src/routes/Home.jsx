import "../App.css";
import React, { useState, useEffect } from "react";
import Footer from "../Components/Footer.jsx";
import Body from "../Components/Body.jsx";

function Home() {
  return (
    <div className="Home">
      <Body />
      <Footer />
    </div>
  );
}

export default Home;
