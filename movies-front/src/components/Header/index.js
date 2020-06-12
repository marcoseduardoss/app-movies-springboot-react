import React from 'react';
import LoginForm from './../../pages/LoginForm'
import './style.css';

function Header() {
  return (
    <div className="footer-bottom">
      <div className="header">
          <div className="header-esq">
            <a href="#"> Ceará Movies </a> 
          </div>
          <div className="header-dir">          
            <LoginForm />
          </div>
      </div>
    </div>
  );
}

export default Header;