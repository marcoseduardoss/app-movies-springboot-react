import { useState } from "react";
import Api from "./../api/api";

export default (callback) => {
    
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
    let rating = callback();
    Api.saveRating(rating);
    setLoading(false);
  };

  return [{ values, loading }, handleChange, handleSubmit];
};

//export default useRatingForm;