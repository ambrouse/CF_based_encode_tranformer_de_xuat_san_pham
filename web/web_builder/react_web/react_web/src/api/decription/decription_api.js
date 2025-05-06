import axios from 'axios';

export async function load_decription_api(_id){
    try {
        const res = await axios('http://localhost:9002/product-service/api/v1/desription-product/'+_id,{
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

export async function add_card(data){
  try {
      const res = await axios('http://localhost:9002/card-service/api/v1/add-card',{
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


export async function add_coment(data){
  try {
    console.log(data)
      const res = await axios('http://localhost:9002/product-service/api/v1/coment',{
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



export async function delete_coment(_id){
  try {
    const res = await axios('http://localhost:9002/product-service/api/v1/coment/'+_id,{
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