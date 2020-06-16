import React from "react";
import './list.css'
import useListMovies from './../../../../../hooks/useListMovies';

import { ProgressBar } from 'primereact/progressbar';
import { Rating } from 'primereact/rating';

import 'primeicons/primeicons.css';
import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.css';
import 'primeflex/primeflex.css';

const LoadingMessage = () => {
  return (<div height="800px">
    Processando dados, por favor aguarde...
    <ProgressBar mode="indeterminate" style={{ height: '6px' }}></ProgressBar>
  </div>);
}

const Error = (props) => {
  const { message } = props;
  return (<b>{message}</b>);
}

const MovieItem = (props) => {

  const { movie } = props;

  return (

    <article>
      <strong>{movie.title}</strong>

      <Rating value={movie.score} readonly={true} stars={5} cancel={false} />

      <img width="700px" height="400px" src={movie.thumbnail} alt="Capa" />

      <a href={"/movie/" + movie.id}>Acessar</a>
    </article>
  );

}

const ListMoviesTable = (props) => {

  const { movies } = props;

  return (<div className="container">

    <div className="listafilmes">
      {
        movies.map(movie => <MovieItem key={movie.id} movie={movie} />)
      }

    </div>
  </div>

  );
}

function MoviesListPublicAccess() {

  const [movies, isLoading, error] = useListMovies();//, fetchMovies


  return <div className="home">


    {
      error ? <Error message={error} /> :
        (isLoading ? <LoadingMessage /> : <ListMoviesTable movies={movies} />)
    }

  </div>;

}

export default MoviesListPublicAccess;