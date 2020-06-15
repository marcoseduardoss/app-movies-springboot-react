import React from "react";
import useLoginForm from "../../hooks/useLoginForm";
import "./login.css";

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

          placeholder="Usuário: admin"
        />
        <input
          onChange={handleChange}
          type="password"
          name="password"
          placeholder="Senha: 123"
        />    
        
          <button type="submit">{loading ? "Enviando..." : "Enviar"}</button>
        </form>
    </div>
  );
};