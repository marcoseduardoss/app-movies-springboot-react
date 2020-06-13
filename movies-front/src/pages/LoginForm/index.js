import React from "react";
import useLoginForm from "./../../hooks/useLoginForm";
import "./login.css";
import { Link } from 'react-router-dom'

export default () => {
  
  const [ {values, loading}, handleChange, handleSubmit] = useLoginForm();
  
  const enviarContato = () => {
    let login = {
      "username":values.username,
      "password":values.password
    };
    return login;
  };

  return (
    <div>
      <form onSubmit={handleSubmit(enviarContato)}  >
        <input
          onChange={handleChange}
          type="text"
          name="username"
          placeholder="Usuário"
        />
        <input
          onChange={handleChange}
          type="password"
          name="password"
          placeholder="Senha"
        />    
        
          <button type="submit">{loading ? "Enviando..." : "Enviar"}</button>
        </form>
    </div>
  );
};