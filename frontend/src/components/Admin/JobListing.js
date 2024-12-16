import React, { useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { deleteJobPosting } from '../../services/registerAPI';

const useStyles = makeStyles(theme => ({
    centerContainer: {
        display: 'flex',
        flexDirection: 'column',
        minHeight: '100vh',
    },
    userList: {
        display: 'flex', // Use flex to align items
        flexDirection: 'column', // Stack items vertically
        //alignItems: 'center', // Align items to the top center
        marginTop: theme.spacing(12),
        marginRight: theme.spacing(10),
    },
    userListContainer: {
        marginTop: theme.spacing(2),
    },
    userItem: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'left',
        padding: theme.spacing(2),
        borderBottom: '1px solid #ccc',
        backgroundColor: '#fffdfa', // Set the background color for all user items
        borderRadius: theme.spacing(1), // Adjust the value as needed
        cursor: 'pointer', // Add cursor: pointer to make the userItem hover on cursor
        '&:hover': {
            backgroundColor: '#ffe4e6', // Change background color on hover
        },

    },
    userEmail: {
        //fontWeight: 'bold',
        color: '#000000', // Set the color for all email addresses
    },
    deleteUserButton: {
        color: 'red',
        cursor: 'pointer',
    },
    searchContainer: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom: theme.spacing(3),
    },
    searchIcon: {
        marginRight: theme.spacing(1),
    },
    errorMsg: {
        backgroundColor: '#fffdfa',
        color: 'red',
        padding: theme.spacing(1),
        marginBottom: theme.spacing(2),
        marginBottom: theme.spacing(2),
        borderRadius: theme.spacing(1), // Adjust the value as needed
    },
}));

function CandidateListing({ data }) {
    const classes = useStyles();
    const initialUserList = data;
    const [errMsg, setErrMsg] = useState('');
    const [userList, setUserList] = useState(initialUserList);

    const [searchTerm, setSearchTerm] = useState('');

    const fetchDataFromAPI = async (userData) => {
        deleteJobPosting(userData.id)
            .then((data) => {
                console.log("Delete " + JSON.stringify(data))
                if (data.errors) {
                    setErrMsg(data.errors[0]);
                } else {
                    const updatedUserList = userList.filter(user => user.id !== userData.id);
                    setUserList(updatedUserList);
                    setErrMsg(data.data);
                }
            })
            .catch(() => {
                setErrMsg('Unable to delete');
            });
        // Clear error message after 5 seconds
        setTimeout(() => {
            setErrMsg('');
        }, 2000);
    };

    const handleDeleteUser = (userData) => {
        fetchDataFromAPI(userData);
        // const updatedUserList = userList.filter(user => user.id !== id);
        //setUserList(updatedUserList);
    };

    const handleSearch = (event) => {
        const searchValue = event.target.value.toLowerCase();
        setSearchTerm(searchValue);

        // Filter the initialUserList based on the search term
        const filteredList = initialUserList.filter(user =>
            user.id.toString().includes(searchValue) || // Convert to string and check for matching job ID
            user.company.toLowerCase().includes(searchValue) || // Check for matching company name
            user.position.toLowerCase().includes(searchValue) // Check for matching position
        );

        setUserList(filteredList);
    };

    return (
        <div className={classes.centerContainer}>
            <div className={classes.userList}>
                <div className={classes.searchContainer}>
                    <svg
                        className={classes.searchIcon}
                        width="16"
                        height="16"
                        viewBox="0 0 16 16"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                        <path
                            fill-rule="evenodd"
                            clip-rule="evenodd"
                            d="M11.7998 10.7998H12.4998L15.6998 14.9998L14.2998 16.3998L10.0998 12.1998V11.4998L9.84979 11.2498C8.99979 12.0998 7.79979 12.5998 6.49979 12.5998C3.14979 12.5998 0.499786 9.94976 0.499786 6.59976C0.499786 3.24976 3.14979 0.599762 6.49979 0.599762C9.84979 0.599762 12.4998 3.24976 12.4998 6.59976C12.4998 7.89976 11.9998 9.09978 11.1498 9.94978L11.3998 10.1998H11.7998V10.7998ZM6.49979 10.5998C8.82979 10.5998 10.6998 8.72977 10.6998 6.59976C10.6998 4.46976 8.82979 2.59976 6.49979 2.59976C4.36979 2.59976 2.49979 4.46976 2.49979 6.59976C2.49979 8.72977 4.36979 10.5998 6.49979 10.5998Z"
                            fill="#007bff"
                        />
                    </svg>
                    {/* Add the search input */}
                    <input
                        type="text"
                        value={searchTerm}
                        onChange={handleSearch}
                        placeholder="Search by Job ID/Company/Position"
                        style={{ width: '300px' }} // Adjust the width value as needed
                    />

                </div>
                {errMsg && (
                    <div className={classes.errorMsg}>
                        {errMsg}
                    </div>
                )}
                <div className={classes.userItem}>
                    <div className={classes.userEmail}><strong>Job ID</strong></div>
                    <div className={classes.userEmail}><strong>Company</strong></div>
                    <div className={classes.userEmail}><strong>Position</strong></div>
                    <div className={classes.userEmail}><strong>Action</strong></div>
                </div>
                {userList.length === 0 && (
                    <div className={classes.userItem}>
                        No data found.
                    </div>
                )}
                {userList.map(user => (
                    <div key={user.id} className={classes.userItem}>
                        <div className={classes.userEmail}>{user.id}</div>
                        <div className={classes.userEmail}>{user.company}</div>
                        <div className={classes.userEmail}>{user.position}</div>
                        <div
                            className={classes.deleteUserButton}
                            onClick={() => handleDeleteUser(user)}
                        >
                            Delete
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default CandidateListing;