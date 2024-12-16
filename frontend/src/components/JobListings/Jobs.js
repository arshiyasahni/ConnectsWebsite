import React, { useEffect, useState } from "react";
import Job from "./Job";
import { makeStyles } from "@material-ui/core/styles";

const Jobs = ({ data, setKeywords, keywords }) => {
  
  const [filteredData, setfilteredData] = useState([]);

  const useStyles = makeStyles((theme) => ({
    // Define the styles for the component using makeStyles hook
    jobsContainer: {
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
    // SearchFunc();
  }, [keywords]);

  return (
    <div className={classes.jobsContainer}>
      {filteredData.map((d) => {
        return <Job key={d.id} data={d} setkeywords={setKeywords} className={classes.jobItem} />;
      })}
    </div>
  );
};

export default Jobs;
