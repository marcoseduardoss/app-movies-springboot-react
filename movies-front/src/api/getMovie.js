import axios from 'axios';

const getUser = (id, onSuccess, onError) => {
  axios.get('http://localhost:8080/movies/'+id)
  .then(onSuccess)
  .catch(onError);
  
};

export default getUser;

