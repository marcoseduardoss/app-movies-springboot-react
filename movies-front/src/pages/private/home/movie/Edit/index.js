import React, { useState } from "react";
import "./movieEdit.css";
import Api from "./../../../../../api/api";
import useForm from "../../../../../hooks/useForm";
import useGetMovie from "../../../../../hooks/useGetMovie";
import ImageUpload from "../../../../../components/ImageUpload";
import createBrowserHistory from 'history/createBrowserHistory';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';
import ReactDOM from 'react-dom'; 

const history = createBrowserHistory({ forceRefresh: true });
//history.push("/"); 

const MovieEdit = (props) => {
  

  const [movieIn, isLoading, error, fetchMovie] = useGetMovie(props.id);

  const [title, setTitle] = useState('');
  const [synopsis, setSynopsis] = useState('');
  const [protagonists, setProtagonists] = useState('');
  const [producer, setProducer] = useState('');
  const [year, setYear] = useState('');
  const [thumbnail, setThumbnail] = useState('');

   const saveOrUpdate = () => {

    

    if (props.id != null)
      update();
    else
      save();

  }

  const update = () => {
    try {

      
      let movie = {
        "title": title, "synopsis": synopsis, "protagonists": protagonists,
        "producer": producer, "year": year, "thumbnail": thumbnail
      }

      let saved = Api.updateMovie(props.id, movie);

      alert('Edição realizada com sucesso!')

      history.push("/");

    } catch (e) {
      alert(e.message);
    }
  }

  const save = () => {
    try {

      let movie = {
        "title": title, "synopsis": synopsis, "protagonists": protagonists,
        "producer": producer, "year": year, "thumbnail": thumbnail
      }

      let saved = Api.saveMovie(movie);

      alert('Objeto incluído com sucesso!')

      history.push("/");

    } catch (e) {
      alert(e.message);
    }
  };

  
  return (
    <div align="center">

      
      <form onSubmit={saveOrUpdate}  >

  
          <div align="left" style={{padding: "50px"}}> 

          {(props.id != null) ? 'ID: ' + props.id : ''}

          <br /> <br />

          <InputText name="title"
            type="text"
            size={30}
            defaultValue={movieIn.title}
            placeholder="Título"
            onChange={(e) => setTitle(e.target.value)} />

          <br /><br />

          <InputTextarea name="synopsis"
            rows={5} cols={80}
            autoResize={true}
            defaultValue={movieIn.synopsis}
            placeholder="Sinopse do filme"
            onChange={(e) => setSynopsis(e.target.value)}></InputTextarea>

          <br /><br />

          <InputText name="Protagonistas"
            type="text"
            size={30}
            defaultValue={movieIn.protagonists}
            placeholder="Protagonistas"
            onChange={(e) => setProtagonists(e.target.value)} />

          <br /><br />


          <InputText name="Thumbnail"
            type="text"
            size={30}
            defaultValue={movieIn.thumbnail}
            placeholder="URL da imagem"
            onChange={(e) => setThumbnail(e.target.value)} />

          <br /><br />
          

          <InputText name="producer"
            type="text"
            size={30}
            defaultValue={movieIn.producer}
            placeholder="Produtor"
            onChange={(e) => setProducer(e.target.value)} />

          <br /><br />

          <InputText name="year"
            type="text"
            size={30}
            defaultValue={movieIn.year}
            placeholder="Ano"
            onChange={(e) => setYear(e.target.value)} />


          <br /><br />

          
          <button type="submit">Salvar</button>
          
          <br /><br />

        </div>

      </form>
    </div>
  );
};

export default MovieEdit;