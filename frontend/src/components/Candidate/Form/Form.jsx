import React, { useState, useEffect  , useRef } from "react";
import Education from "./Education";
import Experiences from "./Experiences";
import PersonalDetails from "./PersonalDetails";
import Project from "./Project";
import Extras from "./Extras";
import axios from "axios";
import { saveAs } from "file-saver";
import Success from "./../Success";
import apiList from "../../../config/constant";

const Form = () => {
  const [success, setSuccess] = useState(false);
  const [formData, setFormData] = useState({
    name: "",
    email_address: "",
    phone_number: "",
    linkedin: "",
    github: "",
    skills: "",

    exp1_org: "",
    exp1_pos: "",
    exp1_desc: "",
    exp1_dur: "",
    exp2_org: "",
    exp2_pos: "",
    exp2_des: "",
    exp2_dur: "",

    proj1_title: "",
    proj1_link: "",
    proj1_desc: "",
    proj2_title: "",
    proj2_link: "",
    proj2_desc: "",

    edu1_school: "",
    edu1_year: "",
    edu1_qualification: "",
    edu1_desc: "",
    edu2_school: "",
    edu2_year: "",
    edu2_qualification: "",
    edu2_desc: "",

    extra_1: "",
    extra_2: "",
  });

  const [page, setPage] = useState(0);
  const componentRef = useRef();

//   const handlePrint = useReactToPrint({
//       content: () => componentRef,
//     });
  const FormTitle = [
    "Personal Details",
    "Education",
    "Experience",
    "Projects",
    "Extras",
  ];
  const [errorBool, setErrorBool] = useState(false);

  const PageDisplay = () => {
    if (page === 0) {
      return <PersonalDetails formData={formData} setFormData={setFormData} errorBool={errorBool} setErrorBool={setErrorBool} />;
    } else if (page === 1) {
      return <Education formData={formData} setFormData={setFormData} />;
    } else if (page === 2) {
      return <Experiences formData={formData} setFormData={setFormData} />;
    } else if (page === 3) {
      return <Project formData={formData} setFormData={setFormData} />;
    } else {
      return <Extras formData={formData} setFormData={setFormData} />;
    }
  };

  return (
    <div>
      <div className="d-flex justify-content-center">
        <h1 className="text-center">{FormTitle[page]}</h1>
      </div>
      <div className="progressbar">
        <div
          style={{
            width:
              page === 0
                ? "20%"
                : page === 1
                ? "40%"
                : page === 2
                ? "60%"
                : page === 3
                ? "80%"
                : "100%",
          }}
        ></div>
      </div>
      <div ref={componentRef}>{PageDisplay()}</div>
      <div className="d-flex justify-content-center gap-3 py-5">
        <button
          className="btn btn-dark"
          disabled={page === 0}
          onClick={() => {
            setPage((currPage) => currPage - 1);
          }}
        >
          Prev
        </button>

        <button
          className="btn btn-primary"
          disabled={page === 0 && errorBool===false}
          onClick={() => {
            let candidateData=JSON.parse(sessionStorage.getItem("AUTH_TOKEN"));
            let email = candidateData.email_address;
            setFormData({ ...formData, email_address: email })
            console.log(formData);
            if (page === FormTitle.length - 1) {
              axios
                .post(apiList.build_resume, formData)
//                 .then(() =>
//                   axios.get("http://localhost:8080/fetch-pdf", {
//                     responseType: "blob",
//                   })
//                 )
                .then((res) => {
                  console.log(res);
                  const pdfBlob = new Blob([res.data], {
                    type: "application/pdf",
                  });
                  setSuccess(true );
                  //saveAs(pdfBlob, "Resume.pdf");
                 });
            } else {
              setPage((currPage) => currPage + 1);
            }
          }}
        >
          {page === FormTitle.length - 1 ? " Submit " : "Next"}
        </button>
      </div>
      {success && <Success />}
    </div>
  );
};

export default Form;