import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { Grid, Paper, Typography, Box, Grow, Dialog, DialogTitle, DialogContent } from "@material-ui/core";
import { createTheme, ThemeProvider } from "@material-ui/core/styles";
import JobDescription from "../JobListings/JobDescription";
import { adminTracking } from '../../services/registerAPI';

const useStyles = makeStyles((theme) => ({
    // Define the styles for the component using makeStyles hook
    root: {
        flexGrow: 1,
        maxWidth: 1000,
        margin: "0 auto",
        marginBottom: theme.spacing(-7),
        marginTop: 40,
        fontFamily: "Arial, sans-serif",
    },
    kanbanBoard: {
        display: "flex",
        flexWrap: "wrap",
        marginTop: "70px",
        justifyContent: "flex-start",
    },
    column: {
        flex: "1 1 25%",
        padding: 16,
        textAlign: "center",
        overflowY: "auto",
        minHeight: 300,
        borderRadius: 4,
        backgroundColor: "#fafafaeb",
        boxShadow: theme.shadows[2],
        transition: "background-color 0.2s ease",
        "&:hover": {
            backgroundColor: "#f0f0f0",
        },
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
    },
    appliedColumn: {
        marginLeft: theme.spacing(-15),
    },
    companyName: {
        fontWeight: 900,
        color: "#0540c5",
    },
    position: {
        color: "#000000",
    },
    card: {
        padding: 16,
        marginBottom: 16,
        borderRadius: 4,
        backgroundColor: "#fff",
        boxShadow: theme.shadows[2],
        cursor: "pointer",
        transition: "box-shadow 0.2s ease",
        "&:hover": {
            boxShadow: theme.shadows[4],
        },
    },
    statusHeader: {
        marginBottom: 16,
        fontWeight: "bold",
        borderBottom: "1px solid #ddd",
        paddingBottom: 8,
    },
}));

const font = "League Spartan, monospace";
const theme = createTheme({
    // Define the theme for Material-UI components
    typography: {
        fontFamily: font,
        button: {
            textTransform: "none",
        },
    },
});

