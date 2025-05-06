import axios from 'axios';

export async function load_home_api(){
    try {
        const res = await axios('http://localhost:9002/product-service/api/v1/home-page',{
          method:"GET",
          headers: {
            'Content-Type': 'application/json'
          },
          withCredentials: true
        })
      return [res.data._result,true];
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