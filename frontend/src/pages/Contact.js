import React from "react";

function Contact() {
  return (
    <div className="min-h-screen flex flex-col md:flex-row items-center justify-center bg-gray-100 p-8">
      <div className="max-w-lg mb-8 md:mb-0 md:mr-8">
        <h2 className="text-4xl font-extrabold text-blue-900 mb-4">Contact Us</h2>
        <p className="text-gray-700 mb-2">
          📧 <span className="font-semibold">Email:</span> support@quickclinic.com
        </p>
        <p className="text-gray-700">
          📞 <span className="font-semibold">Phone:</span> +91 98765 43210
        </p>
      </div>
      <img 
        src="https://img.freepik.com/free-vector/customer-support-flat-design-illustration_23-2148887720.jpg?w=740"
        alt="Contact QuickClinic"
        className="w-80 rounded-lg shadow-lg"
      />
    </div>
  );
}

export default Contact;