const TrackApplications = (props) => {
    const classes = useStyles();
    const [applications, setApplications] = useState({});

    const fetchDataFromAPI = async () => {
        // Function to fetch application data from the API
        adminTracking()
            .then((data) => {
                if (data.errors) {
                    setErrMsg(data.errors[0]);
                } else {
                    console.log("Data: " + JSON.stringify(data))
                    setApplications(data)
                    setErrMsg('');

                }
            })
            .catch(() => {
                setErrMsg('Unable to register');
            });
    };

    useEffect(() => {
        // Fetch data from the API when the component mounts
        fetchDataFromAPI();
    }, []);

    const jobData = props.data;

    const [errMsg, setErrMsg] = useState('');
    const [isDialogOpen, setIsDialogOpen] = useState(false);
    const [jobOffer, setJobOffer] = useState(false);
    const [selectedCardData, setSelectedCardData] = useState(null);

    const getJobDataFromPropsData = (jobID) => {
        console.log("Inside function: " + jobID)
        return props.data.find((job) => job.id === jobID);
    };


    const handleDialogOpen = (data) => {
        setIsDialogOpen(true);
        const jobDataFromProps = getJobDataFromPropsData(data.jobID);
        if (jobDataFromProps) {
            setSelectedCardData({ ...data, ...jobDataFromProps });
        } else {
            setSelectedCardData(data);
        }
    };

    const handleDialogClose = () => {
        setIsDialogOpen(false);
        setSelectedCardData(null);
    };

    const handleJobOffer = (data) => {
        setJobOffer(true);
        const jobDataFromProps = getJobDataFromPropsData(data.jobID);
        if (jobDataFromProps) {
            setSelectedCardData({ ...data, ...jobDataFromProps });
        } else {
            setSelectedCardData(data);
        }
        console.log("Selected card " + JSON.stringify(selectedCardData))
    };

    const handleJobOfferClose = () => {
        setJobOffer(false);
        setSelectedCardData(null);
    };

    return (
        <ThemeProvider theme={theme}>
            <div className={classes.root}>
                <Box className={classes.kanbanBoard}>
                    <div className={`${classes.column} ${classes.appliedColumn}`}>
                        <Typography variant="h6" className={classes.statusHeader}>
                            Applied
                        </Typography>
                        {applications.data?.applied_jobs.map((app) => {
                            const jobDataFromProps = getJobDataFromPropsData(app.jobID);
                            if (!jobDataFromProps || Object.keys(jobDataFromProps).length === 0) {
                                return null;
                            }
                            return (
                                <Grow in key={app.id}>
                                    <Paper className={classes.card} onClick={() => handleDialogOpen(app)}>
                                        <Typography variant="subtitle1" className={classes.companyName}>
                                            {jobDataFromProps?.company}
                                        </Typography>
                                        <Typography variant="body2" className={classes.position}>
                                            {jobDataFromProps?.position}
                                        </Typography>
                                    </Paper>
                                </Grow>
                            );
                        })}
                    </div>
                    <div className={classes.column}>
                        <Typography variant="h6" className={classes.statusHeader}>
                            Interview
                        </Typography>
                        {applications.data?.interview_jobs.map((app) => {
                            const jobDataFromProps = getJobDataFromPropsData(app.jobID);
                            if (!jobDataFromProps || Object.keys(jobDataFromProps).length === 0) {
                                return null;
                            }
                            return (
                                <Grow in key={app.id}>
                                    <Paper className={classes.card} onClick={() => handleDialogOpen(app)}>
                                        <Typography variant="subtitle1" className={classes.companyName}>
                                            {jobDataFromProps?.company}
                                        </Typography>
                                        <Typography variant="body2" className={classes.position}>
                                            {jobDataFromProps?.position}
                                        </Typography>
                                    </Paper>
                                </Grow>
                            );
                        })}
                    </div>
                    <div className={classes.column}>
                        <Typography variant="h6" className={classes.statusHeader}>
                            Job Offers
                        </Typography>
                        {applications.data?.accepted_jobs.map((app) => {
                            const jobDataFromProps = getJobDataFromPropsData(app.jobID);

                            console.log("Job Data: " + JSON.stringify(jobDataFromProps))
                            if (!jobDataFromProps || Object.keys(jobDataFromProps).length === 0) {
                                console.log("Inside if: " + app.jobID)
                                return null;
                            }
                            return (
                                <Grow in key={app.id}>
                                    <Paper className={classes.card} onClick={() => handleJobOffer(app)}>
                                        <Typography variant="subtitle1" className={classes.companyName}>
                                            {jobDataFromProps?.company}
                                        </Typography>
                                        <Typography variant="body2" className={classes.position}>
                                            {jobDataFromProps?.position}
                                        </Typography>
                                    </Paper>
                                </Grow>
                            );
                        })}
                    </div>
                    <div className={classes.column}>
                        <Typography variant="h6" className={classes.statusHeader}>
                            Rejected
                        </Typography>
                        {applications.data?.rejected_jobs.map((app) => {
                            const jobDataFromProps = getJobDataFromPropsData(app.jobID);
                            if (!jobDataFromProps || Object.keys(jobDataFromProps).length === 0) {
                                return null;
                            }
                            return (
                                <Grow in key={app.id}>
                                    <Paper className={classes.card} onClick={() => handleDialogOpen(app)}>
                                        <Typography variant="subtitle1" className={classes.companyName}>
                                            {jobDataFromProps?.company}
                                        </Typography>
                                        <Typography variant="body2" className={classes.position}>
                                            {jobDataFromProps?.position}
                                        </Typography>
                                    </Paper>
                                </Grow>
                            );
                        })}
                    </div>
                </Box>
                <Dialog open={isDialogOpen} onClose={handleDialogClose}>
                    <DialogTitle>Job Description</DialogTitle>
                    <DialogContent>
                        {selectedCardData &&
                            (
                                <JobDescription
                                    open={isDialogOpen}
                                    handleClose={handleDialogClose}
                                    data={selectedCardData}
                                    invoker="track"
                                    candidateData={JSON.parse(sessionStorage.getItem("AUTH_TOKEN"))}
                                />
                            )
                        }
                    </DialogContent>
                </Dialog>
            </div>
        </ThemeProvider>
    );
};

export default TrackApplications;
