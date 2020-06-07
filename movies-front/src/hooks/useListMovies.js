import { useEffect, useState } from 'react';
import listAllMovies from '../api/listAllMovies';

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
      setError({ message: 'Unable to load all movies.' });
    }

    listAllMovies(onSucces, onError);
  };

  useEffect(() => fetchMovies(), []);

  return [movies, isLoading, error, fetchMovies];
}