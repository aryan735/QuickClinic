import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';
import { Button } from '../components/ui/button';
import { FaExclamationTriangle, FaTrash, FaTimes } from 'react-icons/fa';
import axiosConfig from '../config/axiosConfig';
import { toast } from 'react-hot-toast';

const DeleteAccount = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [confirming, setConfirming] = useState(false);

  const handleDelete = async () => {
    setLoading(true);
    try {
      await axiosConfig.delete('/users/delete-user');
      toast.success('Account deleted successfully');
      localStorage.removeItem('token');
      navigate('/login');
    } catch (error) {
      toast.error('Failed to delete account');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-red-50 to-pink-100 flex items-center justify-center p-6">
      <motion.div
        initial={{ opacity: 0, scale: 0.9 }}
        animate={{ opacity: 1, scale: 1 }}
        className="bg-white rounded-2xl shadow-xl p-8 max-w-md w-full text-center"
      >
        <div className="flex justify-center mb-4">
          <FaExclamationTriangle className="text-red-600 text-5xl" />
        </div>
        <h2 className="text-2xl font-bold text-red-700 mb-2">Delete Account</h2>
        <p className="text-gray-600 mb-6">
          This action is irreversible. Are you sure you want to permanently delete your account?
        </p>

        {confirming ? (
          <div className="flex justify-center gap-4">
            <Button
              onClick={handleDelete}
              className="bg-red-600 hover:bg-red-700"
              disabled={loading}
            >
              <FaTrash className="mr-2" />
              {loading ? 'Deleting...' : 'Yes, Delete'}
            </Button>
            <Button
              onClick={() => setConfirming(false)}
              variant="outline"
              className="gap-2"
            >
              <FaTimes /> Cancel
            </Button>
          </div>
        ) : (
          <Button
            onClick={() => setConfirming(true)}
            className="bg-red-600 hover:bg-red-700"
          >
            <FaTrash className="mr-2" />
            Delete My Account
          </Button>
        )}
      </motion.div>
    </div>
  );
};

export default DeleteAccount;
