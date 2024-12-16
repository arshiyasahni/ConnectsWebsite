import * as React from 'react';
import { makeStyles, ThemeProvider } from '@material-ui/core/styles';
import { Grid, Box, CssBaseline } from "@material-ui/core";
import { Particles } from 'react-tsparticles';

const width = 700;
const font = "League Spartan, monospace";


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
    padding: '10px 0px'
  },
}));
const widthModifier = {
  width: `${width}px`,
  fontWeight: "800",
  color: 'var(--main-color)'
};




export default function CandidateHP() {
const classes = useStyles();


  return (
    <Grid sx={{ width: '100%' }}>
        <div style={{backgroundColor: 'black',  width: '100%', color: 'white', }}> Candidate </div>
        <div className={classes.particlesContainer}>
            <Particles params={particlesOptions} />
        </div>
    </Grid>


  );
}