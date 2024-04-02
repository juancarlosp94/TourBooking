import React from "react";
import AddProductButton from "./AddProductButton";

const AddProductPage = ({ handleAdd }) => {
  return (
    <div className="add-product-page">
      <AddProductButton handleAdd={handleAdd} />
    </div>
  );
};

export default AddProductPage;
