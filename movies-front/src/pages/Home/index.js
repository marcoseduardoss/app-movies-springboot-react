
import React from "react";
import './home.css'
import useListMovies from './../../hooks/useListMovies';

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

const MovieItem = (props) => {

  const { movie } = props;

  return (

    <article>
        <strong>{movie.title}</strong>
        
        <img width="700px" height="400px" src={movie.thumbnail} title="Capa"/>
        
        <a href={"/movie/"+movie.id}>Acessar</a> 
    </article>
  );

}

const ListMoviesTable = (props) => {
 
  const { movies } = props;
  
  return (<div className="container">
      
          <div  className="listafilmes">            
            {
              movies.map(movie => <MovieItem key={movie.id} movie={movie} />)
            }
          
        </div>
    </div>

  );
}

function Movies() {

  const [movies, isLoading, error, fetchMovies] = useListMovies();


  return <div className="home">
    
    
    {
        
        error ? <Error message={error} /> : 
        (isLoading ? <LoadingMessage /> : <ListMoviesTable movies={movies} /> )
    }

  </div>;

}

export default Movies;