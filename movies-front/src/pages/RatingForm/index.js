import React from "react";
import useRatingForm from "./../../hooks/useRatingForm";
import Api from "./../../api/api";
import "./rating.css";


export default () => {
  
  const [ {values, loading}, handleChange, handleSubmit] = useRatingForm();
  

  const enviarContato = () => {
    var list = Api.saveRating(
      {"idMovie":2,"score":5,"author":"Maria Clara","comment":"kkkkkkkkkkkkkkkkkkkkkkkkk."} 
    )
    // faça o que for preciso :)
    console.log(list);
  };

  return (
    <div>
      <h1>Contato</h1>
      <form onSubmit={handleSubmit(enviarContato)}>
        <input
          onChange={handleChange}
          type="text"
          name="author"
          placeholder="Digite o seu nome"
        />
        <input
          onChange={handleChange}
          type="text"
          name="email"
          placeholder="Digite o seu e-mail"
        />
        <input
          onChange={handleChange}
          type="text"
          name="comment"
          placeholder="Mensagem"
        />
        <button type="submit">{loading ? "Enviando..." : "Enviar"}</button>
      </form>
    </div>
  );
};