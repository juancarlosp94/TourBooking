import React, { useState } from "react";
import "./AdminPanel.css";
import FileUploadButton from "./FileUploadButton";

const AddProductButton = ({ handleAdd }) => {
  const [product, setProduct] = useState({
    name: "",
    title: "",
    description: "",
    image: "",
    imageFile: null
  });

  const handleFileChange = (e) => {
    setProduct({
      ...product,
      imageFile: e.target.files[0],
    });
  };

  const handleChange = (e) => {
    setProduct({
      ...product,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    handleAdd(product);
    setProduct({
      name: "",
      title: "",
      description: "",
      image: "",
      imageFile: null
    });
  };

  return (
    <form onSubmit={handleSubmit} className="add-product-form">
      <label>
        Nombre:
        <input
          type="text"
          name="name"
          value={product.name}
          onChange={handleChange}
          className="add-product-input"
        />
      </label>

      <label>
        Titulo:
        <input
          type="text"
          name="title"
          value={product.title}
          onChange={handleChange}
          className="add-product-input"
        />
      </label>

      <label>
        Descripción:
        <textarea
          name="description"
          value={product.description}
          onChange={handleChange}
          className="add-product-textarea"
        />
      </label>
      <label>
        Imagen (URL):
        <input
          type="text"
          name="image"
          value={product.image}
          onChange={handleChange}
          className="add-product-input"
          placeholder="Ingrese URL"
        />
      </label>
      <div className="fileImage">
        <label>Ó adjunte los archivos aquí:</label>
        <FileUploadButton handleFileChange={handleFileChange} />
      </div>
      <button type="submit" className="add-product-button">
        Guardar producto
      </button>
    </form>
  );
};

export default AddProductButton;
