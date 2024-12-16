import axios from 'axios';
import React, { useState } from 'react';
import './App.css';
import Home from './components/Home';
import { BASE_ULR, URLS } from './config/constant';
import SignInRegisterContainer from './containers/sign_in_sign_up_container';
import { getUserInfo } from './services/registerAPI';
import JobApplications from "./components/Employer/JobApplications";
import MyJobs from "./components/Employer/MyJobs";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";

const App = () => {

  const [loggedIn, setLoggedIn] = useState(getUserInfo() !== null);
  axios.defaults.baseURL = BASE_ULR
  axios.defaults.headers.post['Content-Type'] = 'application/json';

  axios.interceptors.request.use(function (config) {
    if (config.url !== URLS.login &&
      config.url !== URLS.register_employer &&
      config.url !== URLS.register_candidate) {
      if (sessionStorage.getItem("AUTH_TOKEN") !== null) {
        //config.headers.common['Authorization'] = sessionStorage.getItem("AUTH_TOKEN");
      }
    }
    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  });
  return (
    <Router>
    <div className="App" style={{ height: '100%' }}>

      {loggedIn ? <Home loginCallBack={() => {
        setLoggedIn(sessionStorage.getItem("AUTH_TOKEN") !== null);
      }} /> :
        <SignInRegisterContainer loginCallBack={() => {
          setLoggedIn(sessionStorage.getItem("AUTH_TOKEN") !== null);
        }}></SignInRegisterContainer>


      }
      </div>
      <>
      <Switch>
        <Route exact path="/JobApplications/:jobId" component={JobApplications} >
        </Route>
        <Route exact path="/MyJobs" component={MyJobs} >
        </Route>
      </Switch>

      </>

    </Router>
  );
}

export default App;
