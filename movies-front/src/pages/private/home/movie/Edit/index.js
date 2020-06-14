import React from "react";
import "./movieEdit.css";
import Api from "./../../../../../api/api";
import useForm from "../../../../../hooks/useForm";
import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({ forceRefresh: true });
//history.push("/"); 



const MovieEdit = () => {

  const [{ values, loading }, handleChange, handleSubmit] = useForm();

  const enviarContato = () => {
    try {

      let movie = { "title": values.title, "synopsis": values.synopsis, "protagonists": values.protagonists, 
                    "producer": values.producer, "year": values.year, "score": 0 }

      let saved = Api.saveMovie(movie);

      alert('Operação realizada com sucesso! - ' + saved.id)

      history.push("/");

    } catch (e) {
      alert(e.message);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit(enviarContato)}  >

        <input
          onChange={handleChange}
          type="text"
          name="title"
          placeholder="title"
        />
        <br /><br />
        <input
          onChange={handleChange}
          type="text"
          name="synopsis"
          placeholder="synopsis"
        />
        <br /><br />
        <input
          onChange={handleChange}
          type="text"
          name="protagonists"
          placeholder="protagonists"
        />
        <br /><br />
        <input
          onChange={handleChange}
          type="text"
          name="producer"
          placeholder="producer"
        />
        <br /><br />
        <input
          onChange={handleChange}
          type="text"
          name="year"
          placeholder="year"
        />
        <br /><br />
        <input
          onChange={handleChange}
          type="text"
          name="score"
          placeholder="score"
        />

        <button type="submit">{loading ? "Enviando..." : "Enviar"}</button>

      </form>
    </div>
  );
};

export default MovieEdit;