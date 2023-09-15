import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import './ls.css';
import { Link } from 'react-router-dom/cjs/react-router-dom';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = (e) => {
    e.preventDefault();
    const data = new FormData(e.currentTarget);
    console.log({
        email:data.get('email'),
        password:data.get('password')
    });
 };

  return (
    <div className="container">
      <form className="login-form" onSubmit={handleLogin}>
        {/* <div className='title'>
            Bookstagram.inc
        </div> */}
        <h1>Login Form</h1>
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
        <button type="submit">Login</button>
        <br/><br/>
        <p>Don't have an account? <Link to="/signup">Sign up</Link></p>
      </form>
    </div>
  );
};

export default Login;
