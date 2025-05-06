import { Outlet, useNavigate } from "react-router-dom";
import { useEffect, useState } from 'react'
import '../assets/style/layout/layout.css'
import Button_1 from '../component/button/Button_1'
import translatetoglenavbar,{hidden_navbar} from '../ultils/translatetoglenavbar'
import rotation_text_navber from '../ultils/rotation_text_navber'
import check_token from '../api/authen/check_token_api'

function MainLayout() {

  const [_togle,_set_togle] = useState(1)
  const [_time,_set_time] = useState(new Date())
  const navigate = useNavigate();
  const [_check_token,_set_check_token] = useState(false)
  const [_name_user,_set_name_user] = useState()
  const [_img_user,_set_img_user] = useState(0)

  function togle_nav_func(){
    translatetoglenavbar(_togle)
    _set_togle(_togle*-1)
  }

  useEffect(()=>{
    rotation_text_navber()
    hidden_navbar()
    check_token().then((data)=>{
      if(data[1]){
        _set_check_token(true)
        _set_name_user(data[0]._name_user)
        _set_img_user(data[0]._id_img_user)
      }
    })
    const timer = setInterval(() => _set_time(new Date()), 100); 
    return () => clearInterval(timer);
  },[])



  return (
    <div className="main">
      <div className="main__navbar">
        <div className="main__navbar--item">
          <div className="item__logo">
            <img src="/img/loki-logo.1920x1080.gif" alt="" onClick={()=>{navigate("/")}} />
          </div>
        </div>
        <div className="main__navbar--item">
          <div className="item__pc">
              <div className="item__pc--link">
                <a onClick={()=>{navigate("/")}}>Home</a>
              </div>
              <div className="item__pc--link">
                <a href="">Shop</a>
              </div>
              <div className="item__pc--link">
                <a href="">Feature</a>
              </div>
              <div className="item__pc--link">
                <a href="">Contact</a>
              </div>
              <div className="item__pc--link">
                <a onClick={()=>{
                  _check_token?navigate("/cart"):navigate("/login-sigup")
                }}>Cart</a>
              </div>
              <div className="item__pc--link">
                {_check_token?(
                      <>
                        {_img_user>0?(
                          <img src="/img/b23d923ac45f6bdac43c9c4c329bd7da.gif" alt="" />
                        ):(
                          <img src="/img/b23d923ac45f6bdac43c9c4c329bd7da.gif" alt="" />
                        )}
                        <p>{_name_user}</p>
                      </>
                ):(
                  <Button_1 value={"Login - Sigup"} _style2={{height:"30px",padding:"0px 20px"}} ></Button_1>
                )}
              </div>
          </div>
          <div className="item__phone">
            <div className="item__phone--togle" onClick={togle_nav_func}>
              <span></span>
              <span></span>
              <span></span>
            </div>
          </div>
        </div>
        <div className="item__togle">
          <div className="item__togle--link">
            <a href="">Home</a>
          </div>
          <div className="item__togle--link">
            <a href="">Shop</a>
          </div>
          <div className="item__togle--link">
            <a href="">Feature</a>
          </div>
          <div className="item__togle--link">
            <a href="">Contact</a>
          </div>
          <div className="item__togle--link">
            <a href="">Card</a>
          </div>
          <div className="item__togle--link">
            <Button_1 value={"Login - Sigup"} _style2={{height:"30px",padding:"0px 20px"}} ></Button_1>
          </div>
        </div>
      </div>
      <div className="main__body">
        <Outlet />
      </div>
      <div className="main__footer">
        <div className="main__footer--item">
          <p>Phone: 0123456789</p>
        </div>
        <div className="main__footer--item">
          <p>Gmail: test@gmail.com</p>
        </div>
        <div className="main__footer--item">
          <p>Zalo: 0123456789</p>
        </div>
        <div className="main__footer--item">
          <span></span>
        </div>
        <div className="main__footer--item">
          <p>{_time.toLocaleTimeString('vi-VN', { timeZone: 'Asia/Ho_Chi_Minh' })} - {_time.toLocaleDateString()}</p>
        </div>
      </div>
    </div>
  );
}

export default MainLayout;