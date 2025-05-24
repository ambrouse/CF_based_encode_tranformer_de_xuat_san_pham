// src/router/index.jsx
import { createBrowserRouter } from "react-router-dom";
import Authen from "../pages/Authen";
import MainLayout from "../layout/MainLayout";
import Home from '../pages/Home'
import Addimg_page from "../pages/Addimg_page";
import Decription_page from "../pages/Decription_page";
import Cart_page from '../pages/Cart_page'
import User_profile from '../pages/User_profile'


const Router = createBrowserRouter([
  {
    path: "/login-sigup",
    element: <Authen />,
  },
  {
    path: "/",
    element: <MainLayout />,
    children: [
      { index: true, element: <Home /> },
      { path: "login-sigup", element: <Authen /> },
      { path: "Addimg", element: <Addimg_page /> },
      { path: "decription-product/:_id", element: <Decription_page /> },
      { path: "cart", element: <Cart_page />},
      { path: "user-profile", element: <User_profile />},
    
    ],
  },
]);

export default Router;