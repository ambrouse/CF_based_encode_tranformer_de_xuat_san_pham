import { useEffect, useState } from 'react'
import axios from 'axios';
export default function Addimg_page(){

    const [file, setFile] = useState(null);
    const [id, setid] = useState(null);
    const [imgSrc, setImgSrc] = useState(null);

    const handleFileChange = (e) => {
        const selectedFile = e.target.files[0];
        setFile(selectedFile);
        console.log('File đã chọn:', selectedFile);
      };
    const handleFileChange_size_color = (e) => {
        const selectedFile = e.target.value[0];
        setid(selectedFile);
        console.log('id size color:', selectedFile);
      };
    async function click(){
        const arrayBuffer = await file.arrayBuffer();
        const byteArray = Array.from(new Uint8Array(arrayBuffer)); // chuyển sang byte[]
        console.log(byteArray)
        console.log(id)

        try {
            const res = await axios('http://localhost:9002/product-service/api/v1/add-img',{
              method:"POST",
              headers: {
                'Content-Type': 'application/json'
              },
              data:JSON.stringify(
                {
                    "_img":byteArray,
                    "_id_manager_size_color":id
                }
              ),
            })
            console.log(res.data._result)
            setImgSrc("http://localhost:9005/product-service/api/v1/get-img/"+res.data._result)
        } catch (error) {
          if (error.response) {
            console.error('Server responded with error:', error.response);
          }
          else if (error.request) {
            console.error('No response received:', error.request);
          }
          else {
            console.error('Axios error:', error.message);
          }
        }
        
    }

    return<>
        <input type="file" onChange={handleFileChange} />
        <input type="text" onChange={handleFileChange_size_color} />
        <button onClick={click}>add img</button>
        <img src={imgSrc} alt="Ảnh từ server" />
    </>
}