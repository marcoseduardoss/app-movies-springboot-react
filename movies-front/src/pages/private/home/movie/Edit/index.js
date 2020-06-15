import React from "react";
import "./movieEdit.css";
import Api from "./../../../../../api/api";
import useForm from "../../../../../hooks/useForm";
import useGetMovie from "../../../../../hooks/useGetMovie";
import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({ forceRefresh: true });
//history.push("/"); 

const MovieEdit = (props) => {
  
  const [movieIn, isLoading, error, fetchMovie] = useGetMovie(props.id);

  const [ { values, loading } , handleChange, handleSubmit] = useForm();

  const saveOrUpdate = () =>{
    
    if(props.id != null)
      update();
    else
      save();
    
  }

  const update = () =>{
    try {

      let movie = {"title": values.title, "synopsis": values.synopsis, "protagonists": values.protagonists, 
                    "producer": values.producer, "year": values.year, "score": 0 }

      let saved = Api.updateMovie(props.id, movie);

      alert('Edição realizada com sucesso!')

      history.push("/");

    } catch (e) {
      alert(e.message);
    }
  }

  const save = () => {
    try {

      let movie = { "title": values.title, "synopsis": values.synopsis, "protagonists": values.protagonists, 
                    "producer": values.producer, "year": values.year, "score": 0 }

      let saved = Api.saveMovie(movie);

      alert('Objeto incluído com sucesso!')

      history.push("/");

    } catch (e) {
      alert(e.message);
    }
  };

  return (
    <div align="center">
      <form onSubmit={handleSubmit(saveOrUpdate)}  >
        {(props.id != null) ? 'ID: ' + props.id : '' }
        <br /> <br />
        <input
          onChange={handleChange}
          type="text"    
          defaultValue={movieIn.title}     
          name="title"
          placeholder="title"
        />
        <br /><br />
        <textarea
          onChange={handleChange}
          type="text"
          defaultValue={movieIn.synopsis}
          name="synopsis"
          placeholder="synopsis"
        ></textarea>
        <br /><br />
        <input
          onChange={handleChange}
          type="text"
          defaultValue={movieIn.protagonists}
          name="protagonists"
          placeholder="protagonists"
        />
        <br /><br />
        <input
          onChange={handleChange}
          type="text"
          name="producer"
          defaultValue={movieIn.producer}
          placeholder="producer"
        />
        <br /><br />
        <input
          onChange={handleChange}
          type="text"
          defaultValue={movieIn.year}
          name="year"
          placeholder="year"
        />
        <br /><br />
        <input
          onChange={handleChange}
          type="text"
          name="score"
          defaultValue={movieIn.score}
          placeholder="score"
        />
        <br /><br />
        <button type="submit">{loading ? "Enviando..." : "Enviar"}</button>
        <br /><br />
      </form>
    </div>
  );
};

export default MovieEdit;