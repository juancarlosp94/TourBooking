import "./App.css";
import NavBar from "./Components/NavBar.jsx";
import { Outlet } from "react-router-dom";
import { UserProvider } from "./Context/UserContext";

/*import Admin from './Components/Admin.jsx';*/

function App() {
  return (
    <UserProvider>
      <div className="App">
        <NavBar />
        <Outlet />
      </div>
    </UserProvider>
  );
}

export default App;
