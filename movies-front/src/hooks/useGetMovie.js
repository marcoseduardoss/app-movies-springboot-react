import { useEffect, useState } from 'react';
import getMovie from '../api/getMovie';

export default (id) => {
  const [movie, setMovie] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchMovie = () => {
    setIsLoading(true);

    const onSucces = (response) => {
      setIsLoading(false);
      setMovie(response.data);
    }

    const onError = (error) => {
      console.error(error);
      setIsLoading(false);
      setError({ message: 'Unable to load the movie.' });
    }

    getMovie(id, onSucces, onError);
  };

  useEffect(() => fetchMovie(), []);

  return [movie, isLoading, error, fetchMovie];
}