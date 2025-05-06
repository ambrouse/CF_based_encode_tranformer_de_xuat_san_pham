import { useState } from 'react'
import '../../assets/style/button/button_1.css'


function Button_1({value,onclick_function,_style1,_style2}){
    return <>
    <div className="button__btn1--item" style={_style1}>
        <button onClick={onclick_function} style={_style2} >{value}</button>
    </div>
    </>
}


export default Button_1