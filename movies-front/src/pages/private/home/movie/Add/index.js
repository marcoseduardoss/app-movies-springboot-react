import React, { useState } from "react";
import "./movieAdd.css";
import Api from "./../../../../../api/api";
import createBrowserHistory from 'history/createBrowserHistory';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';

const history = createBrowserHistory({ forceRefresh: true });
//history.push("/"); 

const MovieEdit = (props) => {
  

  const [title, setTitle] = useState('');
  const [synopsis, setSynopsis] = useState('');
  const [protagonists, setProtagonists] = useState('');
  const [producer, setProducer] = useState('');
  const [year, setYear] = useState('');
  const [thumbnail, setThumbnail] = useState('');


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

      
      <form onSubmit={save}  >
      <h1>Cadastro de Filme</h1>
  
          <div align="left" style={{paddingLeft: "50px"}}> 

       

          <InputText name="title"
            type="text"
            size={30}
            value={title}
            placeholder="Título"
            onChange={(e) => setTitle(e.target.value)} />

          <br /><br />

          <InputTextarea name="synopsis"
            rows={5} cols={80}
            autoResize={true}
            value={synopsis}
            placeholder="Sinopse do filme"
            onChange={(e) => setSynopsis(e.target.value)}></InputTextarea>

          <br /><br />

          <InputText name="Protagonistas"
            type="text"
            size={30}
            value={protagonists}
            placeholder="Protagonistas"
            onChange={(e) => setProtagonists(e.target.value)} />

          <br /><br />


          <InputText name="Thumbnail"
            type="text"
            size={30}
            value={thumbnail}
            placeholder="URL da imagem"
            onChange={(e) => setThumbnail(e.target.value)} />

          <br /><br />
          

          <InputText name="producer"
            type="text"
            size={30}
            value={producer}
            placeholder="Produtor"
            onChange={(e) => setProducer(e.target.value)} />

          <br /><br />

          <InputText name="year"
            type="text"
            size={30}
            value={year}
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