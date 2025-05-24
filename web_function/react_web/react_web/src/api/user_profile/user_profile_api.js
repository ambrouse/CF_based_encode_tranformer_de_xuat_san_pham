import axios from 'axios';

export async function load_user_api(){
    try {
        const res = await axios('http://localhost:9002/user-service/api/v1/profile',{
          method:"GET",
          headers: {
            'Content-Type': 'application/json'
          },
          withCredentials: true
        })
      return [res.data._result,true];
    } catch (error) {
      if (error.response) {
        return [error.response.data._result,false]
      }
      else if (error.request) {
        return [error.request,false]
      }
      else {
        return ["error.response.data._result",false]
      }
    }
}

export async function update_user_api(data){
    try {
        const res = await axios('http://localhost:9002/user-service/api/v1/profile',{
          method:"PUT",
          headers: {
            'Content-Type': 'application/json'
          },
          data:JSON.stringify(data),
          withCredentials: true
        })
      return [res.data._result,true];
    } catch (error) {
      if (error.response) {
        return [error.response.data._result,false]
      }
      else if (error.request) {
        return [error.request,false]
      }
      else {
        return ["error.response.data._result",false]
      }
    }
}

export async function update_email_api(data){
    try {
        const res = await axios('http://localhost:9002/user-service/api/v1/profile-email',{
          method:"PUT",
          headers: {
            'Content-Type': 'application/json'
          },
          data:JSON.stringify(data),
          withCredentials: true
        })
      return [res.data._result,true];
    } catch (error) {
      if (error.response) {
        return [error.response.data._result,false]
      }
      else if (error.request) {
        return [error.request,false]
      }
      else {
        return ["error.response.data._result",false]
      }
    }
}

export async function update_address_api(data){
    try {
        const res = await axios('http://localhost:9002/user-service/api/v1/profile-address',{
          method:"POST",
          headers: {
            'Content-Type': 'application/json'
          },
          data:JSON.stringify(data),
          withCredentials: true
        })
      return [res.data._result,true];
    } catch (error) {
      if (error.response) {
        return [error.response.data._result,false]
      }
      else if (error.request) {
        return [error.request,false]
      }
      else {
        return ["error.response.data._result",false]
      }
    }
}


export async function delete_address_api(_id){
    try {
        const res = await axios('http://localhost:9002/user-service/api/v1/profile-address/'+_id,{
          method:"DELETE",
          headers: {
            'Content-Type': 'application/json'
          },
          withCredentials: true
        })
      return [res.data._result,true];
    } catch (error) {
      if (error.response) {
        return [error.response.data._result,false]
      }
      else if (error.request) {
        return [error.request,false]
      }
      else {
        return ["error.response.data._result",false]
      }
    }
}


export async function update_img_api(data){
    try {
        const res = await axios('http://localhost:9002/user-service/api/v1/profile-img',{
          method:"PUT",
          headers: {
            'Content-Type': 'application/json'
          },
          data:JSON.stringify(data),
          withCredentials: true
        })
      return [res.data._result,true];
    } catch (error) {
      if (error.response) {
        return [error.response.data._result,false]
      }
      else if (error.request) {
        return [error.request,false]
      }
      else {
        return ["error.response.data._result",false]
      }
    }
}