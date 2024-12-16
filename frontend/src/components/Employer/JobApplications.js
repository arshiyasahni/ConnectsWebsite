import { useState, useEffect, useContext } from "react";
import {
  Button,
  Chip,
  Grid,
  IconButton,
  InputAdornment,
  makeStyles,
  Paper,
  TextField,
  Typography,
  Modal,
  Slider,
  FormControlLabel,
  FormGroup,
  MenuItem,
  Checkbox,
  Avatar,
} from "@material-ui/core";
import { useParams } from "react-router-dom";
//import Rating from "@material-ui/lab/Rating";
import axios from "axios";
import FilterListIcon from "@material-ui/icons/FilterList";
import ArrowUpwardIcon from "@material-ui/icons/ArrowUpward";
import ArrowDownwardIcon from "@material-ui/icons/ArrowDownward";
import ResumePDF from "./ResumePDF";
//import { SetPopupContext } from "../../App";
import { saveAs } from "file-saver";

import apiList from "../../config/constant";

const useStyles = makeStyles((theme) => ({
  // Define the styles for the component using makeStyles hook
  body: {
    height: "inherit",
  },
  statusBlock: {
    width: "100%",
    height: "100%",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    textTransform: "uppercase",
  },
  jobTileOuter: {
    padding: "30px",
    margin: "20px 0",
    boxSizing: "border-box",
    width: "100%",
  },
  popupDialog: {
    height: "100%",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },
  avatar: {
    width: theme.spacing(17),
    height: theme.spacing(17),
  },
}));

//const FilterPopup = (props) => {
//  const classes = useStyles();
//  const { open, handleClose, searchOptions, setSearchOptions, getData } = props;
//  return (
//    <Modal open={open} onClose={handleClose} className={classes.popupDialog}>
//      <Paper
//        style={{
//          padding: "50px",
//          outline: "none",
//          minWidth: "50%",
//        }}
//      >
//        <Grid container direction="column" alignItems="center" spacing={3}>
//          <Grid container item alignItems="center">
//            <Grid item xs={3}>
//              Application Status
//            </Grid>
//            <Grid
//              container
//              item
//              xs={9}
//              justify="space-around"
//            // alignItems="center"
//            >
//              <Grid item>
//                <FormControlLabel
//                  control={
//                    <Checkbox
//                      name="rejected"
//                      checked={searchOptions.status.rejected}
//                      onChange={(event) => {
//                        setSearchOptions({
//                          ...searchOptions,
//                          status: {
//                            ...searchOptions.status,
//                            [event.target.name]: event.target.checked,
//                          },
//                        });
//                      }}
//                    />
//                  }
//                  label="Rejected"
//                />
//              </Grid>
//              <Grid item>
//                <FormControlLabel
//                  control={
//                    <Checkbox
//                      name="applied"
//                      checked={searchOptions.status.applied}
//                      onChange={(event) => {
//                        setSearchOptions({
//                          ...searchOptions,
//                          status: {
//                            ...searchOptions.status,
//                            [event.target.name]: event.target.checked,
//                          },
//                        });
//                      }}
//                    />
//                  }
//                  label="Applied"
//                />
//              </Grid>
//              <Grid item>
//                <FormControlLabel
//                  control={
//                    <Checkbox
//                      name="shortlisted"
//                      checked={searchOptions.status.shortlisted}
//                      onChange={(event) => {
//                        setSearchOptions({
//                          ...searchOptions,
//                          status: {
//                            ...searchOptions.status,
//                            [event.target.name]: event.target.checked,
//                          },
//                        });
//                      }}
//                    />
//                  }
//                  label="Shortlisted"
//                />
//              </Grid>
//            </Grid>
//          </Grid>
//          <Grid container item alignItems="center">
//            <Grid item xs={3}>
//              Sort
//            </Grid>
//            <Grid item container direction="row" xs={9}>
//              <Grid
//                item
//                container
//                xs={4}
//                justify="space-around"
//                alignItems="center"
//                style={{ border: "1px solid #D1D1D1", borderRadius: "5px" }}
//              >
//                <Grid item>
//                  <Checkbox
//                    name="name"
//                    checked={searchOptions.sort["jobApplicant.name"].status}
//                    onChange={(event) =>
//                      setSearchOptions({
//                        ...searchOptions,
//                        sort: {
//                          ...searchOptions.sort,
//                          "jobApplicant.name": {
//                            ...searchOptions.sort["jobApplicant.name"],
//                            status: event.target.checked,
//                          },
//                        },
//                      })
//                    }
//                    id="email_address"
//                  />
//                </Grid>
//                <Grid item>
//                  <label for="email_address">
//                    <Typography>Name</Typography>
//                  </label>
//                </Grid>
//                <Grid item>
//                  <IconButton
//                    disabled={!searchOptions.sort["jobApplicant"].application_status}
//                    onClick={() => {
//                      setSearchOptions({
//                        ...searchOptions,
//                        sort: {
//                          ...searchOptions.sort,
//                          "jobApplicant.email_address": {
//                            ...searchOptions.sort["jobApplicant.email_address"],
//                            desc: !searchOptions.sort["jobApplicant.email_address"].desc,
//                          },
//                        },
//                      });
//                    }}
//                  >
//                    {searchOptions.sort["jobApplicant.name"].desc ? (
//                      <ArrowDownwardIcon />
//                    ) : (
//                      <ArrowUpwardIcon />
//                    )}
//                  </IconButton>
//                </Grid>
//              </Grid>
//
//              <Grid
//                item
//                container
//                xs={4}
//                justify="space-around"
//                alignItems="center"
//                style={{ border: "1px solid #D1D1D1", borderRadius: "5px" }}
//              >
//
//              </Grid>
//            </Grid>
//          </Grid>
//
//          <Grid item>
//            <Button
//              variant="contained"
//              color="primary"
//              style={{ padding: "10px 50px" }}
//              onClick={() => getData()}
//            >
//              Apply
//            </Button>
//          </Grid>
//        </Grid>
//      </Paper>
//    </Modal>
//  );
//};

