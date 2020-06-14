import axios from 'axios'

const api = axios.create({
  mode: 'cors',
  baseURL: 'http://localhost:8080',
})

// if we don't write the next line, our resources will receive the entire response in the data field. We don't want that.
//api.interceptors.response.use(response => response.data)

//Api user
const authenticate   = (onSuccess, onError) => api.post('/users/authenticate').then(onSuccess).catch(onError);

const saveMovie = data => api.post('/movies', data)//CREATE
//READ
const loadMovie    = (onSuccess, onError) => api.get('/movies').then(onSuccess).catch(onError);
const listAllMovies = (onSuccess, onError) => api.get('/movies').then(onSuccess).catch(onError);
//UPDATE
const updateMovie = data => api.put('/movies', data)
//DELETE
const removeMovie = (id,onSuccess, onError) => api.delete('/movies/'+id).then(onSuccess).catch(onError);
//Api rating
const saveRating = (data, onSuccess, onError)  => api.post('/rating', data).then(onSuccess).catch(onError);

export default {
    listAllMovies,
    loadMovie,
    saveMovie,
    authenticate,
    saveRating,
    removeMovie,
    updateMovie
}