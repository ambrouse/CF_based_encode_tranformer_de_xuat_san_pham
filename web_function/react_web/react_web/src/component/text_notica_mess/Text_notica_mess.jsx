import { useState } from 'react'
import '../../assets/style/text/text_notica.css'

function Text_notica_mess(props){
    
    return  <>
        <div className="text__notica">
            <p style={{color:props.color}} >{props.value}</p>
        </div>
    </>
}


export default Text_notica_mess