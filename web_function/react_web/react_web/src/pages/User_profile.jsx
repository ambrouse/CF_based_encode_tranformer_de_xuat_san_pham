import { useEffect, useState } from 'react'
import '../assets/style/user_profile/user_profile.css'
import Input_1 from '../component/input/Input_1'
import { load_user_api,update_user_api,update_email_api,update_address_api,delete_address_api,update_img_api } from '../api/user_profile/user_profile_api'
import { notification_active, notification_closs } from '../ultils/notification_animation'

export default function User_profile(){

    const [_user_data,_set_user_data] = useState(null)
    const [_address,_set_address] = useState(null)
    const [_address_focus,_set_address_focus] = useState(null)
    const [_img,_set_img] = useState(null)
    const [_email,_set_email] = useState(null)

    useEffect(()=>{
        load_user_api().then((data)=>{
            if(data[1]){
                _set_user_data({"_name_user": data[0]._name_user,
                                "_age_user": data[0]._age_user,
                                "_time_of_birth_user": data[0]._time_of_birth_user,
                                "_phone_number": data[0]._phone_number,
                                "_job_user": data[0]._job_user})
                _set_address(data[0]._address_users_respon)
                _set_img(data[0]._id_user)
                _set_email(data[0]._gmail)
            }})
        },[])
        
    return<>
        <div className="main__profile">
            <div className="main__profile--content">
                <div className="content__avatar">
                    <div className="content__avatar--img">
                    {_img?(
                            <>
                                <img src={"http://localhost:9004/user-service/api/v1/get_img/"+_img} alt="" />
                            </>
                        ):(
                            <>
                                <img src="/img/b23d923ac45f6bdac43c9c4c329bd7da.gif" alt="" />
                            </>
                        )}
                        <img src="/img/Refreshments White Transparent, White Refresh.jpg" alt="" />
                        <input type="file" onChange={async(e)=>{
                            try{
                                document.querySelector(".loadbox").classList.add("loadboxactive")
                                const selectedFile = e.target.files[0];
                                const arrayBuffer = await selectedFile.arrayBuffer();
                                const byteArray = Array.from(new Uint8Array(arrayBuffer));
                                const a = {"_img_user":byteArray}
                                update_img_api(a).then((status)=>{
                                    if(status[1]){
                                        load_user_api().then((data)=>{
                                            if(data[1]){
                                                document.querySelector(".loadbox").classList.remove("loadboxactive")
                                                notification_active(true,"Đã cập nhật ảnh đại diện.")
                                                _set_img(data[0]._id_user)
                                            }})
                                    }else{
                                        notification_active(false,status[0])
                                    }
                                })
                            }catch(e){

                            }
                        }}/>
                    </div>
                </div>
                <div className="content__item">
                    <div className="content__item--infor">
                        <div className="infor__item">
                            {_user_data?(
                                <Input_1 label="Name user" _value={_user_data._name_user} type="text" id="name_user_input" style_1={{backgroundColor:"rgb(53, 52, 52)",color:"white"}} style_2={{backgroundColor:"rgb(53, 52, 52)",color:"white"}} onChangeValue={(val) => {
                                        _user_data._name_user = val
                                        _set_user_data(_user_data)
                                    }}/>
                            ):null}
                        </div>
                        <div className="infor__item">
                            {_user_data?(
                                <Input_1 label="Age" type="number" _value={_user_data._age_user} id="age_user_input" style_1={{backgroundColor:"rgb(53, 52, 52)",color:"white"}} style_2={{backgroundColor:"rgb(53, 52, 52)",color:"white"}} onChangeValue={(val) => {
                                        _user_data._age_user = val
                                        _set_user_data(_user_data)
                                    }}/>
                            ):null}
                        </div>
                        <div className="infor__item">
                            {_user_data?(
                                <Input_1 label="Time of birth" _value={_user_data._time_of_birth_user} type="datetime-local" id="time_of_birth_user_input" style_1={{backgroundColor:"rgb(53, 52, 52)",color:"white"}} style_2={{backgroundColor:"rgb(53, 52, 52)",color:"white"}} onChangeValue={(val) => {
                                        _user_data._time_of_birth_user = val
                                        _set_user_data(_user_data)
                                    }}/>
                            ):null}
                        </div>
                        <div className="infor__item">
                            {_user_data?(
                                <Input_1 label="Phone number" _value={_user_data._phone_number} type="number" id="phone_user_input" style_1={{backgroundColor:"rgb(53, 52, 52)",color:"white"}} style_2={{backgroundColor:"rgb(53, 52, 52)",color:"white"}} onChangeValue={(val) => {
                                        _user_data._phone_number = val
                                        _set_user_data(_user_data)
                                    }}/>
                            ):null}
                        </div>
                        <div className="infor__item">
                            {_user_data?(
                                <Input_1 label="Job" _value={_user_data._job_user} type="text" id="job_user_input" style_1={{backgroundColor:"rgb(53, 52, 52)",color:"white"}} style_2={{backgroundColor:"rgb(53, 52, 52)",color:"white"}} onChangeValue={(val) => {
                                        _user_data._job_user = val
                                        _set_user_data(_user_data)
                                    }}/>
                            ):null}
                        </div>
                        <div className="infor__item">
                            <div className="infor__item--button">
                                <p onClick={()=>{
                                    document.querySelector(".loadbox").classList.add("loadboxactive")
                                    update_user_api(_user_data).then((status)=>{
                                        if(status[1]){
                                            load_user_api().then((data)=>{
                                                if(data[1]){
                                                    document.querySelector(".loadbox").classList.remove("loadboxactive")
                                                    _set_user_data({"_name_user": data[0]._name_user,
                                                                    "_age_user": data[0]._age_user,
                                                                    "_time_of_birth_user": data[0]._time_of_birth_user,
                                                                    "_phone_number": data[0]._phone_number,
                                                                    "_job_user": data[0]._job_user})
                                                    notification_active(true,"Đã cập nhật thông tin.")
                                                }})
                                        }else{
                                            notification_active(false, status[0])

                                        }
                                    })
                                    }}>Save</p>
                            </div>
                        </div>
                        <div className="infor__item">
                            <div className="infor__item--mail">
                                <div className="mail__content">
                                    <p>Email: {_email?_email:null}</p>
                                </div>
                                <div className="mail__btn">
                                    <p onClick={()=>{
                                        document.querySelector(".main__profile--emailbox").classList.add("main__profile--emailboxactive")
                                    }}>Change</p>
                                </div>
                            </div>
                        </div>
                        <div className="infor__item">
                            <div className="infor__item--address">
                                <div className="address__main">
                                    <div className="address__main--header">
                                        <p>Address</p>
                                        <p onClick={()=>{
                                            document.querySelector(".main__profile--addressbox").classList.add("main__profile--addressboxactive")
                                        }}>Add</p>
                                    </div>
                                    <div className="address__main--item">
                                        {_address?(
                                            _address.map((data,index)=>{
                                                return <div className="item__content" key={index}>
                                                    <p>{data._name}</p>
                                                    <p onClick={()=>{
                                                        document.querySelector(".loadbox").classList.add("loadboxactive")
                                                        delete_address_api(data._id).then((status)=>{
                                                            if(status[1]){
                                                                load_user_api().then((data)=>{
                                                                    if(data[1]){
                                                                        document.querySelector(".loadbox").classList.remove("loadboxactive")
                                                                        _set_address(data[0]._address_users_respon)
                                                                        notification_active(true, "Đã xóa địa chỉ.")
                                                                        document.querySelector(".main__profile--addressbox").classList.remove("main__profile--addressboxactive")  
                                                                    }})
                                                            }else{
                                                                notification_active(false, status[0])
                                                            }
                                                        })
                                                    }}
                                                    >delete</p>
                                                </div>
                                            })
                                        ):null}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="main__profile--emailbox">
                <div className="emailbox__content">
                    {_email?(
                        <Input_1 label="Email" type="email" _value={_email} id="email_user_input" style_1={{backgroundColor:"rgb(0, 0, 0)",color:"white"}} style_2={{backgroundColor:"rgb(0, 0, 0)",color:"white"}} onChangeValue={(val) => {
                            _set_email(val)
                        }}/>
                    ):null}
                </div>
                <div className="emailbox__btn">
                    <p onClick={()=>{
                        document.querySelector(".loadbox").classList.add("loadboxactive")
                        update_email_api({"_email":_email}).then((data)=>{
                            if(data[1]){
                                document.querySelector(".loadbox").classList.remove("loadboxactive")
                                notification_active(true, "Đã cập nhật gmail.")
                                document.querySelector(".main__profile--emailbox").classList.remove("main__profile--emailboxactive")  
                            }else{
                                notification_active(false, data[0])
                            }
                        })
                    }}
                    >Save</p>
                </div>
                <div className="emailbox__closs" onClick={()=>{
                    document.querySelector(".main__profile--emailbox").classList.remove("main__profile--emailboxactive")  
                }}>
                    <span></span>
                    <span></span>
                </div>
            </div>
            <div className="main__profile--addressbox">
                <div className="addressbox__content">
                    <Input_1 label="Address" type="email" id="address_user_input" style_1={{backgroundColor:"rgb(0, 0, 0)",color:"white"}} style_2={{backgroundColor:"rgb(0, 0, 0)",color:"white"}} onChangeValue={(val) => {
                        _set_address_focus(val)
                    }}/>
                </div>
                <div className="addressbox__btn">
                    <p onClick={()=>{
                        document.querySelector(".loadbox").classList.add("loadboxactive")
                        update_address_api({"_name":_address_focus}).then((status)=>{
                            if(status[1]){
                                load_user_api().then((data)=>{
                                    if(data[1]){
                                        document.querySelector(".loadbox").classList.remove("loadboxactive")
                                        _set_address(data[0]._address_users_respon)
                                        notification_active(true, "Đã thêm địa chỉ")
                                        document.querySelector(".main__profile--addressbox").classList.remove("main__profile--addressboxactive")  
                                    }})
                            }else{
                                notification_active(false, status[0])
                            }
                        })
                    }}
                    >Save</p>
                </div>
                <div className="addressbox__closs" onClick={()=>{
                    document.querySelector(".main__profile--addressbox").classList.remove("main__profile--addressboxactive")  
                }}>
                    <span></span>
                    <span></span>
                </div>
            </div>
            <div className="notification ">
                <div className="notification__box">
                    <div className="notification__box--tick">
                        <img src="/img/HD White Tick Mark Icon Transparent Background.jpg" alt="" />
                        <img src="/img/HD White X Cross Mark Icon PNG.jpg" alt="" />
                    </div>
                    <div className="notification__box--title">
                        <p></p>
                        <span></span>
                    </div>
                    <div className="notification__box--content">
                        <p></p>
                    </div>
                    <div className="notification__box--out">
                        <div className="out__item" onClick={notification_closs}>
                            <p>Closs</p>
                        </div>
                    </div>
                </div>
            </div>
            <div className="loadbox">
                <div className="loading">
                    <div className="loading__spiner">
                        <div className="loading__spiner--item">
                            <span></span>
                            <span></span>
                        </div>
                        <div className="loading__spiner--item">
                            <span></span>
                            <span></span>
                        </div>
                        <div className="loading__spiner--item">
                            <span></span>
                            <span></span>
                        </div>
                        <div className="loading__spiner--item">
                            <span></span>
                            <span></span>
                        </div>
                    </div>
                    <div className="loading__content">
                        <p>Loading</p>
                    </div>
                </div> 
            </div>
        </div>
    </>
}