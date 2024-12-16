import React, { useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import SideMenu from '../components/SideMenu';
import { AppBar, Toolbar, Typography, IconButton, Menu, MenuItem } from '@material-ui/core';
import { getUserInfo, logOut } from '../services/registerAPI';
import EmployerHP from '../components/Employer/EmployerHP';
import CandidateHP from '../components/Candidate/CandidateHP';
import { createTheme, ThemeProvider } from "@material-ui/core/styles";
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import { Grid } from '@material-ui/core';
import CreateJobsPage from "../components/Employer/CreateJobsPage"
import MyJobs from "../components/Employer/MyJobs"
import Jobs from "../components/JobListings/Jobs";
import Candidates from "../components/CandidateListings/Candidates";
import CandidateTrackApplications from "../components/Candidate/TrackApplications";
import EmployerTrackApplications from "../components/Employer/TrackApplications";
import AdminHP from "../components/Admin/AdminHP";
import CandidateListing from "../components/Admin/CandidateListing";
import EmployerListing from "../components/Admin/EmployerListing";
import JobListing from "../components/Admin/JobListing";
import Resume from "../components/Candidate/Resume";
import Header from "../components/JobListings/Header";
import { jobListings, candidateListings, employerListings, getCandidateResume } from '../services/registerAPI';
import { Dialog, DialogTitle, DialogContent, DialogActions, Button } from '@material-ui/core';
import { formatDistanceToNow } from 'date-fns';
import Tracking from './Admin/Tracking';
import EditProfile from './EditProfile';


const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    height: '100%',
  },
  appBar: {
    height: 55,
    backgroundColor: '#fecdcc',
    flexGrow: 1,
  },
  title: {
    color: "black",
    textAlign: 'left',
    fontWeight: 900,
    marginBottom: theme.spacing(2),
    marginLeft: theme.spacing(0)
  },
  content: {
    display: 'flex',
    flexGrow: 1,
    minHeight: 0,
  },
  menuItem: {
    fontFamily: 'inherit',
    fontWeight: 'inherit',
    fontSize: 'inherit',
  },
  accountIcon: {
    color: 'black',
    fontSize: 30,
    position: "center",
    marginBottom: theme.spacing(1)
  },
  menuIconContainer: {
    position: 'absolute',
    right: 0,
  },
}));


