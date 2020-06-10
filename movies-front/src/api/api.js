import axios from 'axios'

const api = axios.create({
  mode: 'cors',
  baseURL: 'http://localhost:8080',
})

// if we don't write the next line, our resources will receive the entire response in the data field. We don't want that.
//api.interceptors.response.use(response => response.data)

const loadMovie    = (onSuccess, onError) => api.get('/movies').then(onSuccess).catch(onError);
const listAllUsers = (onSuccess, onError) => api.get('/movies').then(onSuccess).catch(onError);

const saveMovie = data => api.put('/movies', data)

const removeMovie = id => api.delete('/movies', { id })

const loadRating = () => api.get('/rating')

//const saveRating = data => api.post('/rating', data)
const saveRating = (data, onSuccess, onError)  => api.post('/rating', data).then(onSuccess).catch(onError);

export default {
  listAllUsers,
    loadMovie,
    saveMovie,
    loadRating,
    saveRating,
    removeMovie,
}