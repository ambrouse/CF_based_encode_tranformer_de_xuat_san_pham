import { useState } from 'react'
import '../../assets/style/button/button_2.css'


function Button_2({value,onclick_function,_style1,_style2}){
    return <>
    <div className="button__btn2--item" style={_style1}>
        <button onClick={onclick_function} style={_style2} >{value}</button>
    </div>
    </>
}


export default Button_2