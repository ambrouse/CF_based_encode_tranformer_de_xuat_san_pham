import { useState } from 'react'
import '../assets/style/authen_page/login.css'
import Button_1 from '../component/button/Button_1'
import Button_2 from '../component/button/button_2'
import Input_1 from '../component/input/Input_1'
import translatelafinform from '../ultils/translateloginform'
import {axios_login,axios_sigup} from '../api/authen/authen_api'
import Text_notica_mess from '../component/text_notica_mess/Text_notica_mess'


function Authen(){

    const [_mess_login,_set_mess_login] = useState([null,null])
    const [_mess_sigup,_set_mess_sigup] = useState([null,null])
    const [_login,_set_login] = useState({
        "_email":"",
        "_password":""
    })
    const [_sigup,_set_sigup] = useState({
        "_gmail": "",
        "_name": "",
        "_age": 0,
        "_time_of_birth": null,
        "_password": "",
        "_phone": ""
      })
    
    async function login_func(){
        axios_login(_login).then(a=>{
            if(a[1]){_set_mess_login([a,"black"])}else{_set_mess_login([a,"red"])}
        })
    }
    async function sigup_func() {
        axios_sigup(_sigup).then(a=>{
            if(a[1]){_set_mess_sigup([a,"black"])}else{_set_mess_sigup([a,"red"])}
        })
    }



    return <>
       <div className="login__container">
            <div className="login__container--form">
                <div className="form__container">
                    <div className="form__container--item">
                            <div className="item__title">
                                <h1>Login form</h1>
                            </div>
                            <div className="item__input">
                                <Input_1 label="Email" type="email" id="login_email_input" onChangeValue={(val) => {
                                    _login._email = val
                                    _set_login(_login)
                                }}/>
                            </div>
                            <div className="item__input">
                                <Input_1 label="Password" type="password" id="login_password_input" onChangeValue={(val) => {
                                    _login._password = val
                                    _set_login(_login)
                                }}/>
                            </div>
                            <div className="item__err">
                                <Text_notica_mess value={_mess_login[0]} color={_mess_login[1]}></Text_notica_mess>
                            </div>
                            <div className="item__btn">
                                <Button_1 value="Login" onclick_function={login_func} _style={{width:"100%"}} ></Button_1>
                            </div>
                            <div className="item__btn">
                                <Button_1 value="Sigup" _style={{width:"100%"}} onclick_function={() => translatelafinform(0)} ></Button_1>
                            </div>
                    </div>
                    <div className="form__container--item">
                            <div className="item__title">
                                <h1>Sigup form</h1>
                            </div>
                            <div className="item__input">
                                <Input_1 label="Email" type="email" id="sigup_email_input" onChangeValue={(val) =>{
                                    _sigup._gmail=val
                                    _set_sigup(_sigup)
                                }}/>
                            </div>
                            <div className="item__input">
                                <Input_1 label="Name" type="text" id="sigup_name_input" onChangeValue={(val) =>{
                                    _sigup._name=val
                                    _set_sigup(_sigup)
                                }}/>
                            </div>
                            <div className="item__input">
                                <Input_1 label="Age" type="number" id="sigup_age_input" onChangeValue={(val) =>{
                                    _sigup._age=val
                                    _set_sigup(_sigup)
                                }}/>
                            </div>
                            <div className="item__input">
                                <Input_1 label="Time Of Birth" type="datetime-local" id="sigup_time_of_birth_input" onChangeValue={(val) =>{
                                    _sigup._time_of_birth=val
                                    _set_sigup(_sigup)
                                }}/>
                            </div>
                            <div className="item__input">
                                <Input_1 label="Password" type="Password" id="sigup_password_input" onChangeValue={(val) =>{
                                    _sigup._password=val
                                    _set_sigup(_sigup)
                                }}/>
                            </div>
                            <div className="item__input">
                                <Input_1 label="Phone" type="number" id="sigup_phone_input" onChangeValue={(val) =>{
                                    _sigup._phone=val
                                    _set_sigup(_sigup)
                                }}/>
                            </div>
                            <div className="item__err">
                                <Text_notica_mess value={_mess_sigup[0]} color={_mess_sigup[1]}></Text_notica_mess>
                            </div>
                            <div className="item__btn">
                                <Button_1 value="Sigup" _style={{width:"100%"}} onclick_function={sigup_func} ></Button_1>
                            </div>
                            <div className="item__btn">
                                <Button_1 value="Login" _style={{width:"100%"}} onclick_function={() => translatelafinform(1)} ></Button_1>
                            </div>
                    </div>
                </div>
                <div className="form__background">
                    <div className="form__background--item">
                        <div className="item__title">
                            <h1>Hello, Wellcome!</h1>
                        </div>
                        <div className="item__text">
                            <p>Lorem, ipsum dolor sit amet consectetur.</p>
                        </div>
                        <div className="item__btn">
                            <Button_2 value="Sigup" onclick_function={() => translatelafinform(0)} ></Button_2>
                        </div>
                    </div>
                    <div className="form__background--item">
                    <div className="item__title">
                            <h1>Hello, Wellcome!</h1>
                        </div>
                        <div className="item__text">
                            <p>Lorem, ipsum dolor sit amet consectetur.</p>
                        </div>
                        <div className="item__btn">
                            <Button_2 value="Login" onclick_function={() => translatelafinform(1)} ></Button_2>
                        </div>
                    </div>
                </div>
            </div>
       </div>
    </>
}


export default Authen