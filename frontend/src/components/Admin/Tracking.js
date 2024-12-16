import React, { useState, useEffect } from "react";
import { makeStyles } from '@material-ui/core/styles';
import { Grid, Box, CssBaseline, Paper, Typography } from "@material-ui/core";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'react-chartjs-2';
import TrackApplications from "./TrackApplications";
import { adminReport, adminReporting } from '../../services/registerAPI';

const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
        flexDirection: 'row', // Arrange components side by side
    },
    paper: {
        padding: theme.spacing(3),
        marginTop: theme.spacing(10),
        display: 'flex',
        flexDirection: 'column',
        backgroundColor: "#fafafaeb"
        //alignItems: 'center',
    },
    countContainer: {
        display: 'flex',
        justifyContent: 'space-between',
        // alignItems: 'center',
        marginTop: theme.spacing(30),
    },
    chartContainer: {
        display: 'flex',
        justifyContent: 'left',
        width: "300px",
        height: "300px",
        // alignItems: 'center',
        margin: theme.spacing(2),
    },
    componentContainer: {
        margin: theme.spacing(2), // Add margin between components
    },
    centerTypography1: {
        textAlign: 'center',
        marginTop: theme.spacing(12),
        marginBottom: theme.spacing(-6),
        marginLeft: theme.spacing(-10),
        marginRight: theme.spacing(20),
    },
    centerTypography2: {
        textAlign: 'center',
        marginTop: theme.spacing(12),
        marginBottom: theme.spacing(-8),
        marginLeft: theme.spacing(-10),
        marginRight: theme.spacing(20),
    },
    widePaper: {
        width: '111.3%', // Adjust this value as needed
        padding: theme.spacing(1),
        backgroundColor: "#fafafaeb",
    },
}));

const chartOptions = {
    responsive: true, // Enable responsiveness
    maintainAspectRatio: false, // Set to false if you want the chart to scale based on container size
    legend: {
        display: true,
        position: 'right', // or 'bottom', 'top', 'left'
    },
};


function JobCount({ postedJobs, activeJobs, inactiveJobs, classes }) {
    const jobData = {
        labels: ['Posted Jobs', 'Active Jobs', 'Inactive Jobs'],
        datasets: [
            {
                data: [postedJobs, activeJobs, inactiveJobs],
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                hoverOffset: 4
            },
        ],
    };

    return (
        <div className={classes.componentContainer}>
            <Paper className={classes.paper}>
                <Typography variant="h5">Job Count</Typography>
                <div className={classes.chartContainer}>
                    <Doughnut data={jobData} options={chartOptions} />
                </div>
            </Paper>
        </div>
    );
}

function UserCount({ candidateCount, employerCount, classes }) {
    const userData = {
        labels: ['Candidate Count', 'Employer Count'],
        datasets: [
            {
                data: [candidateCount, employerCount],
                backgroundColor: ['#FF6384', '#36A2EB'],
                hoverBackgroundColor: ['#FF6384', '#36A2EB'],
                hoverOffset: 4
            },
        ],
    };

    return (
        <div className={classes.componentContainer}>
            <Paper className={classes.paper}>
                <Typography variant="h5">User Count</Typography>
                <div className={classes.chartContainer}>
                    <Doughnut data={userData} options={chartOptions} />
                </div>
            </Paper>
        </div>
    );
}

function Tracking({ data }) {
    const classes = useStyles();
    // Register the necessary Chart.js components
    ChartJS.register(ArcElement, Tooltip, Legend);

    const [errMsg, setErrMsg] = useState('');
    const [statistics, setStatistics] = useState({});

    const fetchDataFromAPI = async () => {
        // Function to fetch application data from the API
        adminReporting()
            .then((data) => {
                if (data.errors) {
                    setErrMsg(data.errors[0]);
                } else {
                    console.log("Data: " + JSON.stringify(data))
                    setStatistics(data)
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

    const jobCountProps = {
        postedJobs: statistics?.data?.jobs_count?.posted_jobs || 0,
        activeJobs: statistics?.data?.jobs_count?.active_jobs || 0,
        inactiveJobs: statistics?.data?.jobs_count?.inactive_jobs || 0,
        classes: classes,
    };

    const userCountProps = {
        candidateCount: statistics?.data?.users_count?.candidate_count || 0,
        employerCount: statistics?.data?.users_count?.employer_count || 0,
        classes: classes,
    };
    return (
        <div className={classes.centerContainer}>
            <div className={classes.centerTypography1}>
                <Paper className={classes.widePaper}>
                    <Typography variant="h4">Job Tracking</Typography>
                </Paper>
            </div>
            <TrackApplications
                data={data}
                candidateData={JSON.parse(sessionStorage.getItem("AUTH_TOKEN"))}
            />
            <Grid>
                <div className={classes.centerTypography2}>
                    <Paper className={classes.widePaper}>
                        <Typography variant="h4">Statistics</Typography>
                    </Paper>
                </div>
                <Box className={classes.root}>
                    <JobCount {...jobCountProps} />
                    <UserCount {...userCountProps} />
                </Box>
            </Grid>
        </div>
    );
}

export default Tracking;