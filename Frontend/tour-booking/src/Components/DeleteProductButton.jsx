import React from "react";

const DeleteProductButton = ({ productId, handleDelete }) => {
  
  const handleClick = () => {
    handleDelete(productId);
  };

  return (
    <button onClick={handleClick}>Eliminar producto</button>
  );
};

export default DeleteProductButton;
