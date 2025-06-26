import React from "react";

function About() {
  return (
    <div className="min-h-screen flex flex-col md:flex-row items-center justify-center bg-gray-100 p-8">
      <div className="max-w-lg mb-8 md:mb-0 md:mr-8">
        <h2 className="text-4xl font-extrabold text-blue-900 mb-4">About QuickClinic</h2>
        <p className="text-gray-700 leading-relaxed">
          QuickClinic is your all-in-one platform for seamless healthcare access. 
          From booking appointments to managing records and consulting top doctors — 
          we’ve got you covered with care, compassion, and cutting-edge technology.
        </p>
      </div>
      <img 
        src="https://img.freepik.com/free-vector/medical-team-concept-illustration_114360-717.jpg?w=740"
        alt="About QuickClinic"
        className="w-80 rounded-lg shadow-lg"
      />
    </div>
  );
}

export default About;
