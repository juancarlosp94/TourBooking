import React, { useState, useEffect } from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import AdminPanel from "./routes/AdminPanel";
import Home from "./routes/Home";
import RegisterForm from "./routes/RegisterForm";
import Login from "./routes/Login";
import ProductList from "./routes/ProductList";
import Category from "./routes/Category";
import TourDetail from "./routes/TourDetail";
import ReservationDetail from "./routes/ReservationDetail";
import EditTourPage from "./routes/EditTourPage";
import UserList from "./routes/UserList.jsx";
import AddTour from "./routes/AddTour";
import AddCategory from "./routes/AddCategory";
import CategoryList from "./routes/CategoryList";
import ReservationList from "./routes/ReservationList";
import FavoriteList from "./routes/FavoriteList";
import UserProfile from "./routes/UserProfile";
import Logout from "./routes/Logout";
import AddCharacteristic from "./routes/AddCharacteristic";
import CharacteristicList from "./routes/CharacteristicList";
import BookTour from "./routes/BookTour";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <React.StrictMode>
    <Router>
      {/* Wrap all your routes in a Routes component */}
      <Routes>
        {/* Define the layout route */}
        <Route path="/" element={<App />}>
          <Route path="admin/adminPanel" element={<AdminPanel />} />
          <Route index element={<Home />} />
          <Route path="login" element={<Login />} />
          <Route path="registerForm" element={<RegisterForm />} />
          <Route path="admin/productList" element={<ProductList />} />
          <Route path="admin/userList" element={<UserList />} />
          <Route path="admin/addTour" element={<AddTour />} />
          <Route path="/admin/edittour/:id" element={<EditTourPage />} />
          <Route path="category/:id" element={<Category />} />
          <Route path="tours/:id" element={<TourDetail />} />
          <Route path="reservation/:id" element={<ReservationDetail />} />
          <Route path="admin/addCategories" element={<AddCategory />} />
          <Route path="admin/categoryList" element={<CategoryList />} />
          <Route path="reservationList" element={<ReservationList />} />
          <Route path="favoriteList" element={<FavoriteList />} />
          <Route path="userProfile" element={<UserProfile />} />
          <Route path="logout" element={<Logout />} />
          <Route
            path="admin/addCharacteristic"
            element={<AddCharacteristic />}
          />
          <Route
            path="admin/characteristicList"
            element={<CharacteristicList />}
          />
          <Route path="bookTour/:id" element={<BookTour />} />
        </Route>
      </Routes>
    </Router>
  </React.StrictMode>
);

reportWebVitals();
