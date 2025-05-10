import axios from 'axios';
import { data } from 'react-router-dom';

export async function axios_login(_login_value) {
  try {
      const res = await axios('http://localhost:9002/auth-service/api/v1/login',{
        method:"POST",
        headers: {
          'Content-Type': 'application/json'
        },
        data:JSON.stringify(_login_value),
        withCredentials: true
      })
    return [res.data._result._status,true];
  } catch (error) {
    if (error.response) {
      // console.error('Server responded with error:', error.response);
      return [error.response.data._result,false]
    }
    else if (error.request) {
      return [error.request,false]
      // console.error('No response received:', error.request);
    }
    else {
      return ["error.response.data._result",false]
      // console.error('Axios error:', error.message);
    }
  }
}

export async function axios_sigup(_sigup_value) {
  try {
      const res = await axios('http://localhost:9002/auth-service/api/v1/sign-up',{
        method:"POST",
        headers: {
          'Content-Type': 'application/json'
        },
        data:JSON.stringify(_sigup_value),
        withCredentials: true
      })
    return [res.data._result._status,true];
  } catch (error) {
    if (error.response) {
      // console.error('Server responded with error:', error.response);
      return [error.response.data._result,false]
    }
    else if (error.request) {
      return [error.request,false]
      // console.error('No response received:', error.request);
    }
    else {
      return ["error.response.data._result",false]
      // console.error('Axios error:', error.message);
    }
  }
}
