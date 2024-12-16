import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
} from "@material-ui/core";
import { applyCandidateJobs } from '../../services/registerAPI';

const JobDescription = (props) => {
  const {
    company,
    contract,
    featured,
    id,
    languages,
    level,
    location,
    //logo,
    position,
    posted_at,
    role,
    tools,
    description,
    requirements,
  } = props.data;

  const [currentContent, setCurrentContent] = useState("default");
  const { open, handleClose } = props;
  const [errMsg, setErrMsg] = useState('');
  const [loading, setLoading] = useState(false);


  const useStyles = makeStyles((theme) => ({
    // Define the styles for the component using makeStyles hook
    container: {},
    dialogContent: {
      width: "100%",
      [theme.breakpoints.up("sm")]: {
        width: "70%",
        marginLeft: "290px",
      },
    },
    dialogTitle: {
      background: "linear-gradient(to right, #ffe4e6, transparent)",
      color: "#000",
      padding: "1rem 2rem",
      textShadow: "2px 2px 4px rgba(0, 0, 0, 0.3)",
      borderRadius: "8px 8px 0 0",
      fontWeight: 700,
    },
    bold: {
      fontWeight: "bold",
    },
    valueText: {
      marginLeft: "50px",
      marginBottom: "5px",
    },
    buttonContainer: {
      display: 'flex',
      justifyContent: 'center',
    },
    button: {
      fontSize: "1.2rem",
      margin: theme.spacing(1, 0),
      backgroundColor: '#fd8a88',
      color: '#fff',
      '&:hover': {
        backgroundColor: '#de4340',
      },
    },
    errMsg: {
      background: "linear-gradient(to right, #d2eaff, transparent)",
      padding: "8px",
      marginBottom: "10px",
      textAlign: "center",
    },
  }));

  const classes = useStyles();

  const handleGoBack = () => {
    setCurrentContent("default");
  };

  const handleNextClick = () => {
    setCurrentContent("newContent");
  };

  const handleApplyClick = async () => {
    setLoading(true);
    let userData = {
      job_id: props.data.id,
      email_address: props.candidateData.email_address,
      employer_email: props.data.employer_email
    }
    applyCandidateJobs(userData)
      .then((data) => {
        if (data.errors) {
          setErrMsg(data.errors[0]);
          console.log(data.errors[0]);
        } else {
          console.log("response in job description: " + JSON.stringify(data))
          setErrMsg("Application submitted successfully!");
        }
      })
      .catch(() => {
        setErrMsg("Error submitting application. Please try again later.");
      })
      .finally(() => {
        setLoading(false); // Set loading back to false
      });
  };

  const contentMap = {
    default: {
      title: position,
      data: {
        JobId: id,
        Company: company,
        Role: role,
        Level: level,
        Contract: contract,
        Description: description,
        Requirements: requirements,
        Languages: languages.join(", "),
        Location: location,
        Tools: tools.join(", "),
        "Posted At": posted_at,
      },
    },
    newContent: {
      title: "Your information",
      data: {
        Note: "Please ensure you have either built your profile or uploaded your resume for your employer to view prior applying. Employer will only receive below data if your profile does not exist.",
        Name: props.candidateData.last_name,
        Email: props.candidateData.email_address,
        Contact: props.candidateData.phone_number,
      }
    },
  };

  const formatCurrentContentData = () => {
    const currentData = contentMap[currentContent].data;
    if (typeof currentData === "object" && Object.keys(currentData).length > 0) {
      return Object.entries(currentData).map(([key, value]) => (
        <React.Fragment key={key}>
          <span className={classes.bold}>{key}: </span>
          <br />
          <div className={classes.valueText}>{value}</div>
          <br />
        </React.Fragment>
      ));
    } else {
      return (
        <React.Fragment>
          <div className={classes.valueText}>No data available</div>
          <br />
        </React.Fragment>
      );
    }
  };


  return (
    <div className={classes.container}>
      <Dialog
        open={open}
        onClose={handleClose}
        maxWidth="xl"
        PaperProps={{
          className: classes.dialogContent,
        }}
        anchorOrigin={{
          vertical: "top",
          horizontal: "right",
        }}
        transformOrigin={{
          vertical: "top",
          horizontal: "right",
        }}
      >
        <DialogTitle className={classes.dialogTitle}>
          {contentMap[currentContent].title}
        </DialogTitle>
        <DialogContent>
          {errMsg && (
            <div className={classes.errMsg} style={{ color: 'red' }}>
              {errMsg}
              <br />
            </div>
          )}
          {formatCurrentContentData()}
        </DialogContent>
        <DialogActions className={classes.buttonContainer}>
          {currentContent === "newContent" ? (
            <React.Fragment>
              {loading ? (
                <div className={classes.errMsg}>Loading...</div> // Display loading message
              ) : (
                <>
                  <Button
                    className={classes.button}
                    onClick={handleGoBack}
                    color="primary"
                  >
                    Back
                  </Button>
                  <Button
                    className={classes.button}
                    onClick={handleApplyClick}
                    color="primary"
                  >
                    Apply
                  </Button>
                  <Button
                    className={classes.button}
                    onClick={handleClose}
                    color="primary"
                  >
                    Close
                  </Button>
                </>
              )}
            </React.Fragment>
          ) : (
            props.invoker === "job_listing" && (
              <Button
                className={classes.button}
                onClick={handleNextClick}
                color="primary"
              >
                Proceed to apply
              </Button>
            )
          )}
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default JobDescription;
