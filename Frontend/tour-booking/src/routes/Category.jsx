import "../App.css";
import React from "react";
import Footer from "../Components/Footer.jsx";
import BodyCategoria from "../Components/BodyCategoria.jsx";
//sumar la logica del fetch por id categoria y renderizar las cards por categoria

function Category() {
  return (
    <div className="Home">
      <BodyCategoria />
      <Footer />
    </div>
  );
}

export default Category;
