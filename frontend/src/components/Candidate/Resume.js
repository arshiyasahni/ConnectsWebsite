import * as React from 'react';
import Box from '@mui/material/Box';
import { makeStyles } from '@material-ui/core/styles';
import Form from "./Form/Form";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import UploadFile from "./UploadFile";
import Typography from "@material-ui/core/Typography";

const divStyle = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    borderRadius: '20px',
    background: '#fcfcfade', //'#ffffffe',
    boxShadow: '0 2px 4px rgba(255, 255, 255, 0.1), 0 0 10px rgba(0, 0, 0, 0.1)',
    padding: '50px 50px',
    marginTop: "calc(2vw - 4rem + 90px)",
    marginRight: "calc(2vw - 4rem + 190px)"
  };
 const useStyles = makeStyles(theme => ({
   particlesContainer: {
       position: 'fixed',
       top: 0,
       left: 0,
       width: '100%',
       height: '100%',
       zIndex: -1,
     },
   root: {
     display: 'flex',
     padding: '50px 0px',
     marginTop: "calc(2vw - 4rem + 90px)",
   },
 }));
function Resume() {

const [value, setValue] = React.useState('1');

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  const classes = useStyles();
  function TabPanel(props) {
      const { children, value, index, ...other } = props;

      return (
        <div
          role="tabpanel"
          hidden={value !== index}
          id={`simple-tabpanel-${index}`}
          aria-labelledby={`simple-tab-${index}`}
          {...other}
        >
          {value === index && (
            <Box>
              <Typography>{children}</Typography>
            </Box>
          )}
        </div>
      );
    }
  return (
    <div className="Resume">
    <div className="resumeContainer" style={divStyle} class="box-style" >
                <Tabs
                    value={value}
                    onChange={handleChange}
                    variant="fullWidth"
                    indicatorColor="primary" // Change the indicator color
                >
                    <Tab
                            label="Build Resume"
                            className={classes.tabs}
                          />
                          <Tab
                            label="Upload Resume"
                            className={classes.tabs}
                          />
                        </Tabs>
                        <TabPanel value={value} index={0}>
                          <h1 className="text-center">Lets build your Resume</h1>
                          <Form />
                        </TabPanel>
                        <TabPanel value={value} index={1}>
                            <UploadFile/>
                        </TabPanel>
                      </div>
    </div>
  );
}

export default Resume;
