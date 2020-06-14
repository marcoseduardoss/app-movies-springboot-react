import React from "react";
import './movie.css'
import useGetMovie from './../../../../../hooks/useGetMovie';
import RatingForm from '../Rating';

const LoadingMessage = () => {

  return (
    <p>
      Loading data, please wait...
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

                <h1>{movie.id} - {movie.title}</h1>        
                <img src={movie.thumbnail} alt="Capa" /> 
                <h3> Sinopse</h3>
                <div >{movie.synopsis} </div> 
                <div><a href="/">Voltar</a> </div> 
                <div><RatingForm /></div>    
            
      </div>
    </div>
  );
}

  return <div className="home">
    
    
    {
        
        error ? <Error message={error} /> : 
        (isLoading ? <LoadingMessage /> : <DetailMovie movie={movie}  /> )
    }

  </div>;

}

export default MovieDetails;