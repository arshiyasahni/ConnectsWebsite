import axios from 'axios';
import { URLS, BASE_ULR } from '../config/constant';
export const login = ({ email, password, userType }) => axios.get(URLS.login, {
  params: {
    email,
    password,
    userType
  }
}).then(
  auth => {
    if (auth.data.data.logged) {
      sessionStorage.setItem("AUTH_TOKEN", JSON.stringify({ ...auth.data.data, userType }));
    }
    return auth.data.data;
  }
);

export const signUp = (userData) => {
  let register_url = URLS.register_employer;

  let data = {
    name: userData.name,
    last_name: userData.last_name,
    phone_number: userData.phone_number,
    email_address: userData.email_address,
    password: userData.password
  }

  if (userData.userType === 'employer') {
    register_url = URLS.register_employer;
    data.company_name = userData.company_name
    data.designation = userData.designation
    data.registration_number = userData.registration_number
  }
  if (userData.userType === 'candidate') {
    register_url = URLS.register_candidate;
    data.age = userData.age
    data.gender = userData.gender
  }
  console.log("data " + JSON.stringify(data))
  return axios.post(register_url, data).then(
    auth => {
      if (auth.data.errors === undefined) {
        sessionStorage.setItem("AUTH_TOKEN", JSON.stringify({ ...auth.data.data, userType: userData.userType }));
      } else {
        return auth.data
      }
      return auth.data;
    }
  );
}

export const editProfile = (userData) => {
  let edit_url = URLS.edit_employer;

  let data = {
    name: userData.name,
    last_name: userData.last_name,
    phone_number: userData.phone_number,
    email_address: userData.email_address,
    password: userData.password
  }

  if (userData.userType === 'employer') {
    edit_url = URLS.edit_employer;
    data.company_name = userData.company_name
    data.designation = userData.designation
    data.registration_number = userData.registration_number
  }
  if (userData.userType === 'candidate') {
    edit_url = URLS.edit_candidate;
    data.age = userData.age
    data.gender = userData.gender
  }
  console.log("data " + JSON.stringify(data))
  return axios.post(edit_url, data).then(
    auth => {
      if (auth.data.errors === undefined) {
        sessionStorage.setItem("AUTH_TOKEN", JSON.stringify({ ...auth.data.data, userType: userData.userType }));
      } else {
        return auth.data
      }
      return auth.data;
    }
  );
}

export const logOut = () => {
  sessionStorage.removeItem("AUTH_TOKEN");
}

export const getUserInfo = () => JSON.parse(sessionStorage.getItem("AUTH_TOKEN"));

export const jobListings = () => {
  let register_url = URLS.candidate_get_job_listings;
  return axios.get(register_url).then(
    auth => {
      return auth.data;
    }
  );
}

export const candidateListings = () => {
  let candidate_listing_url = URLS.employer_get_all_candidates;
  return axios.get(candidate_listing_url).then(
    auth => {
      return auth.data;
    }
  );
}

export const applyCandidateJobs = (data) => {
  let register_url = URLS.candidate_apply_job;
  return axios.post(register_url, data).then(
    auth => {
      console.log(JSON.stringify(data));
      return auth.data;
    }
  );
}

export const candidateTracking = (email_address) => {
  let register_url = URLS.candidate_tracking + "?email_address=" + email_address;
  return axios.get(register_url).then(
    auth => {
      return auth.data;
    }
  );
}


export const getCandidateResume = (email_address) => {
  let candidate_resume = URLS.candidate_resume + "?email_address=" + email_address;
  return axios.get(candidate_resume).then(
    auth => {
      return auth.data;
    }
  );
}


export const deleteCandidate = (email_address) => {
  let register_url = URLS.admin_delete_candidate + "?email_address=" + email_address;
  return axios.delete(register_url).then(
    auth => {
      return auth.data;
    }
  );
}

export const deleteEmployer = (email_address) => {
  let register_url = URLS.admin_delete_employer + "?employer_email=" + email_address;
  return axios.delete(register_url).then(
    auth => {
      return auth.data;
    }
  );
}

export const employerListings = () => {
  let admin_get_all_employers = URLS.admin_get_all_employers;
  return axios.get(admin_get_all_employers).then(
    auth => {
      return auth.data;
    }
  );
}

export const deleteJobPosting = (job_id) => {
  let register_url = URLS.admin_delete_job_posting + "?job_id=" + job_id;
  return axios.delete(register_url).then(
    auth => {
      return auth.data;
    }
  );
}


export const employerTracking = (email_address) => {
  let register_url = URLS.employer_tracking + "?employer_email=" + email_address;
  return axios.get(register_url).then(
    auth => {
      return auth.data;
    }
  );
}

export const adminTracking = () => {
  let register_url = URLS.admin_tracking;
  return axios.get(register_url).then(
    auth => {
      return auth.data;
    }
  );
}

export const adminReporting = () => {
  let register_url = URLS.admin_report;
  return axios.get(register_url).then(
    auth => {
      return auth.data;
    }
  );
}