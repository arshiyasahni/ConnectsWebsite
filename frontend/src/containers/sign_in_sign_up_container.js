import React, { useEffect, useRef, useState } from "react";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import { createTheme, ThemeProvider } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import { Grid, Box, CssBaseline } from "@material-ui/core";
import { makeStyles } from '@mui/styles';
import Login from "../components/login";
import Signup from "../components/signup";
import { Particles } from 'react-tsparticles';


const SignInOutContainer = ({ loginCallBack }) => {
  const signUpRef = useRef(null);
  const [value, setValue] = useState(0);
  const [signUpFormHeight, setSignUpFormHeight] = useState(0);

  const font = "League Spartan, monospace";

  const theme = createTheme({
    // Define the theme for Material-UI components
    typography: {
      fontFamily: font,
      button: {
        textTransform: "none"
      }
    }
  });

  const particlesOptions = {
    background: {
      color: '#f1efe7'
    },
    particles: {
      number: {
        value: 100,
        density: {
          enable: true,
          value_area: 800,
        },
      },
      color: {
        value: ['#000000'],
        //value: ['#ff9000', '#ff0266', '#00ffff'],
      },
      shape: {
        type: 'circle',
      },
      size: {
        value: 3,
        random: true,
        anim: {
          enable: false,
        },
      },
      move: {
        enable: true,
        speed: 2,
        direction: 'none',
        random: true,
        straight: false,
        outModes: {
          default: 'out',
        },
        attract: {
          enable: false,
        },
      },
      links: {
        enable: true,
        color: '#000000',
        //color: '#ffffff',
        distance: 150,
        opacity: 0.4,
        width: 1,
      },
      interactivity: {
        detectsOn: 'canvas',
        events: {
          onHover: {
            enable: true,
            mode: 'repulse',
          },
        },
        modes: {
          repulse: {
            distance: 200,
            duration: 0.4,
          },
        },
      },
    },
    detectRetina: true,
  };

  const divStyle = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    borderRadius: '20px',
    background: '#fcfcfade', //'#ffffffe3',
    boxShadow: '0 2px 4px rgba(255, 255, 255, 0.1), 0 0 10px rgba(0, 0, 0, 0.1)',
    padding: '20px',
  };

  const useStyles = makeStyles((theme) => ({
    // Define the styles for the component using makeStyles hook
    particlesContainer: {
      position: 'fixed',
      top: 0,
      left: 0,
      width: '100%',
      height: '100%',
      zIndex: -1,
    },
    tabs: {
      color: 'black',
      backgroundColor: 'rgba(255, 255, 255, 0.3)',
      borderBottom: '1px solid rgba(0, 0, 0, 0.2)',
      '&:hover': {
        color: 'black',
        backgroundColor: 'rgba(255, 255, 255, 0.1)',
        transition: 'background-color 0.3s ease',
      },
    },
    panel: {
      textAlign: 'center',
    },
    typographyLeagueSpartan: {
      fontFamily: "'League Spartan', normal",
    },
  }));

  const classes = useStyles();


  useEffect(() => {
    // Fetch data from the API when the component mounts
    if (signUpRef.current) {
      setSignUpFormHeight(signUpRef.current.clientHeight);
    }
  }, [value]);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

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
    <Grid
      container
      component="main"
      style={{ height: "100%" }}
      sx={{ height: "100%" }}
    >
      <CssBaseline />
      <Grid
        container
        justifyContent="center"
        alignItems="center"
        style={{ height: `calc(100vh + ${signUpFormHeight}px)` }}
        sx={{
          backgroundRepeat: "no-repeat",
          backgroundColor: (t) =>
            t.palette.mode === "light"
              ? t.palette.grey[50]
              : t.palette.grey[900],
          backgroundSize: "cover",
          backgroundPosition: "center",
          height: "100vh",
        }}
      >
        <div className={classes.particlesContainer}>
          <Particles params={particlesOptions} />
        </div>
        <div className="signinContainer" style={divStyle} class="box-style" >
          <ThemeProvider theme={theme}>
            <Typography variant="h3" style={{ fontFamily: font, fontWeight: 900 }}>connects.</Typography>
          </ThemeProvider>
          <Tabs
            value={value}
            onChange={handleChange}
            variant="fullWidth"
            indicatorColor="primary" // Change the indicator color
          >
            <Tab
              label="Sign In"
              className={classes.tabs}
            />
            <Tab
              label="Sign Up"
              className={classes.tabs}
            />
          </Tabs>
          <TabPanel value={value} index={0}>
            <Login
              handleChange={handleChange}
              loginCallBack={loginCallBack}
            />
          </TabPanel>
          <TabPanel value={value} index={1}>
            <Signup loginCallBack={loginCallBack} />
          </TabPanel>
        </div>

      </Grid>
    </Grid>
  );
};

export default SignInOutContainer;
