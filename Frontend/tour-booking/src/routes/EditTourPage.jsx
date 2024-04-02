import { useEffect, useState, useRef } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import AdminSidebar from "../Components/AdminSidebar";
import { useUserContext } from "../Context/UserContext";
import "../style/AdminSidebar.css";
import "../style/AdminPanel.css";

const EditTourPage = () => {
  const [tour, setTour] = useState({});
  const [selectedFileNames, setSelectedFileNames] = useState([]);
  const { id: tourId } = useParams();
  const [categories, setCategories] = useState([]);
  const { path } = useUserContext();


  const fileInput = useRef();
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
  useEffect(() => {
    const fetchTour = async () => {
      try {
        const response = await axios.get(`http://${path}/tours/${tourId}`);
        setTour(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error al cargar el tour:", error);
      }
    };
    fetchTour();
  }, [tourId]);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setTour((prev) => ({ ...prev, [name]: value }));
  };

  const handleImageDelete = (index) => {
    const updatedImages = [...tour.images];
    updatedImages.splice(index, 1);
    setTour((prev) => ({ ...prev, images: updatedImages }));
  };
  const handleFilesChange = (event) => {
    const files = Array.from(event.target.files);
    setSelectedFileNames(files.map((file) => file.name));

    // Considerando que quieres agregar estas imágenes al estado actual
    setTour((prev) => ({ ...prev, images: [...prev.images, ...files] }));
  };

  const handleClick = () => {
    fileInput.current.click();
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const formData = new FormData();

    // Serializa y añade la información del tour:
    const tourData = { ...tour };
    delete tourData.images; // Remueve la propiedad de imágenes ya que se agregará por separado
    formData.append("tour", JSON.stringify(tourData));

    // Añade las imágenes:
    tour.images.forEach((image, index) => {
      if (image instanceof File) {
        // Si es un archivo
        formData.append("images", image);
      }
    });

    try {
      await axios.patch(`http://${path}/tours/${tourId}`, formData, {});
      alert("Tour actualizado con éxito!");
    } catch (error) {
      console.error("Error al actualizar el tour:", error);
    }
  };
  console.log(tour);

  return (
    <div className="admin-format">
      <div className="admin-sidebar-position">
        <AdminSidebar />
      </div>
      <div className="addTour-panel">
        {" "}
        {/*"Estoy copiando el estilo del addTour panel, Estos forumlarios los debemos"*/}
        {/*"Unificar en un solo estilo y asi reciclamos " */}
       
        <h2>Editar Tour</h2>
        {tour && (
          <form onSubmit={handleSubmit}>
            <label>
              Nombre:
              <input
                type="text"
                name="name"
                value={tour.name || ""}
                onChange={handleInputChange}
              />
            </label>
            <br />
            <label>
              Descripción:
              <textarea
                name="description"
                value={tour.description || ""}
                onChange={handleInputChange}
              />
            </label>
            <br />
            <label>
              Descripción Corta:
              <textarea
                name="shortDescription"
                value={tour.shortDescription || ""}
                onChange={handleInputChange}
              />
            </label>
            <br />
            <label>
              Categoría:
              <select
                type="text"
                name="categoryId" // <- changed to categoryId
                value={tour.category ? tour.category.id : ""}
                onChange={(e) => {
                  setTour({ ...tour, category: { id: e.target.value } });
                }}
              >
                <option value="">Seleccione una categoría...</option>
                {categories.map((category) => (
                  <option key={category.id} value={category.id}>
                    {category.name}
                  </option>
                ))}
              </select>
            </label>
            <br />
            <label>
              Precio:
              <input
                type="number"
                name="price"
                value={tour.price || 0}
                onChange={handleInputChange}
              />
            </label>
            <br />
            <h3>Imágenes</h3>
            <>
              {tour.images &&
                tour.images
                  .filter((img) => img.url && img.url.trim() !== "")
                  .map((image, index) => (
                    <div key={index}>
                      <label>
                        URL de Imagen {index + 1}:
                        <input type="text" value={image.url} />
                      </label>

                      <button onClick={() => handleImageDelete(index)}>
                        Borrar imagen
                      </button>
                    </div>
                  ))}

              <div className="fileImage">
                <label>O seleccione archivos</label>
                <button type="button" onClick={handleClick}>
                  Browse
                </button>
                <input
                  type="file"
                  accept="image/*"
                  multiple
                  multipart
                  className="file"
                  onChange={handleFilesChange}
                  ref={fileInput}
                  style={{ display: "none" }}
                />
                {selectedFileNames.map((name, idx) => (
                  <div key={idx}>{name}</div>
                ))}
              </div>
            </>
            <br />
            <button type="submit">Actualizar Tour</button>
          </form>
        )}
      </div>
    </div>
  );
};

export default EditTourPage;
