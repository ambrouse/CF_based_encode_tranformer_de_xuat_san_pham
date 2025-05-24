import {useEffect,useRef,useState} from "react";
import { useParams } from 'react-router-dom';
import '../assets/style/decription/decription.css'
import {load_page_animation,animation} from '../ultils/decription_animation'
import {move_left_img,move_right_img,move_left_manager,move_right_manager} from '../ultils/decription_animation'
import Button_1 from '../component/button/Button_1'
import {load_decription_api,add_card,add_coment,delete_coment} from '../api/decription/decription_api'
import {notification_active,notification_closs} from '../ultils/notification_animation'

export default function Decription_page(){
    const { _id } = useParams();
    const [_description_product_respons,_set_description_product_respons] = useState([])
    const [_description_products_respons,_set_description_products_respons] = useState([])
    const [_list_img_product,_set_list_img_product] = useState([])
    const [_list_coment,_set_list_coment] = useState([])
    const [_index,_set_index] = useState(0)
    const [_data_add_card, _set_data_add_card] = useState({"_id_manager_size_color":"","_price":""})
    const [_data_coment, _set_data_coment] = useState({"_id_manager_size_color":null,"_content":null,"_imgs_coment":null})
    const [_check_file, _set_check_file] = useState(false)
    const renderCount = useRef(0);

    useEffect(()=>{
        load_decription_api(_id).then((data)=>{
            if(data[1]){
                _set_description_product_respons(data[0]._description_product_respons)
                _set_description_products_respons(data[0].description_products_respons)
                _set_list_img_product(data[0].description_products_respons[0]._list_img)
                _set_list_coment(data[0].description_products_respons[0]._list_coment)
                _set_data_add_card({"_id_manager_size_color":data[0].description_products_respons[0]._id_manager_size_color,"_price":data[0]._description_product_respons._price_product})
                _data_coment._id_manager_size_color = data[0].description_products_respons[0]._id_manager_size_color
                _set_data_coment(_data_coment)
                load_page_animation()
            }})},[])
    useEffect(() => {
        renderCount.current += 1;
    
        if (renderCount.current >= 2) {
            // Chạy sau lần render thứ 2
            animation()
        }
        });
        return<>
        <div className="main__decription">
            <div className="main__decription--item">
                <div className="item__background">
                    <div className="item__background--header">
                        <div className="header__title">
                            <h1 className="top__bottom">-- {_description_product_respons?_description_product_respons._name_product:""} --</h1>
                        </div>
                    </div>
                    <div className="item__background--body">
                        <div className="body__img">
                            <div className="body__img--slide">
                                <div className="slide__main">
                                    {_list_img_product.length!=0?(
                                        _list_img_product.map((i,index)=>{
                                            return <div className="slide__main--item" key={index}>
                                                <img src= {"http://localhost:9005/product-service/api/v1/get-img/"+i} alt="" />
                                            </div>
                                        })):(
                                        <div className="slide__main--item">
                                            <img src="/img/5932208.jpg" alt="" />
                                        </div>)}
                                </div>
                                <div className="slide__button">
                                    <img src="/img/right-arrow.png" alt="" onClick={move_left_img} />
                                    <img src="/img/right-arrow.png" alt="" onClick={move_right_img} />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="item__background--manager">
                        <div className="manager__slide">
                            <div className="manager__slide--main">
                                {_description_products_respons.length!=0?(
                                    _description_products_respons.map((i,index)=>{
                                        return <div className="main__item" key={index}>
                                            <p>Color: {i._name_color}, Size: {i._name_size}</p>
                                        </div>
                                    })
                                ):(
                                    <div className="main__item">
                                        <p>null</p>
                                    </div>
                                )}
                            </div>

                            <div className="manager__slide--button">
                                <img src="/img/right-arrow.png" alt="" onClick={()=>{
                                    move_left_manager()
                                    if(_index+1<_description_products_respons.length){
                                        _data_coment._id_manager_size_color = _description_products_respons[_index+1]._id_manager_size_color
                                        _set_data_coment(_data_coment)
                                        _set_index(_index+1)
                                        _set_list_img_product(_description_products_respons[_index+1]._list_img)
                                        _set_list_coment(_description_products_respons[_index+1]._list_coment)
                                        _set_data_add_card({"_id_manager_size_color":_description_products_respons[_index+1]._id_manager_size_color,
                                            "_price":_description_product_respons._price_product})
                                        }}}/>
                                <img src="/img/right-arrow.png" alt="" onClick={()=>{
                                    move_right_manager()
                                    if(_index-1>=0){
                                        _data_coment._id_manager_size_color = _description_products_respons[_index-1]._id_manager_size_color
                                        _set_data_coment(_data_coment)
                                        _set_index(_index-1)
                                        _set_list_coment(_description_products_respons[_index-1]._list_coment)
                                        _set_list_img_product(_description_products_respons[_index-1]._list_img)
                                        _set_data_add_card({"_id_manager_size_color":_description_products_respons[_index-1]._id_manager_size_color,
                                            "_price":_description_product_respons._price_product})
                                    }}}/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="main__decription--item">
                <div className="item__description">
                    <div className="item__description--title">
                        <p>Lorem ipsum dolor sit </p>
                    </div>
                    <div className="item__description--content">
                        {_description_product_respons.length!=0?(
                            <p>{_description_product_respons._desription_product}</p>
                        ):(
                            <p>khong co mo ta.</p>
                        )}
                    </div>
                </div>
                <div className="item__description">
                    <div className="item__description--main">
                        <div className="main__title">
                            <p>Style: {_description_product_respons?_description_product_respons._style_product:""}</p>
                        </div>
                        <div className="main__title">
                            <p>Type: {_description_product_respons?_description_product_respons._type_product:""}</p>
                        </div>
                        <div className="main__title">
                            <p>Seasion: {_description_product_respons?_description_product_respons._seasion_product:""}</p>
                        </div>
                        <div className="main__title">
                            <p>Price: {_description_product_respons?_description_product_respons._price_product:""}</p>
                        </div>
                        <div className="main__title">
                            <p>Rate: {_description_product_respons?_description_product_respons._rate:""}/10</p>
                        </div>
                        <div className="main__title">
                            <div className="main__title--item">
                                <Button_1 value={"add card"} _style2={{padding:"7px 20px"}} 
                                onclick_function={()=>{
                                    add_card(_data_add_card).then((data)=>{
                                            if(data[1]){
                                                notification_active(true,"Đã thêm vào giỏ hàng.")
                                            }else{
                                                notification_active(false,data[0])
                                            }
                                    })}}/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="main__decription--item">
                <div className="item__coment">
                    <div className="item__coment--main">
                        {_list_coment.length>0?(
                            _list_coment.map((i,index)=>{
                                return <div className="main__item" key={index}>
                                    <div className="main__item--avatar">
                                        <div className="avatar__img">
                                            {i._id_user?(
                                                <img src={"http://localhost:9004/user-service/api/v1/get_img/"+i._id_user} alt="" />
                                            ):(
                                                <img src="/img/muathu.jpg" alt="" />
                                            )}
                                        </div>
                                        <div className="avatar__name">
                                            <p>{i._name_user}</p>
                                            {i._check_user?(<p onClick={()=>{
                                                delete_coment(i._id).then((data)=>{
                                                    if(data[1]){
                                                        _set_list_coment(data[0].coments_respon)
                                                        _description_products_respons[_index]._list_coment = data[0].coments_respon
                                                        document.querySelector(".main__decription .main__decription--item:nth-child(4) .item__notification .item__notification--content p").innerHTML  = "Xoa thanh cong"
                                                        notification_active(true,"Đã xóa bình luận.")
                                                        animation()
                                                    }else{
                                                        notification_active(false,data[0])
                                                    }
                                                })
                                            }}>Xoa Coment</p>):null}
                                        </div>
                                    </div>
                                    <div className="main__item--content">
                                        {i._coment?(
                                            <div className="content__text">
                                                <p>{i._coment}</p>
                                            </div>
                                        ):""}
                                        {i._coment?(
                                            <div className="content__more">
                                                <p onClick={()=>{
                                                    let box = document.querySelector(".main__decription .main__decription--item .item__coment .item__coment--main .main__item:nth-child("+(index+1)+") .main__item--content .content__text")
                                                    let height = document.querySelector(".main__decription .main__decription--item .item__coment .item__coment--main .main__item:nth-child("+(index+1)+") .main__item--content .content__text p").clientHeight
                                                    if(box.classList.contains('content__textative')){
                                                        box.classList.remove("content__textative")
                                                        box.style.height = "100px"
                                                    }else{
                                                        box.style.height = height+"px"
                                                        box.classList.add("content__textative")
                                                    }
                                                }}>xem them</p>
                                            </div>
                                        ):""}
                                        {i._id_img_coments?(
                                            <div className="content__img">
                                                <img src={"http://localhost:9005/product-service/api/v1/get-img-coment/"+i._id_img_coments} alt="" />
                                            </div>
                                        ):""}
                                    </div>
                                </div>
                            })):(<div className="main__item">
                                <div className="main__item--avatar">
                                    <div className="avatar__img">
                                        <img src="/img/muathu.jpg" alt="" />
                                    </div>
                                    <div className="avatar__name">
                                        <p>Unknow</p>
                                    </div>
                                </div>
                                <div className="main__item--content">
                                    <div className="content__text">
                                        <p>Khong co coment</p>
                                    </div>
                                    <div className="content__more">
                                        <p >xem them</p>
                                    </div>
                                </div>
                            </div>)}
                    </div>
                    <div className="main__item--send">
                        <div className="send__main">
                            <div className="send__main--item">
                                <textarea type="text" onChange={(e)=>{
                                    const val = e.target.value;
                                    _data_coment._content = val
                                    _set_data_coment(_data_coment)
                                }}
                                />
                            </div>
                            <div className="send__main--item">
                                <input type="file" onChange={async (e)=>{
                                    try{
                                        const selectedFile = e.target.files[0];
                                        const arrayBuffer = await selectedFile.arrayBuffer();
                                        const byteArray = Array.from(new Uint8Array(arrayBuffer));
                                        _set_check_file(true)
                                        _data_coment._imgs_coment = byteArray
                                        _set_data_coment(_data_coment)
                                    }catch(e){

                                    }
                                }} />
                                {_check_file==false?(
                                    <button>Chon anh</button>
                                ):(
                                    <button>Anh da duoc chon, doi?</button>
                                )}
                            </div>
                            <div className="send__main--item">
                                <button 
                                onClick={()=>{add_coment(_data_coment).then(async (data)=>{
                                    if(data[1]){
                                        _data_coment._content = null
                                        _data_coment._imgs_coment = null
                                        _set_data_coment(_data_coment)
                                        _set_check_file(false)
                                        _set_list_coment(data[0].coments_respon)
                                        _description_products_respons[_index]._list_coment = data[0].coments_respon
                                        document.querySelector(".main__decription .main__decription--item .item__coment .main__item--send .send__main .send__main--item textarea").value = null
                                        document.querySelector(".main__decription .main__decription--item .item__coment .main__item--send .send__main .send__main--item input").value = null
                                        document.querySelector(".main__decription .main__decription--item:nth-child(4) .item__notification .item__notification--content p").innerHTML  = "Coment thanh cong"
                                        notification_active(true,"Đã đăng bình luận.")
                                        animation()
                                    }else{
                                        notification_active(false,data[0])
                                    }
                                })}}
                                >Send.</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="main__decription--item">
                <div className="item__notification">
                    <div className="item__notification--tick">
                    </div>
                    <div className="item__notification--content">
                        <p></p>
                    </div>
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
        </div>
    </>
}