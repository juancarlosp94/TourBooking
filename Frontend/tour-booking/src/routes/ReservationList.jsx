import React, { useEffect, useState } from "react";
import axios from "axios";
import { useUserContext } from "../Context/UserContext";
import Footer from "../Components/Footer";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import { Link, useParams } from "react-router-dom";

const columns = [
  { id: "idReservation", label: "ID", minWidth: 20 },
  { id: "date", label: "FECHA", minWidth: 20 },
  { id: "tour.name", label: "NOMBRE", minWidth: 100 },
  { id: "tour.description", label: "DESCRIPCIÓN", minWidth: 100 },
  { id: "tickets", label: "CANT.", minWidth: 100 },
  { id: "rating.ratingValue", label: "PUNTAJE", minWidth: 100 },
  { id: "rating.comments", label: "COMENTARIO", minWidth: 100 },
  {
    id: "actions",
    label: "ELIMINAR",
    minWidth: 100,
  },
];

const FavoriteList = () => {
  const { loggedUser, path } = useUserContext();
  const [reserva, setReserva] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://${path}/reservation/byusuarioid/${loggedUser.id}`
        );
        setReserva(response.data);
        setLoading(false);
      } catch (error) {
        console.error("Error al cargar el listado de favoritos: ", error);
        setLoading(false);
      }
    };
    fetchData();
  }, [loggedUser.id]);

  const handleEraseFavorite = async (id) => {
    try {
      await axios.delete(`http://${path}/reservation/${id}`, {
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(parseInt(loggedUser.id)),
      });
      console.log("Favorito borrado correctamente.");
      setReserva((prevReserva) =>
        prevReserva.filter((reserva) => reserva.idReservation !== id)
      );
    } catch (error) {
      console.log("Error borrando el favorito: ", error);
    }
  };

  if (loading) {
    return (
      <div className="loading">
        <h1>Cargando listado de reservas...</h1>
        <img src="/images/loading.gif" alt="" />
      </div>
    );
  }

  return (
    <>
      <div className="bodyfavoritos">
        <h2>Mis Reservas</h2>
        <TableContainer style={{ backgroundColor: "white" }}>
          <Table stickyHeader aria-label="sticky table">
            <TableHead>
              <TableRow>
                {columns.map((column) => (
                  <TableCell
                    key={column.id}
                    align="left"
                    style={{ minWidth: column.minWidth, color: "black" }}
                  >
                    {column.label}
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {reserva.map((row) => (
                <TableRow key={row.idReservation}>
                  {columns.map((column) => {
                    const value =
                      column.id === "tour.name"
                        ? row.tour.name
                        : column.id === "tour.description"
                        ? row.tour.description
                        : column.id === "rating.ratingValue"
                        ? row.rating?.ratingValue || "N/A"
                        : column.id === "rating.comments"
                        ? row.rating?.comments || "-"
                        : row[column.id];

                    if (
                      column.id === "rating.ratingValue" &&
                      (value === "N/A" || value === null)
                    ) {
                      return (
                        <TableCell
                          key={column.id}
                          align="left"
                          className="table-cell" // Use className for CSS
                        >
                          <Link to={"../reservation/" + row.idReservation}>
                            <button className="button-delete">Puntuar</button>
                          </Link>
                        </TableCell>
                      );
                    } else {
                      return (
                        <TableCell
                          key={column.id}
                          align="left"
                          className="table-cell" // Use className for CSS
                        >
                          {column.id === "actions" ? (
                            <button
                              className="button-delete"
                              onClick={() =>
                                handleEraseFavorite(row.idReservation)
                              }
                            >
                              <span className="material-symbols-outlined">
                                delete
                              </span>
                            </button>
                          ) : (
                            value
                          )}
                        </TableCell>
                      );
                    }
                  })}
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
      <Footer />
    </>
  );
};

export default FavoriteList;
