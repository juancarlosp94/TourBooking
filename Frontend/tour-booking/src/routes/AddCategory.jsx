import { useState, useRef } from "react";
import Footer from "../Components/Footer";
import AdminSidebar from "../Components/AdminSidebar";
import axios from "axios";
import style from "../style/AddCategory.css";
import { useUserContext } from "../Context/UserContext";

const AddCategory = () => {
  const [errors, setErrors] = useState({});
  const fileInput = useRef();
  const [selectedFileName, setSelectedFileName] = useState([]);
  const { path } = useUserContext();

  const [categoryData, setCategoryData] = useState({
    name: "",
    description: "",
    image: [],
  });

  const handleClick = () => {
    fileInput.current.click();
  };

  const handleFilesChange = (event) => {
    const files = Array.from(event.target.files);
    setSelectedFileName(files.map((file) => file.name));

    setCategoryData((prevState) => ({ ...prevState, image: files }));
  };

  // validacion de formulario
  const validateForm = () => {
    let isValid = true;
    const validationErrors = {};

    if (!categoryData.name === "") {
      validationErrors.name = "El nombre es requerido.";
      isValid = false;
    }
    if (categoryData.description.length < 10) {
      validationErrors.description = "La descripción es demasiado corta.";
      isValid = false;
    }
    if (categoryData.image === 0) {
      validationErrors.image = "Ingrese una imagen.";
      isValid = false;
    }
    setErrors(validationErrors);
    return isValid;
  };
  // se agrega para la validacion de casos
  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!validateForm()) {
      return; // Si el formulario no es válido, detener la ejecución aquí
    }

    const formData = new FormData();
    const newCategory = {
      name: categoryData.name,
      description: categoryData.description,
    };

    formData.append("category", JSON.stringify(newCategory));
    // formData.append("urlCategoryImage", categoryData.image);
    categoryData.image.forEach((image, index) => {
      if (image) {
        formData.append("urlCategoryImage", image);
      }
    });

    try {
      await axios.post(`http://${path}/category`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      alert("La categoria se ha cargado correctamente!");
      setCategoryData({
        name: "",
        description: "",
        image: "",
      });
      setSelectedFileName([]);
    } catch (error) {
      console.error("Error en el formulario de registro:", error);
      alert("Verifique los datos ingresados.");
    }
  };

  return (
    <div>
      <div className="admin-format">
        <div className="admin-sidebar-position">
          <AdminSidebar />
        </div>
        <div className="addCategory">
          <h2 className="add-category-tittle">Agrega una nueva categoria</h2>

          <form className="form-category-new">
            <label>Nombre de la categoria:</label>
            <input
              type="text"
              name="name"
              placeholder="Escribe un nombre"
              value={categoryData.name}
              onChange={(e) =>
                setCategoryData({ ...categoryData, name: e.target.value })
              }
              style={errors.name ? { border: "2px solid #F28705" } : {}}
            />
            {errors.name && <p style={{ color: "red" }}>{errors.name}</p>}

            <label>Agrega una descripcion:</label>
            <input
              type="text"
              name="categoryDescription"
              placeholder="Agrega una descripcion"
              value={categoryData.description}
              onChange={(e) =>
                setCategoryData({
                  ...categoryData,
                  description: e.target.value,
                })
              }
              required
              style={errors.description ? { border: "2px solid #F28705" } : {}}
            />
            {errors.description && (
              <p style={{ color: "red" }}>{errors.description}</p>
            )}

            <div className="fileImage">
              <label className="imagineadding">
                Agrege una imagen a categoria
                <button type="button" onClick={handleClick}>
                  Browse
                </button>
                <input
                  type="file"
                  accept="image/*"
                  className="file"
                  onChange={handleFilesChange}
                  ref={fileInput}
                  style={{ display: "none" }}
                />
                {selectedFileName.map((name, idx) => (
                  <div key={idx}>{name}</div>
                ))}
                {errors.image && <p style={{ color: "red" }}>{errors.image}</p>}
              </label>
            </div>

            <button
              className="registro-categoria-button"
              type="submit"
              onClick={handleSubmit}
            >
              Guardar
            </button>
          </form>
        </div>
      </div>

      <Footer />
    </div>
  );
};

export default AddCategory;
