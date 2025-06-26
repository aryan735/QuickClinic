import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import About from "./pages/About";
import Contact from "./pages/Contact";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Home from "./pages/Home";
import Header from "./components/Header";

function App() {
  return (
    <Router>
      <div className="font-sans text-gray-800">
        <Header />   {/* Use the new header */}
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
        </Routes>
        <footer className="p-4 text-center text-sm text-gray-500">
          &copy; 2025 QuickClinic. All rights reserved.
        </footer>
      </div>
    </Router>
  );
}

export default App;