function Home({ loginCallBack }) {
  const classes = useStyles();
  const user = getUserInfo();
  console.log("This is user info "+user.last_name);
  let userType = user?.userType;

  const [dataFetched, setDataFetched] = useState(false);
  const [editProfileOpen, setEditProfileOpen] = useState(false);

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

  const [selectedOption, setSelectedOption] = useState(defaultActiveMenuItem);
  const [filterKeywords, setfilterKeywords] = useState([]);
  const [errMsg, setErrMsg] = useState('');

  const [data, setData] = useState([]);
  const [candidateData, setCandidateData] = useState([]);
  const [employerData, setEmployerData] = useState([]);

  const formatPostedAt = (dateString) => {
    const date = new Date(dateString);
    // Check if the date is valid before formatting
    if (isNaN(date)) {
      return "Invalid date"; // You can customize the message as needed
    }
    const new_format = formatDistanceToNow(date, { addSuffix: true });
    return new_format;
  };

  const fetchDataFromAPI = async () => {
    jobListings()
      .then((data) => {
        if (data.errors) {
          setErrMsg(data.errors[0]);
        } else {
          const formattedData = data.map((item) => ({
            ...item,
            posted_at: formatPostedAt(item.posted_at),
          }));
          setData(formattedData);
          setErrMsg('');
          setDataFetched(true);
        }
      })
      .catch(() => {
        setErrMsg('Unable to register');
      });
  };

  const fetchCandidateDataFromAPI = async () => {
    try {
      const response = await candidateListings();

      if (response.errors) {
        setErrMsg(response.errors[0]);
      } else {
        // Collect all the email addresses from the response
        const emailAddresses = response.map((candidate) => candidate.email_address);

        // Make API calls for each candidate's email address and get the resumes
        const getCandidateResumePromises = emailAddresses.map((email) =>
          getCandidateResume(email)
        );

        // Wait for all the API calls to complete
        const candidateResumes = await Promise.all(getCandidateResumePromises);

        // Merge candidate data with their resumes
        const candidatesWithData = response.map((candidate, index) => ({
          ...candidate,
          resume: candidateResumes[index],
        }));

        setCandidateData(candidatesWithData);
        setErrMsg("");
        setDataFetched(true);
      }
    } catch (error) {
      setErrMsg("");
    }
  };

  const fetchEmployerDataFromAPI = async () => {
    try {
      const response = await employerListings();

      if (response.errors) {
        setErrMsg(response.errors[0]);
      } else {
        setEmployerData(response.data);
        setErrMsg("");
        setDataFetched(true);
      }
    } catch (error) {
      setErrMsg("");
    }
  };

  useEffect(() => {
    // Fetch data from the API when the component mounts
    fetchDataFromAPI();
    fetchCandidateDataFromAPI();
    fetchEmployerDataFromAPI();
  }, []);

  const addFilterKeywords = (data) => {
    if (!filterKeywords.includes(data)) {
      setfilterKeywords([...filterKeywords, data]);
    }
  };

  const deleteKeyword = (data) => {
    const newKeywords = filterKeywords.filter((key) => key !== data);
    setfilterKeywords(newKeywords);
  };

  const clearAll = () => {
    setfilterKeywords([]);
  };

  //let userType = user?.userType;
  const font = "League Spartan, monospace";

  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    logOut();
    loginCallBack();
  };

  const handleMenuItemClick = (option) => {
    setSelectedOption(option);
    handleClose();
  };


  const handleEditProfile = () => {
      setEditProfileOpen(true);
      //handleClose(); // Close the menu
    };

  const handleCloseEP = () => {
      setAnchorEl(null);
      setEditProfileOpen(false); // Close the dialog
  };

  const theme = createTheme({
    // Define the theme for Material-UI components
    typography: {
      fontFamily: font,
      button: {
        textTransform: "none"
      }
    }
  });

  return (
    <>
      <ThemeProvider theme={theme}>
        <div className={classes.root}>
          <AppBar position="fixed" className={classes.appBar}>
            <Toolbar>
              <Typography variant="h4" className={classes.title}>connects.</Typography>
              <div className={classes.menuIconContainer}>
                <IconButton color="inherit" onClick={handleClick}>
                  <AccountCircleIcon className={classes.accountIcon} />
                </IconButton>
                <Menu
                  anchorEl={anchorEl}
                  keepMounted
                  open={Boolean(anchorEl)}
                  onClose={handleClose}
                >
                  <MenuItem onClick={handleEditProfile} className={classes.menuItem}>User: {user.last_name}</MenuItem>
                  <MenuItem onClick={handleLogout} className={classes.menuItem}>Logout</MenuItem>
                </Menu>
                <Dialog open={editProfileOpen} onClose={handleCloseEP} maxWidth="sm" fullWidth>
                        <DialogTitle>Edit Profile</DialogTitle>
                        <DialogContent>
                          <EditProfile userData={user} accountType={userType} />
                        </DialogContent>
                        <DialogActions>
                          <Button onClick={handleClose} color="primary">
                            Close
                          </Button>
                        </DialogActions>
                </Dialog>
              </div>
            </Toolbar>
          </AppBar>
          {userType !== null && dataFetched &&
            <Grid container className={classes.content}>
              <Grid item xs={3}>
                <SideMenu
                  loginCallBack={loginCallBack}
                  onMenuItemClick={handleMenuItemClick}
                />
              </Grid>
              <Grid item xs>
                {userType === 'admin' ? (
                  <>
                    <AdminHP />
                    {selectedOption === 'tracking' ? (
                      <>
                        <Tracking
                          data={data} />
                      </>
                    ) : null}
                    {selectedOption === 'candidate_listings' ? (
                      <>
                        <CandidateListing
                          data={candidateData}
                        />
                      </>
                    ) : null}
                    {selectedOption === 'employer_listings' ? (
                      <>
                        <EmployerListing
                          data={employerData}
                        />
                      </>
                    ) : null}
                    {selectedOption === 'job_listings' ? (
                      <>
                        <JobListing
                          data={data}
                        />
                      </>
                    ) : null}
                  </>

                ) : null}
                {userType === 'employer' ? (
                  <>
                    <EmployerHP />
                    {selectedOption === 'candidate_listings' ? (
                      <>
                        {filterKeywords.length > 0 && (
                          <Header
                            keywords={filterKeywords}
                            removeKeywords={deleteKeyword}
                            clearAll={clearAll}
                          />
                        )}
                        <Candidates
                          keywords={filterKeywords}
                          data={candidateData}
                          setKeywords={addFilterKeywords}
                        />
                      </>
                    ) : null}
                    {selectedOption === 'track' ? (
                      <>
                        <EmployerTrackApplications
                          data={data}
                          employerData={JSON.parse(sessionStorage.getItem("AUTH_TOKEN"))}
                        />
                      </>
                    ) : null}
                    {selectedOption === 'my_jobs' ? (
                      <>
                        <MyJobs />
                      </>
                    ) : null}
                    {selectedOption === 'create_jobs' ? (
                      <>
                        {filterKeywords.length > 0 && (
                          <Header
                            keywords={filterKeywords}
                            removeKeywords={deleteKeyword}
                            clearAll={clearAll}
                          />
                        )}
                        <CreateJobsPage
                          keywords={filterKeywords}
                          data={data}
                          setKeywords={addFilterKeywords}
                        />
                      </>
                    ) : null}
                  </>
                ) : null}

                {userType === 'candidate' ? (
                  <>
                    <CandidateHP />
                    {selectedOption === 'job_listings' && data.length > 0 ? (
                      <>
                        {filterKeywords.length > 0 && (
                          <Header
                            keywords={filterKeywords}
                            removeKeywords={deleteKeyword}
                            clearAll={clearAll}
                          />
                        )}
                        <Jobs
                          keywords={filterKeywords}
                          data={data}
                          setKeywords={addFilterKeywords}
                        />
                      </>
                    ) : null}
                    {selectedOption === 'resume' ? (
                      <>
                        {filterKeywords.length > 0 && (
                          <Header
                            keywords={filterKeywords}
                            removeKeywords={deleteKeyword}
                            clearAll={clearAll}
                          />
                        )}
                        < Resume
                          keywords={filterKeywords}
                          data={data}
                          setKeywords={addFilterKeywords}
                        />
                      </>
                    ) : null}
                    {selectedOption === 'track' ? (
                      <>
                        <CandidateTrackApplications
                          data={data}
                          candidateData={JSON.parse(sessionStorage.getItem("AUTH_TOKEN"))}
                        />
                      </>
                    ) : null}
                  </>
                ) : null}
              </Grid>
            </Grid>
          }
        </div>
      </ThemeProvider>
    </>
  );
}

export default Home;