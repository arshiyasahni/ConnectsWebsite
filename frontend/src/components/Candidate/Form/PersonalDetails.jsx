// import React from "react";
//
// const PersonalDetails = ({ formData, setFormData }) => {
//   // name: "",
//   // email: "",
//   // phone: "",
//   // linkedin: "",
//   // github: "",
//   // skills: "",
//   return (
//     <div className="container">
//       <form className="row g-3">
//         <div className="col-md-6">
//           <label for="name" className="form-label">
//             Name
//           </label>
//           <input
//             type="text"
//             className="form-control"
//             id="name"
//             value={formData.name}
//             onChange={(e) => {
//               setFormData({ ...formData, name: e.target.value });
//             }}
//           />
//         </div>
//         <div className="col-md-6">
//           <label for="email_address" className="form-label">
//             Email
//           </label>
//           <input
//             type="email"
//             className="form-control"
//             id="email_address"
//             value={formData.email_address}
//             onChange={(e) => {
//               setFormData({ ...formData, email_address: e.target.value });
//             }}
//           />
//         </div>
//         <div className="col-md-6">
//           <label for="phone_number" className="form-label">
//             Phone
//           </label>
//           <input
//             type="text"
//             className="form-control"
//             id="phone_number"
//             value={formData.phone_number}
//             onChange={(e) => {
//               setFormData({ ...formData, phone_number: e.target.value });
//             }}
//           />
//         </div>
//
//         <div className="col-12">
//           <label for="github" className="form-label">
//             Github
//           </label>
//           <input
//             type="text"
//             className="form-control"
//             id="github"
//             placeholder="https://github/YOURUSERNAME"
//             value={formData.github}
//             onChange={(e) => {
//               setFormData({ ...formData, github: e.target.value });
//             }}
//           />
//         </div>
//         <div className="col-12">
//           <label for="LinkedIn" className="form-label">
//             LinkedIn
//           </label>
//           <input
//             type="text"
//             className="form-control"
//             id="LinkedIn"
//             placeholder="https://linkedIn/YOURUSERNAME"
//             value={formData.linkedin}
//             onChange={(e) => {
//               setFormData({ ...formData, linkedin: e.target.value });
//             }}
//           />
//         </div>
//         <div className="col-12">
//           <label for="Skills" className="form-label">
//             Skills
//           </label>
//           <input
//             type="text"
//             className="form-control"
//             id="Skills"
//             placeholder="Enter skills and separate each of them with a comma "
//             value={formData.skills}
//             onChange={(e) => {
//               setFormData({ ...formData, skills: e.target.value });
//             }}
//           />
//         </div>
//       </form>
//     </div>
//   );
// };
//
// export default PersonalDetails;


import React, { useState } from "react";

const PersonalDetails = ({ formData, setFormData, errorBool, setErrorBool }) => {
//   const [formData, setFormData] = useState({
//     name: "",
//     email_address: "",
//     phone_number: "",
//     linkedin: "",
//     github: "",
//     skills: "",
//   });

  const [errors, setErrors] = useState({
    name: "",
    email_address: "",
    phone_number: "",
    linkedin: "",
    github: "",
    skills: "",
  });


  let candidateData=JSON.parse(sessionStorage.getItem("AUTH_TOKEN"));
  let email = candidateData.email_address;
  //setFormData({ ...formData, [email]: email });
  const validateEmail = (email) => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  };

  const validateURL = (url) => {
    const re = /^(ftp|http|https):\/\/[^ "]+$/;
    return re.test(url);
  };

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    if(id === "skills"){setErrorBool(true);}
    setFormData({ ...formData, [id]: value });
    // Validation
    let errorMsg = "";
    switch (id) {
      case "name":
        errorMsg = value.trim() === "" ? "Name is required" : "";
        if(errorMsg !== ""){setErrorBool(false);}
        break;
      case "email_address":
        errorMsg = !validateEmail(value) ? "Invalid email address" : "";
        if(errorMsg !== ""){setErrorBool(false);}
        break;
      case "phone_number":
        errorMsg = value.trim() === "" ? "Phone number is required" : "";
        setErrorBool(errorMsg === ""? true:false);
        break;
      case "linkedin":
        errorMsg = !validateURL(value) ? "Invalid LinkedIn URL" : "";
        setErrorBool(errorMsg === ""? true:false);
        break;
      case "github":
        errorMsg = !validateURL(value) ? "Invalid GitHub URL" : "";
        setErrorBool(errorMsg === ""? true:false);
        break;
      case "skills":
        setErrorBool(true);
      default:
        errorMsg = "";
    }

    setErrors({ ...errors, [id]: errorMsg });
  };

  return (
    <div className="container">
      <form className="row g-3">
        <div className="col-md-6">
          <label htmlFor="name" className="form-label">
            Name
          </label>
          <input
            type="text"
            className="form-control"
            id="name"
            value={formData.name}
            onChange={handleInputChange}
          />
          {errors.name && <div className="error-message">{errors.name}</div>}
        </div>
        <div className="col-md-6">
          <label htmlFor="email_address" className="form-label">
            Email
          </label>
          <input
            type="email"
            className="form-control"
            id="email_address"
            value={email}
            required={true} readOnly={true}
            //onChange={handleInputChange}
          />
          {errors.email_address && <div className="error-message">{errors.email_address}</div>}
        </div>
        <div className="col-md-6">
          <label htmlFor="phone_number" className="form-label">
            Phone
          </label>
          <input
            type="number"
            className="form-control"
            id="phone_number"
            value={formData.phone_number}
            onChange={handleInputChange}
          />
          {errors.phone_number && <div className="error-message">{errors.phone_number}</div>}
        </div>
        <div className="col-12">
          <label htmlFor="github" className="form-label">
            Github
          </label>
          <input
            type="text"
            className="form-control"
            id="github"
            placeholder="https://github/YOURUSERNAME"
            value={formData.github}
            onChange={handleInputChange}
          />
          {errors.github && <div className="error-message">{errors.github}</div>}
        </div>
        <div className="col-12">
          <label htmlFor="linkedin" className="form-label">
            LinkedIn
          </label>
          <input
            type="text"
            className="form-control"
            id="linkedin"
            placeholder="https://linkedin/YOURUSERNAME"
            value={formData.linkedin}
            onChange={handleInputChange}
          />
          {errors.linkedin && <div className="error-message">{errors.linkedin}</div>}
        </div>
        <div className="col-12">
          <label htmlFor="skills" className="form-label">
            Skills
          </label>
          <input
            type="text"
            className="form-control"
            id="skills"
            placeholder="Enter skills and separate each of them with a comma"
            value={formData.skills}
            onChange={handleInputChange}
          />
          {errors.skills && <div className="error-message">{errors.skills}</div>}
        </div>
      </form>
    </div>
  );
};

export default PersonalDetails;

