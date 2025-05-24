import axios from 'axios';

export async function load_cart(){
    try {
        const res = await axios('http://localhost:9002/card-service/api/v1/card-user',{
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

export async function update_mini_card(data){
    try {
        const res = await axios('http://localhost:9002/card-service/api/v1/card-user',{
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

export async function apply_cart(data){
    try {
        const res = await axios('http://localhost:9002/card-service/api/v1/card-user-apply',{
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

export async function delete_mini_card(_id){
    try {
        const res = await axios('http://localhost:9002/card-service/api/v1/card-user/'+_id,{
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