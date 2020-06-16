import axios from 'axios';

const getUser = async  (id, onSuccess, onError) => {
  await axios.get('http://localhost:8080/movies/'+id)
  .then(onSuccess)
  .catch(onError);
  
};

export default getUser;

