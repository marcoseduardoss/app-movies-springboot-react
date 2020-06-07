import axios from 'axios';


const listAllUsers = (onSuccess, onError) => {
  axios.get('http://localhost:8080/movies')
  .then(onSuccess)
  .catch(onError);
  
};

export default listAllUsers;

