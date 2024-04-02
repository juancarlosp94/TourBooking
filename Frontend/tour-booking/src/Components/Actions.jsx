import React, { useState } from "react";
import { Link } from "react-router-dom";

const Actions = ({ productId, handleDelete }) => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className="actions">
      <button onClick={toggleMenu}>Actions</button>
      {isOpen && (
        <div className="actionsButton">
          <button onClick={() => handleDelete(productId)}>Eliminar</button>
          <Link to="/admin/addproduct">
            <button>Agregar Producto</button>
          </Link>
        </div>
      )}
    </div>
  );
};

export default Actions;
