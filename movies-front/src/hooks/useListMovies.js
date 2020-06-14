import { useEffect, useState } from 'react';
//import listAllMovies from '../api/listAllMovies';
import Api from "../api/api";

export default () => {
  const [movies, setMovies] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchMovies = () => {
    setIsLoading(true);

    const onSucces = (response) => {
      setIsLoading(false);
      setMovies(response.data);
    }

    const onError = (error) => {
      console.error(error);
      setIsLoading(false);
      setError('Unable to load all movies.');
    }

    Api.listAllMovies(onSucces, onError);
    //listAllMovies(onSucces, onError);
    
  };

  useEffect(() => fetchMovies(), []);

  return [movies, isLoading, error, fetchMovies];
}