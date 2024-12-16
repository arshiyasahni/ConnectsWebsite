import React, { useState } from 'react';
import {
  Grid,
  Paper,
  Avatar,
  TextField,
  Button,
  Typography,
  Link,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
} from '@material-ui/core';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import { login } from '../services/registerAPI';
import { Alert } from '@mui/material';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { makeStyles } from '@material-ui/core/styles';


const useStyles = makeStyles((theme) => ({
  // Define the styles for the component using makeStyles hook
  paper: {
    padding: theme.spacing(4),
    width: '500px', // Increase form width
    margin: '0 auto',
  },
  avatar: {
    backgroundColor: theme.palette.primary.main,
    marginBottom: theme.spacing(2),
  },
  form: {
    width: '100%',
  },
  formContainer: {
    padding: theme.spacing(4),
    width: '500px', // Increase form width
    margin: '0 auto',
  },
  inputField: {
    marginBottom: theme.spacing(2),
  },
  submitButton: {
    margin: theme.spacing(2, 0),
    backgroundColor: '#fd8a88',
    '&:hover': {
      backgroundColor: '#de4340',
    },
  },
  textField: {
    width: '100%',
    fontSize: '1.2rem',
  },
  select: {
    width: '100%',
    fontSize: '1.2rem',
  }
}));

const Login = ({ handleChange, loginCallBack }) => {
  const classes = useStyles();
  const [errMsg, setErrMsg] = useState('');
  const [userType, setAccountType] = useState('employer');

  const accountHandleChange = (event) => {
    setAccountType(event.target.value);
  };

  const initialValues = {
    email: '',
    password: '',
  };

  const validationSchema = Yup.object().shape({
    email: Yup.string().email('Please enter a valid email').required('Required'),
    password: Yup.string().required('Required'),
  });

  const onSubmit = (values, props) => {
    login({ ...values, userType: userType })
      .then((data) => {
        if (data.logged) {
          loginCallBack();
          props.resetForm();
          setErrMsg('');
        } else {
          setErrMsg('Username or password is incorrect');
        }
      })
      .catch(() => {
        setErrMsg('Unable to login');
      })
      .finally(() => {
        props.setSubmitting(false);
      });
  };

  return (
    <Grid>
      <Paper className={`${classes.paper} ${classes.formContainer}`} elevation={0}>
        <Formik initialValues={initialValues} onSubmit={onSubmit} validationSchema={validationSchema}>
          {(props) => (
            <Form className={classes.form}>
              {errMsg !== '' && <Alert severity="error">{errMsg}</Alert>}
              <FormControl fullWidth className={classes.inputField}>
                <InputLabel id="account-type-label" className={classes.formText}>Account Type</InputLabel>
                <Select
                  labelId="account-type-label"
                  id="account-type"
                  value={userType}
                  onChange={accountHandleChange}
                  className={classes.select}
                >
                  <MenuItem value="employer">Employer</MenuItem>
                  <MenuItem value="candidate">Candidate</MenuItem>
                  <MenuItem value="admin">Administrator</MenuItem>
                </Select>
              </FormControl>
              <Field
                as={TextField}
                label="Email"
                name="email"
                placeholder="Enter email"
                fullWidth
                required
                className={classes.inputField}
                error={props.errors.email && props.touched.email}
                helperText={<ErrorMessage name="email" />}
                inputProps={{

                  className: classes.textField,
                }}
              />
              <Field
                as={TextField}
                label="Password"
                name="password"
                placeholder="Enter password"
                type="password"
                fullWidth
                required
                className={classes.inputField}
                error={props.errors.password && props.touched.password}
                helperText={<ErrorMessage name="password" />}
                inputProps={{
                  className: classes.textField,
                }}
              />
              <Button
                type="submit"
                variant="contained"
                color="primary"
                className={classes.submitButton}
                disabled={props.isSubmitting}
                fullWidth
              >
                {props.isSubmitting ? 'Loading' : 'Sign In'}
              </Button>
              <Grid container>
                <Grid item xs>
                  <Link href="#" variant="body2">
                    Forgot password?
                  </Link>
                </Grid>
              </Grid>
            </Form>
          )}
        </Formik>
      </Paper>
    </Grid>
  );
};

export default Login;
