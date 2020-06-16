import React, { useState } from "react";
import Api from "../../../../../api/api";
import "./rating.css";

import { Rating } from 'primereact/rating';
import { InputTextarea } from 'primereact/inputtextarea';

import { InputText } from 'primereact/inputtext';
import { TabView, TabPanel } from 'primereact/tabview';
import { Button } from 'primereact/button';

import 'primeicons/primeicons.css';
import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.css';
import 'primeflex/primeflex.css';

import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({ forceRefresh: true });
//history.push("/"); 


export default (props) => {
  
  const [score, setScore] = useState(0);
  const [comment, setComment] = useState("");
  const [author, setAuthor] = useState("");

  function register(e){
    e.preventDefault();

    let rating = {
      "idMovie": props.id,
      "score": score,
      "author": author,
      "comment": comment
    };
 
    Api.saveRating(rating);
    
    alert('Operacao realizada com suscesso!')

    history.push("/");
    
  };

  return (
    <div >
      <form onSubmit={register}>
        <TabView renderActiveOnly={false}>
          <TabPanel header="Avaliação" leftIcon="pi pi-user" className="tab-panel-color">
            <div className="p-fluid p-formgrid p-grid">
              <div className="p-field p-col-12 p-md-6">
              <InputText id="float-input" name="author"
                  type="text"
                  size={30}
                  required={true}
                  value={author}
                  placeholder="Autor"
                  onChange={(e) => setAuthor(e.target.value)} />
              </div>
              <div className="p-field p-col-12 p-md-6" >
              
              <Rating name="score" value={score} cancel={false} className="input-rating"
                  onChange={(e) => setScore(e.value)} />
               
              </div>

              <div className="p-field p-col-12">
                <InputTextarea value={comment} name="comment"
                  rows={5} cols={80}
                  autoResize={true}                  
                  placeholder="Comente o filme aqui"
                  onChange={(e) => setComment(e.target.value)}></InputTextarea>
              </div>
            </div>

            <Button type="submit" className="p-button-success" label="Incluir" icon="pi pi-check" />
          
          </TabPanel>
          <TabPanel header="Informações Adicionais" rightIcon="pi pi-info-circle" disabled={true}>
            <p>todo: incluir informações adicionais aqui</p>
          </TabPanel>
          <TabPanel header="Histórico de Avaliações" rightIcon="pi pi-comment" disabled={true}>
            <p>todo: incluir mensagens postadas aqui</p>
          </TabPanel>

        </TabView>

      </form>
    </div>
  );
};