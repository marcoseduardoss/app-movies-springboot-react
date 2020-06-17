import React from "react";
import './movie.css'
import useGetMovie from './../../../../../hooks/useGetMovie';
import RatingForm from '../Rating';

import { Rating } from 'primereact/rating';
import 'primeicons/primeicons.css';
import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.css';
import 'primeflex/primeflex.css';
import './movie.css'

const LoadingMessage = () => {

  return (
    <p>
      Carregando dados, por favor aguarde...
    </p>
  );
}

const Error = (props) => {
  const { message } = props;
  return (
    <h3>
      {message}
    </h3>
  );
}

function MovieDetails(props) {

  const { id } = props;

  const [movie, isLoading, error] = useGetMovie(id);//, fetchMovie

  const DetailMovie = (props) => {

    const { movie } = props;

    return (<div className="container">
      <div className="movie-detail">

        <h1>{movie.title}</h1>

        <img src={movie.thumbnail} alt="Capa" />
        
        <div className="p-grid">
          <div className="p-col"><h3> Sinopse</h3></div>
          <div className="p-col">
            <div align="right">
              <Rating value={movie.score} readonly={true} stars={5} cancel={false} />
            </div>
          </div>

        </div>

        <div >{movie.synopsis} </div>
        <div>
            <RatingForm id={movie.id} />
        </div>

      </div>
    </div>
    );
  }

  return <div className="home">


    {

      error ? <Error message={error} /> :
        (isLoading ? <LoadingMessage /> : <DetailMovie movie={movie} />)
    }

  </div>;

}

export default MovieDetails;