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

export default Input_1