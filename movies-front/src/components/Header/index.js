import React from 'react';
import LoginForm from '../../components/LoginForm'
import {isAuthenticated} from './../../Auth'
import './style.css';

import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({forceRefresh:true});



function Header() {
  
const  logout = () => {
    localStorage.setItem('user', '');
    history.push("/"); 
    //alert('Volte sempre!!!')
}

  return (
    <div className="footer-bottom">
      <div className="header">
          <div className="header-esq">
            <a href="/"> Ceará Movies </a> 
          </div>
          <div className="header-dir" >          
            {
            ( !isAuthenticated() ? <LoginForm /> : <a href="#" onClick={logout}> Sair </a>)
            }
          </div>
      </div>
    </div>
  );
}

export default Header;