import React from "react";
import useRatingForm from "./../../hooks/useRatingForm";
import "./rating.css";


export default () => {
  
  const [ {values, loading}, handleChange, handleSubmit] = useRatingForm();
  
  const enviarContato = () => {
    let rating = {
      "idMovie":2,
      "score":values.score,
      "author":values.author,
      "comment":values.comment
    };
    return rating;
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
          name="score"
          placeholder="Digite o score"
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