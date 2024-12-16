import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
} from "@material-ui/core";


const JobOfferDetails = (props) => {
  const {
    company,
    contract,
    featured,
    id,
    languages,
    level,
    location,
    logo,
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
  const [accepted, setAccepted] = useState(false);
const [rejected, setRejected] = useState(false);


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
        marginTop: theme.spacing(2),
      },
      button: {
        fontSize: "1.2rem",
        margin: theme.spacing(1, 1),
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

  const handleAcceptOffer = () => {
    setAccepted(true);
  };
  
  const handleRejectOffer = () => {
    setRejected(true);
  };
  
  const contentMap = {
    default: {
      title: position,
      data: {
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
    }
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
          <div className={classes.buttonContainer}>
          <>
              <Button
                className={classes.button}
                onClick={handleAcceptOffer}
                color="primary"
              >
                Accept
              </Button>
              <Button
                className={classes.button}
                onClick={handleRejectOffer}
                color="primary"
              >
                Reject
              </Button>
            </>
        </div>
        </DialogContent>
      </Dialog>
    </div>
  );
};

export default JobOfferDetails;
