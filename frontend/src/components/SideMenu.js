import React from 'react';
import Drawer from '@material-ui/core/Drawer';
import { makeStyles } from '@material-ui/core/styles';
import { MenuItem } from '@material-ui/core';
import { getUserInfo } from '../services/registerAPI';
import { createTheme, ThemeProvider } from "@material-ui/core/styles";

const drawerWidth = 240;

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

const useStyles = makeStyles(theme => ({
  particlesContainer: {
    position: 'fixed',
    top: 0,
    left: 0,
    width: '100%',
    height: '100%',
    zIndex: -1,
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
  },
  drawerPaper: {
    width: drawerWidth,
    top: theme.mixins.toolbar.minHeight,
    height: `calc(100% - ${theme.mixins.toolbar.minHeight})`,
    background: '#fffaf1',
    color: 'black',
    boxShadow: '3px 0 10px rgba(0, 0, 0, 0.2)',
  },
  bigAvatar: {
    margin: 30,
    width: 100,
    height: 100,
  },
  menuItem: {
    fontFamily: 'inherit',
    fontWeight: 'inherit',
    fontSize: 'inherit',
    position: 'relative',
    transition: 'background-color 0.2s ease-in-out',
    '&:hover': {
      backgroundColor: '#ffe4e6',
    },
    '&::after': {
      content: '""',
      position: 'absolute',
      bottom: 0,
      left: 0,
      height: '2px',
      width: '100%',
      background: `linear-gradient(to right, #d3d3d3, transparent)`,
    },
  },
  activeMenuItem: {
    backgroundColor: '#ffe4e6',
  },
  menu: {
    position: 'absolute',
    top: theme.mixins.toolbar.minHeight + 20,
    right: 10,
  },
}));

function SideMenu({ loginCallBack, onMenuItemClick }) {
  const classes = useStyles();
  const user = getUserInfo();
  let userType = user?.userType;

  // Define a default active menu item based on userType
  let defaultActiveMenuItem;
  switch (userType) {
    case 'candidate':
      defaultActiveMenuItem = 'job_listings';
      break;
    case 'employer':
      defaultActiveMenuItem = 'my_jobs';
      break;
    case 'admin':
      defaultActiveMenuItem = 'tracking';
      break;
    default:
      defaultActiveMenuItem = ''; 
  }


  // Initialize activeMenuItem state with the default value
  const [activeMenuItem, setActiveMenuItem] = React.useState(defaultActiveMenuItem);


  const handleMenuItemClick = (option) => {
    onMenuItemClick(option);
    setActiveMenuItem(option);
  };

  const getMenuOptions = (userType, classes) => {
    switch (userType) {
      case 'candidate':
        return (
          <>
            <MenuItem
              className={`${classes.menuItem} ${activeMenuItem === 'job_listings' ? classes.activeMenuItem : ''
                }`}
              onClick={() => handleMenuItemClick('job_listings')}
            >
              Job Listings
            </MenuItem>

            <MenuItem
              className={`${classes.menuItem} ${activeMenuItem === 'resume' ? classes.activeMenuItem : ''
                }`}
              onClick={() => handleMenuItemClick('resume')}
            >
              Build/Upload Resume
            </MenuItem>

            <MenuItem
              className={`${classes.menuItem} ${activeMenuItem === 'track' ? classes.activeMenuItem : ''
                }`}
              onClick={() => handleMenuItemClick('track')}
            >
              Track Applications
            </MenuItem>
          </>
        );
      case 'employer':
        return (
          <>
            <MenuItem className={`${classes.menuItem} ${activeMenuItem === 'my_jobs' ? classes.activeMenuItem : ''}`}
              onClick={() => handleMenuItemClick('my_jobs')}>My Jobs
            </MenuItem>

            <MenuItem
              className={`${classes.menuItem} ${activeMenuItem === 'track' ? classes.activeMenuItem : ''
                }`}
              onClick={() => handleMenuItemClick('track')}
            >
              Track Applications
            </MenuItem>
            <MenuItem
              className={`${classes.menuItem} ${activeMenuItem === 'create_jobs' ? classes.activeMenuItem : ''}`}
              onClick={() => handleMenuItemClick('create_jobs')}
            >
              Create Jobs
            </MenuItem>
            <MenuItem
              className={`${classes.menuItem} ${activeMenuItem === 'candidate_listings' ? classes.activeMenuItem : ''}`}
              onClick={() => handleMenuItemClick('candidate_listings')}
            >
              Browse Candidates
            </MenuItem>

          </>
        );
      case 'admin':
        return (
          <>
            <MenuItem
              className={`${classes.menuItem} ${activeMenuItem === 'tracking' ? classes.activeMenuItem : ''}`}
              onClick={() => handleMenuItemClick('tracking')}
            >
              Tracking
            </MenuItem>
            <MenuItem
              className={`${classes.menuItem} ${activeMenuItem === 'candidate_listings' ? classes.activeMenuItem : ''}`}
              onClick={() => handleMenuItemClick('candidate_listings')}
            >
              Candidates
            </MenuItem>
            <MenuItem
              className={`${classes.menuItem} ${activeMenuItem === 'employer_listings' ? classes.activeMenuItem : ''}`}
              onClick={() => handleMenuItemClick('employer_listings')}
            >
              Employers
            </MenuItem>
            <MenuItem
              className={`${classes.menuItem} ${activeMenuItem === 'job_listings' ? classes.activeMenuItem : ''}`}
              onClick={() => handleMenuItemClick('job_listings')}
            >
              Job Listings
            </MenuItem>
          </>
        );
      default:
        return null;
    }
  };

  return (
    <Drawer
      open={true}
      variant='permanent'
      anchor='left'
      className={classes.drawer}
      classes={{
        paper: classes.drawerPaper,
      }}
    >
      <ThemeProvider theme={theme}>
        {getMenuOptions(userType, classes)}
      </ThemeProvider>
    </Drawer>
  );
}

export default SideMenu;