const ApplicationTile = (props) => {
  const classes = useStyles();
  const { application, getData } = props;
  //const setPopup = useContext(SetPopupContext);
  const [open, setOpen] = useState(false);
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


  //const appliedOn = new Date(application.dateOfApplication);

  const handleClose = () => {
    setOpen(false);
  };

  const colorSet = {
    applied: "#3454D1",
    shortlisted: "#DC851F",
    accepted: "#09BC8A",
    rejected: "#D1345B",
    deleted: "#B49A67",
    cancelled: "#FF8484",
    finished: "#4EA5D9",
  };

  const handlePrint = (formData) => {
      // You can use the existing ref to get the resume information and generate the PDF blob
      const resumePDF = (
        <ResumePDF formData={formData} />
      );

      // Create the PDF blob using the react-to-pdf library
      const pdfBlob = new Blob([resumePDF], { type: "application/pdf" });
//      const fileURL = URL.createObjectURL(pdfBlob);
//      window.open(fileURL);
      // Save the PDF blob as a file
      saveAs(pdfBlob, "Resume.pdf");
    };

  const getBuiltResume = () => {
    let address = `${apiList.get_built_resume}?email_address=${application.email_address}`;
    axios.GET(address).then((res) =>{
          console.log(application.email_address);
//          if(res.data.errors){
//          return alert(res.data.errors[0]);
//          }
//          else{
          setFormData(res);
          console.log("Form Data res "+ res);
          handlePrint(res);
          //}
          })
    };

  const getResume = () => {
    let build = false;
    if (!build) {
      //const address = `${server}${application.jobApplicant.resume}`;
      const address = `${apiList.get_uploaded_resume}?email_address=${application.email_address}`;
      console.log(address);
      axios(address, {
        method: "GET",
        responseType: "blob",
      })
        .then((response) => {
          console.log(response.data.size);
          if(response.data.size !== 0){
               const file = new Blob([response.data], { type: "application/pdf" });
               const fileURL = URL.createObjectURL(file);
               window.open(fileURL);
          }
          else{
          console.log("Go to build resume");
          getBuiltResume();
          build = true;
          }
        })
    }
  };

  let employerData=JSON.parse(sessionStorage.getItem("AUTH_TOKEN"));
  let emp_email = employerData.email_address;

  const updateStatus = (status) => {
    console.log(application);
    let address = "";
    if(status === "rejected"){
       address = `${apiList.rejected}`;
    }
    else if(status === "accepted"){
       address = `${apiList.accepted}`;
    }
    else if(status === "shortlisted"){
        address = `${apiList.interview}`;
    }
    console.log(address);
    let updateStatusData = {job_id: application.job_id,
                emailAddress: emp_email ,
                employerEmail: application.email_address};
    console.log(updateStatusData);
    axios
      .post(address, updateStatusData)
      .then((response) => {
        console.log(response);
        getData();
      })
      .catch((err) => {
        console.log(err.response);
      });
  };


  const buttonSet = {
    APPLIED_BY_CANDIDATE: (
      <>
         <Grid item xs>
                  <Button
                    className={classes.statusBlock}
                    style={{
                      background: colorSet["accepted"],
                      color: "#ffffff",
                    }}
                    onClick={() => updateStatus("accepted")}
                  >
                    Accept
        </Button>
        </Grid>
        <Grid item xs>
          <Button
            className={classes.statusBlock}
            style={{
              background: colorSet["shortlisted"],
              color: "#ffffff",
            }}
            onClick={() => updateStatus("shortlisted")}
          >
            Shortlist
          </Button>
        </Grid>
        <Grid item xs>
          <Button
            className={classes.statusBlock}
            style={{
              background: colorSet["rejected"],
              color: "#ffffff",
            }}
            onClick={() => updateStatus("rejected")}
          >
            Reject
          </Button>
        </Grid>
      </>
    ),
    INTERVIEW_WITH_EMPLOYER: (
      <>
        <Grid item xs>
          <Button
            className={classes.statusBlock}
            style={{
              background: colorSet["accepted"],
              color: "#ffffff",
            }}
            onClick={() => updateStatus("accepted")}
          >
            Accept
          </Button>
        </Grid>
        <Grid item xs>
          <Button
            className={classes.statusBlock}
            style={{
              background: colorSet["rejected"],
              color: "#ffffff",
            }}
            onClick={() => updateStatus("rejected")}
          >
            Reject
          </Button>
        </Grid>
      </>
    ),
    REJECTED_BY_EMPLOYER: (
      <>
        <Grid item xs>
          <Paper
            className={classes.statusBlock}
            style={{
              background: colorSet["rejected"],
              color: "#ffffff",
            }}
          >
            Rejected
          </Paper>
        </Grid>
      </>
    ),
    ACCEPTED_BY_EMPLOYER: (
      <>
        <Grid item xs>
          <Paper
            className={classes.statusBlock}
            style={{
              background: colorSet["accepted"],
              color: "#ffffff",
            }}
          >
            Accepted
          </Paper>
        </Grid>
      </>
    ),

  };

  return (
    <Paper className={classes.jobTileOuter} elevation={3}>
      <Grid container>
        <Grid
          item
          xs={2}
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
             maxHeight: "10 px", width: "10 px"
          }}
        >

        </Grid>
        <Grid container item xs={7} spacing={1} direction="column">
          <Grid item>
            <Typography variant="h5">
              {application.email_address}
            </Typography>
          </Grid>


        </Grid>
        <Grid item container direction="column" xs={3}>
          <Grid item>
            <Button
              variant="contained"
              className={classes.statusBlock}
              color="primary"
              onClick={() => getResume()}
            >
              View Resume
            </Button>
          </Grid>
          <Grid item container xs>
            {buttonSet[application.application_status]}
          </Grid>
        </Grid>
      </Grid>
      <Modal open={open} onClose={handleClose} className={classes.popupDialog}>
        <Paper
          style={{
            padding: "20px",
            outline: "none",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            minWidth: "30%",
            alignItems: "center",
          }}
        >
          <Button
            variant="contained"
            color="primary"
            style={{ padding: "10px 50px" }}
          // onClick={() => changeRating()}
          >
            Submit
          </Button>
        </Paper>
      </Modal>
    </Paper>
  );
};

