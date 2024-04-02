import { useState, useRef} from "react";
import Footer from '../Components/Footer'
import AdminSidebar from "../Components/AdminSidebar";
import axios from "axios";
import style from "../style/AddCharacteristic.css"
import { useUserContext } from "../Context/UserContext";

const AddCharacteristic = () => {
  const [errors, setErrors] = useState({});
  const { path } = useUserContext();
  
  const [characteristicsData, setCharacteristicsData] = useState({
    name: "",
    urlCharacteristicImage:[],
  });


  // validacion de formulario
  const validateForm = () => {
    let isValid = true;
    const validationErrors = {};

    if (characteristicsData.name === "") {
      validationErrors.name = "El nombre es requerido.";
      isValid = false;
    }
    if (characteristicsData.urlCharacteristicImage=== "") {
        validationErrors.urlCharacteristicImage = "Ingrese una icono.";
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
  const newCharacteristic = {
    name: characteristicsData.name,
    urlCharacteristicImage: characteristicsData.urlCharacteristicImage
  };

  formData.append("characteristic", JSON.stringify(newCharacteristic));
 


  try {
    await axios.post(`http://${path}/characteristic`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    alert("La caracteristica se ha cargado correctamente!");
    setCharacteristicsData({
      name: "",
      urlCharacteristicImage:"",
    });
    

  } catch (error) {
    console.error("Error en el formulario de registro:", error);
    alert("Verifique los datos ingresados.");
  }
};
console.log(characteristicsData);
  

  return (
    <>
    <div className="admin-format">
      <div className="admin-sidebar-position">
      <AdminSidebar/>
      </div>
      <div className='addCharacteristic'>
        <h2 className="add-category-tittle">Agrega una nueva caracteristica</h2>
        <form className='form-category-new'>
        <label>Nombre de la caracteristica:</label>
            <input
              type="text"
              name="name"
              placeholder="Escribe un nombre"
              value={characteristicsData.name}
              onChange={(e) => setCharacteristicsData({...characteristicsData, name: e.target.value })}
              style={errors.name ? { border: "2px solid #F28705" } : {}}
            />
      
          <label>Agrege un icono a la caracteristica:</label>
            <input
              type="text"
              name="codeIcone"
              placeholder="Escribe el codigo del icono"
              onChange={(e) => setCharacteristicsData({...characteristicsData, urlCharacteristicImage: e.target.value })}
            />
          
       
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

    <Footer/>
    
    </>

  )
  }
      
 
export default AddCharacteristic
