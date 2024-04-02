import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useUserContext } from "../Context/UserContext";

const Logout = () => {
  const { logout } = useUserContext();
  const navigate = useNavigate();

  useEffect(() => {
    // Call the logout method from the GlobalContext
    logout();

    // Redirect the user to the home page ("/")
    navigate("/");
  }, [logout, navigate]);

  // You can optionally display a message or loading indicator while logging out
  return <div>Logging out...</div>;
};

export default Logout;