const JobApplications = (props) => {
  //const setPopup = useContext(SetPopupContext);
  const [applications, setApplications] = useState([]);
  //const { job, getJob } = props;
  const { jobId } = props;
//  const [filterOpen, setFilterOpen] = useState(false);
//  const [searchOptions, setSearchOptions] = useState({
//    status: {
//      all: false,
//      applied: false,
//      shortlisted: false,
//    },
//    sort: {
////      "jobApplicant.name": {
////        status: false,
////        desc: false,
////      }
//    },
//  });

  useEffect(() => {
    // Fetch data from the API when the component mounts
    getData();
  }, []);

  const getData = () => {
    let searchParams = [];

//    if (searchOptions.status.rejected) {
//      searchParams = [...searchParams, `status=rejected`];
//    }
//    if (searchOptions.status.applied) {
//      searchParams = [...searchParams, `status=applied`];
//    }
//    if (searchOptions.status.shortlisted) {
//      searchParams = [...searchParams, `status=shortlisted`];
//    }

    let asc = [],
      desc = [];

//    Object.keys(searchOptions.sort).forEach((obj) => {
//      const item = searchOptions.sort[obj];
//      if (item.status) {
//        if (item.desc) {
//          desc = [...desc, `desc=${obj}`];
//        } else {
//          asc = [...asc, `asc=${obj}`];
//        }
//      }
//    });
//    searchParams = [...searchParams, ...asc, ...desc];
//    const queryString = searchParams.join("&");
//    console.log(queryString);
    let address = `${apiList.applicants}?job_id=${jobId}`;
    console.log(address);
//    if (queryString !== "") {
//      //address = `${address}&${queryString}`;
//    }

    console.log(address);

    axios
      .get(address, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .then((response) => {
        console.log(response.data);
        setApplications(response.data);
      })
      .catch((err) => {
        console.log(err.response);
        // console.log(err.response.data);
        setApplications([]);
        //        setPopup({
        //          open: true,
        //          severity: "error",
        //          message: err.response.data.message,
        //        });
      });
  };

  return (
    <>
      <Grid
        container
        item
        direction="column"
        alignItems="center"
        style={{ padding: "30px", minHeight: "93vh" }}
      >
        <Grid item>
          <Typography variant="h2">Applications</Typography>
        </Grid>
        <Grid item>

        </Grid>
        <Grid
          container
          item
          xs
          direction="column"
          style={{ width: "100%" }}
          alignItems="stretch"
          justify="center"
        >
          {applications.length > 0 ? (
            applications.map((obj) => (
              <Grid item>
                {/* {console.log(obj)} */}
                <ApplicationTile application={obj} getData={getData} />
              </Grid>
            ))
          ) : (
            <Typography variant="h5" style={{ textAlign: "center" }}>
              No Applications Found
            </Typography>
          )}
        </Grid>
      </Grid>

    </>
  );
};

export default JobApplications;