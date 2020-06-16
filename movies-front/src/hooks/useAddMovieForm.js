import { useState,useEffect } from "react";
import Api from "./../api/api";

export default (init, callback) => {
    
  const [values, setValues] = useState({ });
  const [loading, setLoading] = useState(false);

  const handleChange = (event) => {
    const auxValues = { ...values };
    auxValues[event.target.name] = event.target.value;
    setValues(auxValues);
  };

  const handleSubmit = callback => event => {
    event.preventDefault();
    setLoading(true);
    callback();
    
  };

  const _fetchMovie = () => {
    setLoading(false);
  }

  useEffect(() => _fetchMovie(), []);
  
  return [{ values, loading }, handleChange, handleSubmit, _fetchMovie];
};

//export default useRatingForm;