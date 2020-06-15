import React, { useState } from "react";
import axios from 'axios'

export default (props) => {

    const [image, setImage] = useState(props.image);//{ preview: "", raw: "" }

    //const image = props.image;

    const handleChange = e => {
        if (e.target.files.length) {


            setImage({
                preview: URL.createObjectURL(e.target.files[0]),
                raw: e.target.files[0]
            });

            const formData = new FormData();
            formData.append("file", image.raw);

            axios.post("http://localhost:8080/movies/upload", formData, {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            }).then(response => {
                localStorage.setItem('image', response.data);
            });

            /* fetch("http://localhost:8080/movies/upload", {
               method: "POST",
               headers: {
                 "Content-Type": "multipart/form-data"
               },
               body: formData
             }).then(function(response) {
                 image.fileb64 = response.data;
             });*/


        }
    };


    /*const handleUpload = async e => {
      e.preventDefault();
      const formData = new FormData();
      formData.append("image", image.raw);
  
      await fetch("YOUR_URL", {
        method: "POST",
        headers: {
          "Content-Type": "multipart/form-data"
        },
        body: formData
      });
    };*/

    return (

        <div>

            <label htmlFor="upload-button">

                {image.preview ? (
                    <img src={image.preview} alt="dummy" width="300" height="300" />
                ) : (
                        <span style={{ cursor: "pointer" }}>
                            <span className="fa-stack fa-2x mt-3 mb-2">
                                <i className="fas fa-circle fa-stack-2x" />
                                <i className="fas fa-store fa-stack-1x fa-inverse" />
                            </span>
                            <h5 className="text-center">Upload de Imagem PNG</h5>
                        </span>
                    )}
            </label>

            <input
                type="file"
                id="upload-button"
                style={{ display: "none" }}
                onChange={handleChange}
            />

            <br />

        </div>
    );
}
