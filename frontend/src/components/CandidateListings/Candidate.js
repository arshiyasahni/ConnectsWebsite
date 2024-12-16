import React, { useEffect, useState, useRef } from "react";
import { makeStyles } from "@material-ui/core/styles";
import {
  useMediaQuery,
} from "@material-ui/core";
import CandidateDescription from "./CandidateDescription";

const Candidate = (props) => {
  const {
    email_address,
    last_name,
    phone_number
  } = props.data;

  //let keywords = [role, level, ...languages, ...tools];
  const jobRef = useRef(null);
  const isSmallScreen = useMediaQuery((theme) => theme.breakpoints.down("sm"));

  const [icon, setIcon] = useState("");
  const [openDialog, setOpenDialog] = useState(false); // State to control dialog box

  useEffect(() => {
    // Fetch data from the API when the component mounts
    if (isSmallScreen) {
      setOpenDialog(false);
    }
  }, [isSmallScreen]);

  const handleDialogOpen = () => {
    setOpenDialog(true);
  };

  const handleDialogClose = () => {
    setOpenDialog(false);
  };

  const useStyles = makeStyles((theme) => ({
    // Define the styles for the component using makeStyles hook
    part2: {
      display: "flex",
      flexWrap: "wrap",
      justifyContent: "space-between",
      [theme.breakpoints.down("sm")]: {
        borderTop: `1px solid ${theme.palette.grey[700]}`,
        paddingTop: "1rem",
        justifyContent: "flex-start",
      },
    },
    span: {
      "&:not(:last-child)": {
        marginRight: "1rem",
      },
      "&:hover": {
        backgroundColor: theme.palette.primary.main,
        color: "#fff",
      },
      [theme.breakpoints.down("sm")]: {
        marginBottom: "1rem",
      },
      backgroundColor: theme.palette.background.default,
      color: theme.palette.primary.main,
      fontWeight: 700,
      padding: "0.5rem",
      borderRadius: "5px",
      cursor: "pointer",
    },
    dialogContent: {
      width: "100%",
      [theme.breakpoints.up("sm")]: {
        width: "70%",
        marginLeft: "290px",
      },
    },
  }));

  const classes = useStyles();

  return (
    <div
      ref={jobRef}
      className={ "job-container"}
    >
      <div className="logo">
        <img src={icon} alt="" />
      </div>
      <div className="part1">
        <div className="company"  onClick={handleDialogOpen}>
          <span className="cname">{last_name}</span>
        </div>

        <div className="details">
          <span>{email_address}</span>
          <span>&nbsp;•&nbsp;</span>
          <span>{phone_number}</span>
          <span>&nbsp;•&nbsp;</span>
        </div>
      </div>

      <div className={classes.part2}>
        {/* {keywords.map((key, id) => (
          <span
            className={classes.span}
            onClick={() => props.setkeywords(key)}
            key={id}
          >
            {key}
          </span>
        ))} */}
      </div>

      {/* Dialog */}
      <CandidateDescription
        open={openDialog}
        handleClose={handleDialogClose}
        data={props.data}
        candidateData={JSON.parse(sessionStorage.getItem("AUTH_TOKEN"))}
      />
    </div>
  );
};

export default Candidate;
