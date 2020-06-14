import { useState } from "react";

import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({forceRefresh:true});
//history.push("/"); 

export default (callback) => {

  //const history = useHistory();    
  const [values, setValues] = useState({ });
  const [loading, setLoading] = useState(false);

  const handleChange = (event) => {
    const auxValues = { ...values };
    auxValues[event.target.name] = event.target.value;
    setValues(auxValues);
  };

  const handleSubmit = callback => event => {
    try {
      event.preventDefault();
      setLoading(true);
      let login = callback();

      if(login.username == 'admin' && login.password == '123'){
        localStorage.setItem('user', login.username);
        setLoading(false);  
      }else{
        localStorage.setItem('user', '');
        alert('Usuário inválido');
      }
      history.push("/");    
      
    } catch (e) {
      alert(e.message);
    }
  };

  return [{ values, loading }, handleChange, handleSubmit];
};

//export default useRatingForm;