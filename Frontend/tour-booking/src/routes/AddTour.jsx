import { useEffect, useState, useRef } from "react";
import AdminSidebar from "../Components/AdminSidebar";
import "../style/AddTour.css";
import axios from "axios";
import { useUserContext } from "../Context/UserContext";
import Select from "react-select";

const AddTour = () => {
  const [errors, setErrors] = useState({});
  const [categories, setCategories] = useState([]);
  const fileInput = useRef();
  const [selectedFileNames, setSelectedFileNames] = useState([]);
  const [characteristics, setCharacteristics] = useState([]);
  const { path } = useUserContext();

  const handleChangeMark = (selectedOptions) => {
    const id = characteristics.id;
    const selectedCharacteristicIds = selectedOptions.map((option) => ({
      id: option.value,
    }));
    setTourData((prevData) => ({
      ...prevData,
      characteristics: selectedCharacteristicIds,
    }));
  };

  const [tourData, setTourData] = useState({
    name: "",
    description: "",
    shortDescription: "",
    category: { id: "" },
    characteristics: [],
    price: 0,
    images: [],
  });

  useEffect(() => {
    const fetchCategory = async () => {
      try {
        const response = await axios.get(`http://${path}/category`);
        setCategories(Array.isArray(response.data) ? response.data : []);
        console.log(response.data);
      } catch (error) {
        console.error("Error al cargar la categoria:", error);
      }
    };
    fetchCategory();
  }, []);
  console.log(categories);

  useEffect(() => {
    const fetchCaracteristicas = async () => {
      try {
        const response = await axios.get(`http://${path}/characteristic`);
        setCharacteristics(Array.isArray(response.data) ? response.data : []);
        console.log(response.data);
      } catch (error) {
        console.error("Error al cargar las caracteristicas:", error);
      }
    };
    fetchCaracteristicas();
  }, []);
  console.log(characteristics);

  const validateForm = () => {
    let isValid = true;
    const validationErrors = {};

    if (!tourData.name.trim()) {
      validationErrors.name = "El nombre es requerido.";
      isValid = false;
    }
    if (tourData.description.length < 10) {
      validationErrors.description = "La descripción es demasiado corta.";
      isValid = false;
    }
    if (tourData.shortDescription.length < 5) {
      validationErrors.shortDescription = "La descripcion es muy corta.";
    }
    if (!tourData.category) {
      validationErrors.category = "Seleccione una categoria.";
      isValid = false;
    }
    if (tourData.images.length === 0) {
      validationErrors.images = "Ingrese al menos una imagen.";
      isValid = false;
    }

    if (tourData.price <= 0) {
      validationErrors.price = "Ingrese un precio valido.";
      isValid = false;
    }
    setErrors(validationErrors);
    return isValid;
  };

  const handleClick = () => {
    fileInput.current.click();
  };
  const handleFilesChange = (event) => {
    const files = Array.from(event.target.files);
    setSelectedFileNames(files.map((file) => file.name));

    // Considerando que quieres agregar estas imágenes al estado actual
    setTourData((prevState) => ({ ...prevState, images: files }));
  };
  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!validateForm()) {
      return; // Si el formulario no es válido, detener la ejecución aquí
    }

    const formData = new FormData();

    const tourWithoutImages = {
      name: tourData.name,
      description: tourData.description,
      shortDescription: tourData.shortDescription,
      category: tourData.category,
      characteristics: tourData.characteristics,
      price: tourData.price,
    };

    formData.append("tour", JSON.stringify(tourWithoutImages));

    tourData.images.forEach((image, index) => {
      if (image) {
        formData.append("images", image);
      }
    });
    try {
      await axios.post(`http://${path}/tours`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      alert("El tour se ha cargado correctamente!");
      setTourData({
        name: "",
        description: "",
        shortDescription: "",
        category: { id: "" },
        characteristics: [],
        price: 0,
        images: [],
      });
      setSelectedFileNames([]);
    } catch (error) {
      console.error("Error en el formulario de registro:", error);
      alert("Verifique los datos ingresados.");
    }
  };
  console.log(tourData);

  return (
    <div className="admin-format">
      <div className="admin-sidebar-position">
        <AdminSidebar />
      </div>
      <div className="addTour-panel">
        <h2>Añadí un nuevo tour</h2>
        <form onSubmit={handleSubmit}>
          <label>
            Nombre:
            <input
              type="text"
              name="name"
              value={tourData.name}
              onChange={(e) =>
                setTourData({ ...tourData, name: e.target.value })
              }
              style={errors.name ? { border: "1px solid red" } : {}}
            />
            {errors.name && <p style={{ color: "red" }}>{errors.name}</p>}
          </label>
          <label>
            Descripción:
            <textarea
              name="description"
              value={tourData.description}
              onChange={(e) =>
                setTourData({ ...tourData, description: e.target.value })
              }
              style={errors.description ? { border: "1px solid red" } : {}}
            />
            {errors.description && (
              <p style={{ color: "red" }}>{errors.description}</p>
            )}
          </label>
          <label>
            Descripción Corta:
            <textarea
              name="shortDescription"
              value={tourData.shortDescription}
              onChange={(e) =>
                setTourData({ ...tourData, shortDescription: e.target.value })
              }
              style={errors.shortDescription ? { border: "1px solid red" } : {}}
            />
            {errors.shortDescription && (
              <p style={{ color: "red" }}>{errors.shortDescription}</p>
            )}
          </label>
          <label>
            Categoría:
            <select
              type="text"
              name="categoryId" // <- changed to categoryId
              value={tourData.category.id}
              onChange={(e) => {
                setTourData({ ...tourData, category: { id: e.target.value } });
              }}
              style={errors.category ? { border: "1px solid red" } : {}}
            >
              <option value="">Seleccione una categoría...</option>
              {categories &&
                categories.map((category) => (
                  <option key={category.id} value={category.id}>
                    {category.name}
                  </option>
                ))}
            </select>
            {errors.category && (
              <p style={{ color: "red" }}>{errors.category}</p>
            )}
          </label>

          {/* Se agrega caracteristicas del tour */}

          <div>
            <label>Caracteristicas:</label>
            <div className="caracteristicas">
              <Select
                id={characteristics.id}
                options={characteristics.map((characteristic) => ({
                  value: characteristic.id,
                  label: characteristic.name,
                }))}
                isMulti
                value={tourData.characteristics.map((id) => ({
                  value: id.id,
                  label: characteristics.find(
                    (characteristic) => characteristic.id === id.id
                  ).name,
                }))}
                onChange={handleChangeMark}
              />
            </div>
          </div>

          <label>
            Precio:
            <input
              type="number"
              name="price"
              value={tourData.price}
              onChange={(e) =>
                setTourData({ ...tourData, price: e.target.value })
              }
              style={errors.price ? { border: "1px solid red" } : {}}
            />
            {errors.price && <p style={{ color: "red" }}>{errors.price}</p>}
          </label>

          <h3>Imágenes</h3>
          <div className="fileImage">
            <label>
              O seleccione archivos
              <button type="button" onClick={handleClick}>
                Browse
              </button>
              <input
                type="file"
                accept="image/*"
                multipart
                multiple // Permitir selección múltiple
                className="file"
                onChange={handleFilesChange}
                ref={fileInput}
                style={{ display: "none" }}
              />
              {/* Listar los nombres de los archivos seleccionados */}
              {selectedFileNames.map((name, idx) => (
                <div key={idx}>{name}</div>
              ))}
              {errors.images && <p style={{ color: "red" }}>{errors.images}</p>}
            </label>
          </div>
          <button type="submit">Guardar Destino</button>
        </form>
      </div>
    </div>
  );
};

export default AddTour;
