import React from "react";
import './list.css'
import useListMovies from './../../../../../hooks/useListMovies';
import Api from "./../../../../../api/api";

import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({ forceRefresh: true });
//history.push("/"); 

const LoadingMessage = () => {
  return (<div height="800px">Processando dados, por favor aguarde...</div>);
}

const Error = (props) => {
  const { message } = props;
  return (<b>{message}</b>);
}

const handleEditItem = (e) => {
  const id = e.target.getAttribute("id");
  history.push("/movie/edit/" + id);
}

const handleAddItem = (e) => {
  history.push("/movie/add");
}

const handleRemoveItem = (e) => {

  const id = e.target.getAttribute("id");

  if (window.confirm("Deseja apagar o registro com código " + id + "?")) {
    try {

      Api.removeMovie(id);

      history.push("/");

    } catch (e) {
      alert(e);
    }
  }
};

const MovieItem = (props) => {

  const { movie } = props;

  return (

    <tr >
      <td><img src={movie.thumbnail} title="Capa" height="125px" width="185px" alt="foto" /></td>

      <td  >
        <p>
          <div className="movie-descriptons">{movie.title}</div>
          <div className="movie-descriptons">{movie.producer}</div>          
          <div className="movie-descriptons">{movie.year}</div>
          <div className="movie-descriptons" >Id: {movie.id}</div>
          
        </p> 
      </td>

      <td><button id={movie.id} onClick={handleRemoveItem} >Remover</button></td>
      <td><button id={movie.id} onClick={handleEditItem} >Alterar</button></td>

    </tr>
  );

}

const ListMoviesTable = (props) => {

  const { movies } = props;


  return (<div className="container">

    <button onClick={handleAddItem} >Incluir Novo</button>

    <table>
      {
        movies.map(movie => <MovieItem key={movie.id} movie={movie} />)
      }

    </table>
  </div>

  );
}

function MoviesListPrivateAccess() {

  const [movies, isLoading, error] = useListMovies();//, fetchMovies


  return <div className="home">


    {
      error ? <Error message={error} /> :
        (isLoading ? <LoadingMessage /> : <ListMoviesTable movies={movies} />)
    }

  </div>;

}


export default MoviesListPrivateAccess;