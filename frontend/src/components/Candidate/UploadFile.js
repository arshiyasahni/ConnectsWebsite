import React, {useState} from 'react';
import axios from 'axios';
import Success from "./Success";

function UploadFile() {

  const [file, setFile] = useState()
  const [success, setSuccess] = useState(false);
  function handleChange(event) {
    setFile(event.target.files[0])
  }

  function handleSubmit(event) {
    event.preventDefault()
    const url = 'http://localhost:8080/v1/upload_resume';
    const formData = new FormData();
    formData.append('uploaded_resume', file);
    let candidateData=JSON.parse(sessionStorage.getItem("AUTH_TOKEN"));
    let email = candidateData.email_address;

    formData.append('email_address', email);

    const config = {
      headers: {
        'content-type': 'multipart/form-data',
      },
    };
    axios.post(url, formData, config).then((response) => {
      console.log(response.data);
      setSuccess(true );
    });

  }

  return (
    <div className="App">
        <form onSubmit={handleSubmit}>
          <h1>Upload Your Resume</h1>
          <input type="file" onChange={handleChange}/>
          <button type="submit">Upload</button>
        </form>
        {success && <Success />}
    </div>

  );
}

export default UploadFile;