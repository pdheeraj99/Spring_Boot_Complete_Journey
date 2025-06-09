import React, { useState } from 'react';
import './App.css';

function App() {
  const [booking, setBooking] = useState(null);
  const [formData, setFormData] = useState({
    customer: { name: '', email: '' },
    travel: { destination: '', date: '' }
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const response = await fetch('http://localhost:8081/customers/book', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          customer: formData.customer,
          travelDetails: formData.travel
        })
      });
      
      if (!response.ok) {
        throw new Error('Booking failed');
      }
      
      const result = await response.json();
      setBooking(result);
    } catch (error) {
      console.error('Error:', error);
      alert('Booking failed. Please try again.');
    }
  };

  const handleChange = (e, section) => {
    setFormData({
      ...formData,
      [section]: {
        ...formData[section],
        [e.target.name]: e.target.value
      }
    });
  };

  return (
    <div className="app-container">
      <h1>Travel Booking System</h1>
      
      <form onSubmit={handleSubmit} className="booking-form">
        <div className="form-section">
          <h2>Customer Details</h2>
          <input 
            name="name"
            placeholder="Full Name"
            value={formData.customer.name}
            onChange={(e) => handleChange(e, 'customer')}
            required
          />
          <input
            name="email"
            type="email"
            placeholder="Email"
            value={formData.customer.email}
            onChange={(e) => handleChange(e, 'customer')}
            required
          />
        </div>
        
        <div className="form-section">
          <h2>Travel Details</h2>
          <input
            name="destination"
            placeholder="Destination"
            value={formData.travel.destination}
            onChange={(e) => handleChange(e, 'travel')}
            required
          />
          <input
            name="date"
            type="date"
            value={formData.travel.date}
            onChange={(e) => handleChange(e, 'travel')}
            required
          />
        </div>
        
        <button type="submit">Book Ticket</button>
      </form>

      {booking && (
        <div className="booking-confirmation">
          <h2>Booking Confirmed!</h2>
          <div className="customer-details">
            <h3>Customer Information</h3>
            <p><strong>Name:</strong> {booking.customer.name}</p>
            <p><strong>Email:</strong> {booking.customer.email}</p>
          </div>
          
          <div className="ticket-details">
            <h3>Ticket Information</h3>
            <p><strong>Ticket ID:</strong> {booking.ticket.id}</p>
            <p><strong>Destination:</strong> {booking.ticket.destination}</p>
            <p><strong>Travel Date:</strong> {booking.ticket.date}</p>
            <p><strong>Status:</strong> {booking.ticket.status}</p>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;