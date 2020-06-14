
import React from "react";
import './list.css'
import useListMovies from './../../../../../hooks/useListMovies';

const LoadingMessage = () => {
  return (<div height="800px">Processando dados, por favor aguarde...</div>);
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

function MoviesListPublicAccess() {

  const [movies, isLoading, error, fetchMovies] = useListMovies();


  return <div className="home">
    
    
    {        
        error ? <Error message={error} /> : 
        (isLoading ? <LoadingMessage /> : <ListMoviesTable movies={movies} /> )
    }

  </div>;

}

export default MoviesListPublicAccess;