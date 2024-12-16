import React, { useState } from 'react';
import {
  Grid,
  Paper,
  Avatar,
  TextField,
  Button,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  FormHelperText,
} from '@material-ui/core';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import { Alert } from '@mui/material';
import { makeStyles } from '@material-ui/core/styles';

import { signUp } from '../services/registerAPI';

const useStyles = makeStyles((theme) => ({
  // Define the styles for the component using makeStyles hook
  paper: {
    padding: theme.spacing(4),
    width: '500px',
    margin: '0 auto',
  },
  avatar: {
    backgroundColor: theme.palette.primary.main,
    marginBottom: theme.spacing(2),
  },
  form: {
    width: '100%',
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
  errorText: {
    color: 'red',
  },
}));

const Signup = ({ loginCallBack }) => {
  const classes = useStyles();
  const [errMsg, setErrMsg] = useState('');
  const [accountType, setAccountType] = useState('employer');


  const handleChange = (event) => {
    console.log("In signup: " + event.target.value);
    setAccountType(event.target.value);
  };

  const initialValues = {
    name: '',
    email_address: '',
    phone_number: '',
    company_name: '',
    designation: '',
    age: '',
    gender: '',
    password: '',
    confirmPassword: '',
    registration_number: '',
  };

  const validationSchema = Yup.object().shape({
    name: Yup.string().min(3, "It's too short").required('Required'),
    company_name: Yup.string().min(0, 'Enter your company name'),
    designation: Yup.string().min(0, 'Enter your designation'),
    email_address: Yup.string().email('Enter valid email').required('Required'),
    phone_number: Yup.string().typeError('Enter valid Phone Number').required('Required'),
    password: Yup.string().min(8, 'Password minimum length should be 8').required('Required'),
    confirmPassword: Yup.string()
      .oneOf([Yup.ref('password')], 'Password not matched')
      .required('Required'),
    registration_number: Yup.string().length(5, "Registration number is 5 character long"),
    age: Yup.number().positive().integer().min(1, "Enter your age"),
    gender: Yup.string().oneOf(['male', 'female']),
  });

  const onSubmit = (values, props) => {
    console.log("entered")
    signUp({ ...values, last_name: values.name, userType: accountType })
      .then((data) => {
        console.log("Values: " + data)
        if (data.errors) {
          setErrMsg(data.errors[0]);
        } else {
          console.log("response: " + JSON.stringify(data))
          loginCallBack();
          props.resetForm();
          setErrMsg('');
        }
      })
      .catch(() => {
        setErrMsg('Unable to register');
      })
      .finally(() => {
        props.setSubmitting(false);
      });

  };

  return (
    <Grid style={{ height: '550px', overflow: 'auto' }}>
      <Paper className={classes.paper} elevation={0}>
        <Formik initialValues={initialValues} validationSchema={validationSchema} onSubmit={onSubmit}>
          {(props) => (
            <Form className={classes.form}>
              {errMsg !== '' && <Alert severity="error">{errMsg}</Alert>}
              <FormControl fullWidth className={classes.inputField}>
                <InputLabel id="account-type-label">Account Type</InputLabel>
                <Select
                  labelId="account-type-label"
                  id="account-type"
                  value={accountType}
                  onChange={handleChange}
                >
                  <MenuItem value="employer">Employer</MenuItem>
                  <MenuItem value="candidate">Candidate</MenuItem>
                </Select>
              </FormControl>
              <Field
                required
                as={TextField}
                fullWidth
                name="name"
                label="Name"
                placeholder="Enter your name"
                className={classes.inputField}
                error={props.errors.name && props.touched.name}
                helperText={<ErrorMessage name="name" className={classes.errorText} />}
              />
              <Field
                required
                as={TextField}
                fullWidth
                name="email_address"
                label="Email"
                placeholder="Enter your email"
                className={classes.inputField}
                error={props.errors.email_address && props.touched.email_address}
                helperText={<ErrorMessage name="email_address" className={classes.errorText} />}
              />
              <Field
                required
                as={TextField}
                fullWidth
                name="phone_number"
                label="Phone Number"
                placeholder="Enter your phone number"
                className={classes.inputField}
                error={props.errors.phone_number && props.touched.phone_number}
                helperText={<ErrorMessage name="phone_number" className={classes.errorText} />}
              />
              {accountType === 'employer' && (
                <>
                  <Field
                    required
                    as={TextField}
                    fullWidth
                    name="company_name"
                    label="Company Name"
                    placeholder="Enter your company name"
                    className={classes.inputField}
                    error={props.errors.company_name && props.touched.company_name}
                    helperText={<ErrorMessage name="company_name" className={classes.errorText} />}
                  />
                  <Field
                    required
                    as={TextField}
                    fullWidth
                    name="designation"
                    label="Designation"
                    placeholder="Enter your designation"
                    className={classes.inputField}
                    error={props.errors.designation && props.touched.designation}
                    helperText={<ErrorMessage name="designation" className={classes.errorText} />}
                  />
                  <Field
                    as={TextField}
                    fullWidth
                    name="registration_number"
                    label="Registration Number"
                    placeholder="Enter your Registration number"
                    className={classes.inputField}
                    error={props.errors.registration_number && props.touched.registration_number}
                    helperText={
                      <ErrorMessage name="registration_number" className={classes.errorText} />
                    }
                  />
                </>
              )}
              {accountType === 'candidate' && (
                <>
                  <Field
                    required
                    as={TextField}
                    fullWidth
                    name="age"
                    label="Age"
                    placeholder="Enter your Age"
                    className={classes.inputField}
                    error={props.errors.age && props.touched.age}
                    helperText={<ErrorMessage name="age" className={classes.errorText} />}
                  />
                  <Field
                    required
                    as={TextField}
                    fullWidth
                    name="gender"
                    select 
                    label="Gender"
                    className={classes.inputField}
                    error={props.errors.gender && props.touched.gender}
                    helperText={<ErrorMessage name="gender" className={classes.errorText} />}
                  >
                    <MenuItem value="male">Male</MenuItem>
                    <MenuItem value="female">Female</MenuItem>
                  </Field>
                </>
              )}
              <Field
                required
                as={TextField}
                fullWidth
                name="password"
                type="password"
                label="Password"
                placeholder="Enter your password"
                className={classes.inputField}
                error={props.errors.password && props.touched.password}
                helperText={<ErrorMessage name="password" className={classes.errorText} />}
              />
              <Field
                required
                as={TextField}
                fullWidth
                name="confirmPassword"
                type="password"
                label="Confirm Password"
                placeholder="Confirm your password"
                className={classes.inputField}
                error={props.errors.confirmPassword && props.touched.confirmPassword}
                helperText={<ErrorMessage name="confirmPassword" className={classes.errorText} />}
              />
              <Button
                fullWidth
                type="submit"
                variant="contained"
                color="primary"
                className={classes.submitButton}
                disabled={props.isSubmitting}
              >
                {props.isSubmitting ? 'Loading' : 'Sign up'}
              </Button>
            </Form>
          )}
        </Formik>
      </Paper>
    </Grid>
  );
};

export default Signup;
