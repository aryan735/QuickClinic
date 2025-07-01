import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Toaster } from "react-hot-toast";
import About from "./pages/About";
import Contact from "./pages/Contact";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Home from "./pages/Home";
import Header from "./components/Header";
import DashboardHome from "./pages/DashboardHome";
import ViewDetails from "./pages/ViewDetails";
import UpdateProfile from "./pages/UpdateProfile";
import DeleteAccount from "./pages/DeleteAccount";

function App() {
  return (
    <Router>
      <div className="font-sans text-gray-800">
        {/* Toast Notifications Provider */}
        <Toaster
          position="top-center"
          toastOptions={{
            className: "hospital-toast",
            duration: 4000,
            style: {
              background: "#f0f9ff",  // Light blue background
              color: "#0369a1",       // Dark blue text
              border: "1px solid #bae6fd",
              fontSize: "0.875rem",
              borderRadius: "0.75rem",
              boxShadow: "0 4px 6px -1px rgb(0 0 0 / 0.1)",
            },
            success: {
              iconTheme: {
                primary: "#22c55e",  // Green for success
                secondary: "#f8fafc",
              },
            },
            error: {
              iconTheme: {
                primary: "#ef4444",  // Red for errors
                secondary: "#f8fafc",
              },
            },
          }}
        />
        
        <Header />   {/* Use the new header */}
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/dashboard" element={<DashboardHome />} />
          <Route path="/dashboard/view" element={<ViewDetails />} />
          <Route path="/dashboard/update" element={<UpdateProfile />} />
          <Route path="/dashboard/delete" element={<DeleteAccount />} />
        </Routes>
        <footer className="p-4 text-center text-sm text-gray-500">
          &copy; 2025 QuickClinic. All rights reserved.
        </footer>
      </div>
    </Router>
  );
}

export default App;