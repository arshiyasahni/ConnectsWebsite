import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
} from "@material-ui/core";
import { applyCandidateJobs } from '../../services/registerAPI';

const CandidateDescription = (props) => {
  const candidateData = props.data;

  const [currentContent, setCurrentContent] = useState("default");
  const { open, handleClose } = props;
  const [errMsg, setErrMsg] = useState('');

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
    // let userData = {
    //   job_id: props.data.id,
    //   email_address: props.candidateData.email_address
    // }
    // applyCandidateJobs(userData)
    //   .then((data) => {
    //     if (data.errors) {
    //       setErrMsg(data.errors[0]);
    //     } else {
    //       console.log("response in job description: " + JSON.stringify(data))
    //       setErrMsg("Application submitted successfully!");
    //     }
    //   })
    //   .catch(() => {
    //     setErrMsg("Error submitting application. Please try again later.");
    //   });
  };


  const formatCandidateData = (candidateData) => {
    const renderValue = (value) => {
      if (typeof value === "object" && !Array.isArray(value)) {
        if (value.errors) {
          return <div className={classes.valueText}>{value.errors[0]}</div>;
        }
        return formatCandidateData(value);
      } else {
        return <div className={classes.valueText}>{value}</div>;
      }
    };

    if (candidateData && typeof candidateData === "object" && Object.keys(candidateData).length > 0) {
      return Object.entries(candidateData).map(([key, value]) => (
        <React.Fragment key={key}>
          <span className={classes.bold}>{key}: </span>
          <br />
          {renderValue(value)}
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
          {candidateData.last_name}
        </DialogTitle>
        <DialogContent>
          {errMsg && (
            <div className={classes.errMsg} style={{ color: "red" }}>
              {errMsg}
              <br />
            </div>
          )}
          {formatCandidateData(candidateData)}
        </DialogContent>
      </Dialog>
    </div>
  );

};

export default CandidateDescription;
