  import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './style.css';

const FoodManager = () => {
  const [foods, setFoods] = useState([]);
  const [food, setFood] = useState({
    id: '',
    name: '',
    category: '',
    price: '',
    description: '',
    availability: ''
  });
  const [idToFetch, setIdToFetch] = useState('');
  const [fetchedFood, setFetchedFood] = useState(null);
  const [message, setMessage] = useState('');
  const [editMode, setEditMode] = useState(false);

  const baseUrl = 'http://localhost:2030/foodapi';


  useEffect(() => {
    fetchAllFoods();
  }, []);
  const fetchAllFoods = async () => {
    try {
      const res = await axios.get(`${baseUrl}/all`);
      setFoods(res.data);
    } catch {
      setMessage('Failed to fetch foods.');
    }
  };

  const handleChange = (e) => {
    setFood({ ...food, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    for (let key in food) {
      if (!food[key] || food[key].toString().trim() === '') {
        setMessage(`Please fill out the ${key} field.`);
        return false;
      }
    }
    return true;
  };
  const addFood = async () => {
    if (!validateForm()) return;
    try {
      await axios.post(`${baseUrl}/add`, food);
      setMessage('Food item added successfully.');
      fetchAllFoods();
      resetForm();
    } catch {
      setMessage('Error adding food item.');
    }
  };

  const updateFood = async () => {
    if (!validateForm()) return;
    try {
      await axios.put(`${baseUrl}/update`, food);
      setMessage('Food item updated successfully.');
      fetchAllFoods();
      resetForm();
    } catch {
      setMessage('Error updating food item.');
    }
  };
  const deleteFood = async (id) => {
    try {
      const res = await axios.delete(`${baseUrl}/delete/${id}`);
      setMessage(res.data);
      fetchAllFoods();
    } catch {
      setMessage('Error deleting food item.');
    }
  };

  const getFoodById = async () => {
    try {
      const res = await axios.get(`${baseUrl}/get/${idToFetch}`);
      setFetchedFood(res.data);
      setMessage('');
    } catch {
      setFetchedFood(null);
      setMessage('Food item not found.');
    }
  };

  const handleEdit = (fd) => {
    setFood(fd);
    setEditMode(true);
    setMessage(`Editing food item with ID ${fd.id}`);
  };
  const resetForm = () => {
    setFood({
      id: '',
      name: '',
      category: '',
      price: '',
      description: '',
      availability: ''
    });
    setEditMode(false);
  };

  return (
    <div className="student-container">

      {message && (
        <div className={`message-banner ${message.toLowerCase().includes('error') ? 'error' : 'success'}`}>
          {message}
        </div>
      )}
      <h2>Food Delivery Management</h2>

      <div>
        <h3>{editMode ? 'Edit Food Item' : 'Add Food Item'}</h3>
        <div className="form-grid">
          <input type="number" name="id" placeholder="ID" value={food.id} onChange={handleChange} />
          <input type="text" name="name" placeholder="Name" value={food.name} onChange={handleChange} />
          <input type="text" name="category" placeholder="Category" value={food.category} onChange={handleChange} />
          <input type="number" name="price" placeholder="Price" value={food.price} onChange={handleChange} />
          <input type="text" name="description" placeholder="Description" value={food.description} onChange={handleChange} />
          <select name="availability" value={food.availability} onChange={handleChange}>
            <option value="">Select Availability</option>
            <option value="AVAILABLE">AVAILABLE</option>
            <option value="OUT_OF_STOCK">OUT OF STOCK</option>
          </select>
        </div>
         <div className="btn-group">
          {!editMode ? (
            <button className="btn-blue" onClick={addFood}>Add Food</button>
          ) : (
            <>
              <button className="btn-green" onClick={updateFood}>Update Food</button>
              <button className="btn-gray" onClick={resetForm}>Cancel</button>
            </>
          )}
        </div>
      </div>

      <div>
        <h3>Get Food By ID</h3>
        <input
          type="number"
          value={idToFetch}
          onChange={(e) => setIdToFetch(e.target.value)}
          placeholder="Enter ID"
        />
        <button className="btn-blue" onClick={getFoodById}>Fetch</button>
        {fetchedFood && (
          <div>
            <h4>Food Found:</h4>
            <pre>{JSON.stringify(fetchedFood, null, 2)}</pre>
          </div>
        )}
      </div>

      <div>
        <h3>All Food Items</h3>
        {foods.length === 0 ? (
          <p>No food items found.</p>
        ) : (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  {Object.keys(food).map((key) => (
                    <th key={key}>{key}</th>
                  ))}
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                  {foods.map((fd) => (
                  <tr key={fd.id}>
                    {Object.keys(food).map((key) => (
                      <td key={key}>{fd[key]}</td>
                    ))}
                    <td>
                      <div className="action-buttons">
                        <button className="btn-green" onClick={() => handleEdit(fd)}>Edit</button>
                        <button className="btn-red" onClick={() => deleteFood(fd.id)}>Delete</button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

    </div>
  );
};

export default FoodManager;
