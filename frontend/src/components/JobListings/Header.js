import React from "react";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  // Define the styles for the component using makeStyles hook
  headerContainer: {
    display: "flex",
    flexDirection: "column",
    alignItems: "left",
    marginTop: "calc(2vw - 4rem + 90px)",
    marginLeft: theme.spacing(-2),
  },
  keywordList: {
    [theme.breakpoints.down("xs")]: {
      flexWrap: "wrap",
      marginBottom: "1rem",
    },
    listStyle: "none",
    width: "70vw",
    backgroundColor: "#fff",
    display: "flex",
    alignItems: "center",
    padding: "1.5rem 2rem",
    borderRadius: "5px",
    margin: "1.3rem 0",
    position: "relative",
    boxShadow: "0px 4px 6px 4px rgba(91, 164, 164, 0.2)",
    marginBottom: "1rem",
    position: "relative",
  },
  keywordItem: {
    backgroundColor: "#f5f5f5", 
    color: theme.palette.primary.main,
    fontWeight: 700,
    padding: "0.2rem",
    display: "flex",
    alignItems: "center",
    borderRadius: "5px",
    [theme.breakpoints.down("xs")]: {
      marginBottom: "0.5rem",
    },
    "&:not(:last-child)": {
      marginRight: "1rem",
    },
    "& .close": {
      backgroundColor: "var(--primary-color)",
      border: "none",
      borderRadius: "0 5px 5px 0",
      cursor: "pointer",
      marginLeft: "0.5rem",
      transition: "all 0.2s",
      "& img": {
        padding: "0.5rem",
      },
      "&:hover": {
        backgroundColor: "var(--very-dark-greyish)",
      },
    },
  },
  clearLink: {
    color: "var(--very-dark-greyish)",
    position: "absolute",
    right: "1.5rem",
    textDecoration: "none",
    "&:hover": {
      color: "var(--primary-color)",
      textDecoration: "underline",
    },
  },
  keywordText: {
    padding: "0.2rem", 
  },
}));

const Header = ({ keywords, removeKeywords, clearAll }) => {
  const classes = useStyles();

  return (
    <div className={classes.headerContainer}>
      <ul className={classes.keywordList}>
        {keywords.map((key, id) => {
          return (
            <li key={id} className={classes.keywordItem}>
               <span className={classes.keywordText}>{key}</span> 
              <button className="close" onClick={() => removeKeywords(key)}>
                {}
              </button>
            </li>
          );
        })}
        <a href="/#" onClick={() => clearAll()} className={classes.clearLink}>
          Clear
        </a>
      </ul>
    </div>
  );
};

export default Header;
