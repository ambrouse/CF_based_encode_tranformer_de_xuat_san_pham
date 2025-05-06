import { useState } from 'react'
import '../../assets/style/input/input1.css'


function Input_1({label,onChangeValue,type,id,_value}){
    const [value, setValue] = useState(_value);

    const handleChange = (e) => {
        const val = e.target.value;
        setValue(val);
        if (onChangeValue) onChangeValue(val); 
    };
    return <>
        <div className='input1__main'>
            <div className="input1__main--item">
                <input type={type} id={id} placeholder='' onChange={handleChange}  />
                <label htmlFor={id} >{label} </label>
            </div>
        </div>
    </>
}

// export function getCurrentDateTimeLocal() {
//     const now = new Date();
  
//     const year = now.getFullYear();
//     const month = String(now.getMonth() + 1).padStart(2, "0"); // Tháng bắt đầu từ 0
//     const day = String(now.getDate()).padStart(2, "0");
  
//     const hours = String(now.getHours()).padStart(2, "0");
//     const minutes = String(now.getMinutes()).padStart(2, "0");
  
//     return `${year}-${month}-${day}T${hours}:${minutes}`;
// }

export default Input_1