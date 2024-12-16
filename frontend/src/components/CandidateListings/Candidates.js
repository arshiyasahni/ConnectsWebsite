import React, { useEffect, useState } from "react";
import Candidate from "./Candidate";
import { makeStyles } from "@material-ui/core/styles";

const Candidates = ({ data, setKeywords, keywords }) => {
  
  const [filteredData, setfilteredData] = useState([]);

  const useStyles = makeStyles((theme) => ({
    // Define the styles for the component using makeStyles hook
    candidatesContainer: {
      display: "flex",
      flexDirection: "column",
      marginLeft: theme.spacing(-2),
      marginTop: "calc(2vw - 4rem + 75px)",
    },
  }));

  const classes = useStyles();

  const modifiedData = () => {
    if (keywords) {
      const newData = data.filter((d) => {
        return keywords.every((key) => {
          return (
            d.role === key ||
            d.level === key ||
            d.languages.includes(key) ||
            d.tools.includes(key)
          );
        });
      });
      setfilteredData(newData);
    } else {
      setfilteredData(data);
    }
  };

  useEffect(() => {
    // Fetch data from the API when the component mounts
    modifiedData();
  }, [keywords]);

  return (
    <div className={classes.candidatesContainer}>
      {filteredData.map((d) => {
        return <Candidate key={d.id} data={d} setkeywords={setKeywords}/>;
      })}
    </div>
  );
};

export default Candidates;
