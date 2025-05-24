import { useState } from 'react'
import '../../assets/style/input/input1.css'


function Input_1({label,onChangeValue,type,id,_value,style_1,style_2}){
    const [value, setValue] = useState(_value?_value:"");

    const handleChange = (e) => {
        const val = e.target.value;
        setValue(val);
        if (onChangeValue) onChangeValue(val); 
    };
    return <>
        <div className='input1__main'>
            <div className="input1__main--item">
                <input type={type} id={id} placeholder='' onChange={handleChange} style={style_2} value={value}  />
                <label htmlFor={id} style={style_1}>{label} </label>
            </div>
        </div>
    </>
}

export default Input_1