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
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
} from "@material-ui/core";
import { useHistory } from "react-router-dom";
//import Rating from "@material-ui/lab/Rating";
//import Pagination from "@material-ui/lab/Pagination";
import axios from "axios";
import SearchIcon from "@material-ui/icons/Search";
import FilterListIcon from "@material-ui/icons/FilterList";
import ArrowUpwardIcon from "@material-ui/icons/ArrowUpward";
import ArrowDownwardIcon from "@material-ui/icons/ArrowDownward";
import JobApplications from "./JobApplications";
//import { SetPopupContext } from "../../App";

import apiList from "../../config/constant";

const useStyles = makeStyles((theme) => ({
  // Define the styles for the component using makeStyles hook
  body: {
    height: "inherit",
  },
  button: {
    width: "100%",
    height: "100%",
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
  statusBlock: {
    width: "100%",
    height: "100%",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    textTransform: "uppercase",
  },
}));

const JobTile = (props) => {
  const classes = useStyles();
  let history = useHistory();
  const { job, getData } = props;
  //const setPopup = useContext(SetPopupContext);

  const [open, setOpen] = useState(false);
  const [openUpdate, setOpenUpdate] = useState(false);
  const [jobDetails, setJobDetails] = useState(job);

  console.log(jobDetails);

  const handleInput = (key, value) => {
    setJobDetails({
      ...jobDetails,
      [key]: value,
    });
  };

  const handleClick = (location) => {
    history.push(location);
  };

  const handleViewApplications = () => {
      if (props.onViewApplications) {
        props.onViewApplications(job);
      }
    };

  const handleClose = () => {
    setOpen(false);
  };

  const handleCloseUpdate = () => {
    setOpenUpdate(false);
  };

  const handleDelete = () => {
    console.log(job.id);
    axios
      .delete(`${apiList.delete_job}?job_id=${job.id}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .catch((err) => {
        console.log(err.response);
        handleClose();
      });
    props.onDelete(true)
    handleClose();
  };

  const handleJobUpdate = () => {
    axios
      .put(`${apiList.jobs}/${job._id}`, jobDetails, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .catch((err) => {
        console.log(err.response);
      });
  };

  const postedOn = new Date(job.posted_at);

  return (
    <Paper className={classes.jobTileOuter} elevation={3}>
      <Grid container>
        <Grid container item xs={9} spacing={1} direction="column">
          <Grid item>
            <Typography variant="h5">{job.role}</Typography>
          </Grid>
          <Grid item>Position : {job.position}</Grid>
          <Grid item>Salary :  {job.salary === 0 | job.salary === null? "Not Available": "$ " +job.salary+ " per month"} </Grid>
          <Grid item>Date Of Posting: {postedOn.toLocaleDateString()}</Grid>

          <Grid item>
            {job.tools.map((tools) => (
              <Chip label={tools} style={{ marginRight: "2px" }} />
            ))}
          </Grid>
        </Grid>
        <Grid item container direction="column" xs={3}>
          <Grid item xs>
            <Button
              variant="contained"
              color="primary"
              className={classes.statusBlock}
              //onClick={() => handleClick(`/JobApplications/${job.id}`)}
              onClick={() => handleViewApplications()}
            >
              View Applications
            </Button>
          </Grid>
          <Grid item>
            <Button
              variant="contained"
              color="secondary"
              className={classes.statusBlock}
              onClick={() => {
                setOpen(true);
              }}
            >
              Delete Job
            </Button>
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
          <Typography variant="h4" style={{ marginBottom: "10px" }}>
            Are you sure?
          </Typography>
          <Grid container justify="center" spacing={5}>
            <Grid item>
              <Button
                variant="contained"
                color="secondary"
                style={{ padding: "10px 50px" }}
                onClick={() => handleDelete()}
              >
                Delete
              </Button>
            </Grid>
            <Grid item>
              <Button
                variant="contained"
                color="primary"
                style={{ padding: "10px 50px" }}
                onClick={() => handleClose()}
              >
                Cancel
              </Button>
            </Grid>
          </Grid>
        </Paper>
      </Modal>
      <Modal
        open={openUpdate}
        onClose={handleCloseUpdate}
        className={classes.popupDialog}
      >
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
          <Typography variant="h4" style={{ marginBottom: "10px" }}>
            Update Details
          </Typography>
          <Grid
            container
            direction="column"
            spacing={3}
            style={{ margin: "10px" }}
          >
            <Grid item>
              <TextField
                label="Description"
                type="datetime-local"
                value={jobDetails.description}
                onChange={(event) => {
                  //handleInput("description", event.target.value);
                }}
                variant="outlined"
                fullWidth
              />
            </Grid>
            <Grid item>
              <TextField
                label="Requirements"

                variant="outlined"
                value={jobDetails.requirements}
                onChange={(event) => {
                  //handleInput("requirements", event.target.value);
                }}
                InputProps={{ inputProps: { min: 1 } }}
                fullWidth
              />
            </Grid>
            <Grid item>
              <TextField
                label="Salary"
                type="number"
                variant="outlined"
                value={jobDetails.salary}
                onChange={(event) => {
                  //handleInput("salary", event.target.value);
                }}
                InputProps={{ inputProps: { min: 1 } }}
                fullWidth
              />
            </Grid>
          </Grid>
          <Grid container justify="center" spacing={5}>
            <Grid item>
              <Button
                variant="contained"
                color="secondary"
                style={{ padding: "10px 50px" }}
                onClick={() => handleJobUpdate()}
              >
                Update
              </Button>
            </Grid>
            <Grid item>
              <Button
                variant="contained"
                color="primary"
                style={{ padding: "10px 50px" }}
                onClick={() => handleCloseUpdate()}
              >
                Cancel
              </Button>
            </Grid>
          </Grid>
        </Paper>
      </Modal>
    </Paper>
  );
};

const MyJobs = (props) => {
  const [jobs, setJobs] = useState([]);
  const [searchOptions, setSearchOptions] = useState({
    query: "",
    jobType: {
      fullTime: false,
      partTime: false,
      wfh: false,
    },
    sort: {
      salary: {
        status: false,
        desc: false,
      }
    },
  });

  const [openApplications, setOpenApplications] = useState(false);
  const [selectedJobId, setSelectedJobId] = useState("");

  // Function to open the application dialog
  const handleOpenApplications = (jobId) => {
      setSelectedJobId(jobId);
      setOpenApplications(true);
    };

    // Function to close the application dialog
  const handleCloseApplications = () => {
      setOpenApplications(false);
    };
  useEffect(() => {
    // Fetch data from the API when the component mounts
    getData();
  }, []);

  const getData = () => {

    const queryString = "";
    console.log(queryString);
    let address = apiList.getMyJobs;
    if (queryString !== "") {
      address = `${address}?${queryString}`;
    }

    console.log(address);
    axios
      .get(address, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .then((response) => {
        console.log(response.data);
        setJobs(response.data);

      })
      .catch((err) => {
        console.log(err.response.data);
      });
  };

  const reload = (del) =>{
  if(del){
  window.location.reload(false);
    getData();}

  }

  return (
    <>
      <Grid
        container
        item
        direction="column"
        alignItems="center"
        style={{ padding: "50px", minHeight: "93vh", width: "", paddingRight: "190px" }}
      >
        <Grid
          item
          container
          direction="column"
          justify="center"
          alignItems="center"
        >
          <Grid item xs>
            <Typography variant="h2">My Jobs</Typography>
          </Grid>
        </Grid>

        <Grid
          container
          item
          xs
          direction="column"
          alignItems="stretch"
          justify="center"
        >
          {jobs.length > 0 ? (
            jobs.map((job) => {
              //return <JobTile job={job} getData={getData} />;
              return (
                              <JobTile
                                key={job._id}
                                job={job}
                                getData={getData}
                                onViewApplications={() => handleOpenApplications(job.id)}
                                onDelete ={() => reload(true)}
                              />
                            );
            })
          ) : (
            <Typography variant="h5" style={{ textAlign: "center" }}>
              No jobs found
            </Typography>
          )}
        </Grid>
      </Grid>
      <Dialog
              open={openApplications}
              onClose={handleCloseApplications}
              fullWidth
              maxWidth="md"
            >
              <DialogTitle>Job Applications</DialogTitle>
              <DialogContent>
                <JobApplications jobId={selectedJobId} />
              </DialogContent>
              <DialogActions>
                <Button onClick={handleCloseApplications} color="primary">
                  Close
                </Button>
              </DialogActions>
            </Dialog>
    </>
  );
};

export default MyJobs;