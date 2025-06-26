import React from "react";

export default function Home() {
  return (
    <div className="min-h-screen bg-gray-100 p-4 lg:p-8">
      
      {/* Hero Section */}
      <div className="flex flex-col lg:flex-row items-center justify-between">
        
        {/* Text block */}
        <div className="max-w-lg lg:w-1/2 mb-8 lg:mb-0 lg:ml-12">
          <h1 className="text-5xl sm:text-6xl font-extrabold text-blue-950 mb-4">
            Your Health Our Priority
          </h1>
          <p className="text-lg sm:text-xl text-blue-950 mb-5">
            Providing quality healthcare services with care and compassion.
          </p>
          <button className="bg-blue-900 text-white px-6 py-3 rounded hover:bg-blue-900 transition">
            Get Started
          </button>
        </div>

        {/* Image */}
        <div className="lg:w-1/2 flex justify-center">
          <img
            src="https://img.freepik.com/free-vector/doctor-clinic-illustration_1308-27202.jpg?w=740"
            alt="Doctor at Clinic"
            className="rounded shadow-lg max-w-xs sm:max-w-sm md:max-w-md w-full"
          />
        </div>
      </div>

      {/* Features Section */}
      <div className="mt-10 grid grid-cols-1 sm:grid-cols-3 gap-4 max-w-4xl mx-auto">
        <div className="flex flex-col items-center text-center bg-white p-4 rounded shadow-sm">
          <div className="w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center mb-2">
            <img
              src="https://cdn-icons-png.flaticon.com/512/10337/10337986.png"
              alt="Book Appointments"
              className="w-8 h-8"
            />
          </div>
          <p className="font-medium">Book Appointments</p>
        </div>

        <div className="flex flex-col items-center text-center bg-white p-4 rounded shadow-sm">
          <div className="w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center mb-2">
            <img
              src="https://cdn-icons-png.flaticon.com/512/10035/10035149.png"
              alt="Consult with Doctors"
              className="w-8 h-8"
            />
          </div>
          <p className="font-medium">Consult with Doctors</p>
        </div>

        <div className="flex flex-col items-center text-center bg-white p-4 rounded shadow-sm">
          <div className="w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center mb-2">
            <img
              src="https://cdn-icons-png.flaticon.com/512/888/888879.png"
              alt="Access Medical Records"
              className="w-8 h-8"
            />
          </div>
          <p className="font-medium">Access Medical Records</p>
        </div>
      </div>
    </div>
  );
}
