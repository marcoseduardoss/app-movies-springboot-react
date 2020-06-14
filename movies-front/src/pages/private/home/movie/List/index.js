import React from "react";
import './list.css'
import useListMovies  from './../../../../../hooks/useListMovies';
import Api            from "./../../../../../api/api";

import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({forceRefresh:true});
//history.push("/"); 

const LoadingMessage = () => {
  return (<div height="800px">Processando dados, por favor aguarde...</div>);
}

const Error = (props) => {
  const { message } = props;
  return (<b>{message}</b>);
}

const handleAddItem = (e) => {
  history.push({
    pathname: '/movie/edit',
    state: { detail: 'some_value' }
});  
}

const handleEditItem = (e) => {
  history.push("/movie/edit");  
}

const handleRemoveItem = (e) => {
  
  const id = e.target.getAttribute("id");

  if ( window.confirm("Deseja apagar o registro com código " + id + "?" ) ){    
    try{  
    
      Api.removeMovie(id);

      history.push("/");

    } catch(e){
      alert(e);
    }
  }
 };

const MovieItem = (props) => {

  const { movie } = props;
   
  return (

    <tr >
        <td><img src={movie.thumbnail} title="Capa"/></td>
        
        <td  > {movie.title}</td>

        <td><button id={movie.id} onClick={handleRemoveItem} >Remover</button></td>
        <td><button id={'id_edit_'+movie.id} onClick={handleEditItem} >Alterar</button></td>
        
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

  const [movies, isLoading, error, fetchMovies] = useListMovies();


  return <div className="home">
    
    
    {        
        error ? <Error message={error} /> : 
        (isLoading ? <LoadingMessage /> : <ListMoviesTable movies={movies} /> )
    }

  </div>;

}


export default MoviesListPrivateAccess;