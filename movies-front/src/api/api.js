import axios from 'axios'

const api = axios.create({
  mode: 'cors',
  baseURL: 'http://localhost:8080',
})

// if we don't write the next line, our resources will receive the entire response in the data field. We don't want that.
//api.interceptors.response.use(response => response.data)

//Api user
const authenticate   = (onSuccess, onError) => api.post('/users/authenticate').then(onSuccess).catch(onError);

const getMovie      = (id) => api.get('/movies/'+id);
const listAllMovies = (onSuccess, onError) => api.get('/movies').then(onSuccess).catch(onError);
const updateMovie   = (id, data) => {return api.put('/movies/'+id, data)}
const saveMovie     = data => api.post('/movies', data)//CREATE
const removeMovie   = (id,onSuccess, onError) => api.delete('/movies/'+id).then(onSuccess).catch(onError);
const saveRating    = (data, onSuccess, onError)  => api.post('/rating', data).then(onSuccess).catch(onError);

export default {
    listAllMovies,
    getMovie,
    saveMovie,
    authenticate,
    saveRating,
    removeMovie,
    updateMovie
}