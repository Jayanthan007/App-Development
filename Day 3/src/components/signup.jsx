import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import './ls.css';
import { Link } from 'react-router-dom/cjs/react-router-dom';

const Signup = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSignup = (e) => {
    e.preventDefault();
    const data = new FormData(e.currentTarget);
    console.log({
        name:data.get('name'),
        email:data.get('email'),
        password:data.get('password')
    });
  };

  return (
    <div className="container">
      <form className="signup-form" onSubmit={handleSignup}>
        {/* <div className='title'>
            Bookstagram.inc
        </div> */}
        <h1>Sign up Form</h1>
        <TextField
              margin="normal"
              required
              fullWidth
              id="name"
              label="Name"
              name="name"
              autoComplete="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              autoFocus
            />
        <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="email"
              autoComplete="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              autoFocus
            />
        <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              autoComplete="current-password"
            />
            <br/><br/>   
        <button type="submit">Sign up</button>
        <br/><br/>
        <p>Already have an account? <Link to="/login">Login</Link></p>
      </form>
    </div>
  );
};

export default Signup;
