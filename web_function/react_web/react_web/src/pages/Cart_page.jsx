import { useEffect, useState } from "react"
import '../assets/style/card/cart.css'
import {load_cart,update_mini_card,delete_mini_card,apply_cart} from '../api/cart/cart_api'
import Button_2 from '../component/button/Button_2'
import {notification_active,notification_closs} from '../ultils/notification_animation'
import sleep from '../ultils/sleep'

export default function Cart_page(){
    const [_cart_decription,_set_cart_decription] = useState(null)
    const [_address, _set_address] = useState([])
    const [_payment_method, _set_payment_method] = useState([])
    const [_check_card,_set_check_card] = useState(true)
    const [_loading,_set_loading] = useState(false)
    const [_address_focus,_set_address_focus] = useState()
    const [_payment_method_focus,_set_payment_method_focus] = useState()
    const [_phone_focus,_set_phone_focus] = useState()
    const [_total_price,_set_total_price] = useState(0)

    useEffect(()=>{
        load_cart().then(async (data)=>{
            await sleep(500)
            if(data[1]){
                _set_cart_decription(data[0])
                _set_address(data[0]._address_respon)
                _set_payment_method(data[0]._payment_method_respon)
                _set_check_card(true)
                _set_loading(true)
                _set_address_focus(data[0]._address_respon[0]._id)
                _set_payment_method_focus(data[0]._payment_method_respon[0]._id)
                let a = 0
                data[0]._cards_respon._cards_desription.map((i)=>{
                    a+=(i._price*i._count)
                })
                _set_total_price(a)
            }else{
                _set_check_card(false)
                _set_loading(true)
            }
        })
    },[])

    return<>
        <div className="cart__main">
            <div className="cart__main--item">
                <div className="item__header">
                    <div className="item__header--title">
                        <h1>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nulla sapiente</h1>
                    </div>
                    <div className="item__header--background">
                        <img src="/img/1496233.jpg" alt="" />
                    </div>
                </div>
            </div>
            <div className="cart__main--item">
                <div className="item__product">
                    <div className="item__product--table">
                        {_loading?(
                            _check_card!=false?(
                                <div className="table__header">
                                    <div className="table__header--item">
                                        <p>San pham</p>
                                    </div>
                                    <div className="table__header--item">
                                        <p>Size - Mau</p>
                                    </div>
                                    <div className="table__header--item">
                                        <p>So luong</p>
                                    </div>
                                    <div className="table__header--item">
                                        <p>Don gia</p>
                                    </div>
                                </div>
                            ):null
                        ):null}
                        {_loading?(
                            _check_card!=false?(
                                _cart_decription?._cards_respon?._cards_desription.length>0?(
                                    _cart_decription._cards_respon._cards_desription.map((i,index)=>{
                                        return <div className="table__main" key={index}>
                                            <div className="table__main--item">
                                                {i._img_product?(
                                                    <img src= {"http://localhost:9002/product-service/api/v1/get-img/"+i._img_product} alt="" />
                                                ):(
                                                    <img src= "/img/muathu.jpg" alt="" />
                                                )}
                                                <p>{i._name_product}</p>
                                            </div>
                                            <div className="table__main--item">
                                                <select onChange={(e)=>{
                                                    let request = {"_id_card_mini":i._id_card_mini,"_id_manager_size_color":e.target.value,"_count":i._count}
                                                    update_mini_card(request).then((status)=>{
                                                        load_cart().then((data)=>{
                                                            if(data[1]){
                                                                _set_cart_decription(data[0])
                                                                let a = 0
                                                                data[0]._cards_respon._cards_desription.map((i)=>{
                                                                    a+=(i._price*i._count)
                                                                })
                                                                _set_total_price(a)
                                                            }
                                                        })
                                                    })
                                                }} value={i._id_manager_size_color}>
                                                    {i._manager_size_colors_respon.length>0?(
                                                        i._manager_size_colors_respon.map((j,index)=>(
                                                            <option value={j._id} key={index} >Size: {j._name_size}, {j._name_color}</option>
                                                        ))
                                                    ):null}
                                                </select>
                                            </div>
                                            <div className="table__main--item">
                                                <input type="number" onChange={(e)=>{
                                                    let request = {"_id_card_mini":i._id_card_mini,"_id_manager_size_color":i._id_manager_size_color,"_count":e.target.value}
                                                    update_mini_card(request).then((status)=>{
                                                        if(status[1]){
                                                            load_cart().then((data)=>{
                                                                if(data[1]){
                                                                    _set_cart_decription(data[0])
                                                                    _set_check_card(true)
                                                                    _set_loading(true)
                                                                    let a = 0
                                                                    data[0]._cards_respon._cards_desription.map((i)=>{
                                                                        a+=(i._price*i._count)
                                                                    })
                                                                    _set_total_price(a)
                                                                }else{
                                                                    _set_check_card(false)
                                                                    _set_loading(true)
                                                                }
                                                            })
                                                        }else{
                                                            notification_active(false,status[0])
                                                        }
                                                    })
                                                }} value={i._count}/>
                                            </div>
                                            <div className="table__main--item">
                                                <p>{i._price*i._count} vnd</p>
                                                <p onClick={()=>{
                                                    delete_mini_card(i._id_card_mini).then((status)=>{
                                                        if(status[1]){
                                                            notification_active(true,"Da xoa san pham")
                                                            load_cart().then((data)=>{
                                                                if(data[0]){
                                                                    _set_cart_decription(data[0])
                                                                    _set_check_card(true)
                                                                    _set_loading(true)
                                                                    let a = 0
                                                                    data[0]._cards_respon._cards_desription.map((i)=>{
                                                                        a+=(i._price*i._count)
                                                                    })
                                                                    _set_total_price(a)
                                                                }else{
                                                                    _set_check_card(false)
                                                                    _set_loading(true)
                                                                }
                                                            })
                                                        }
                                                    })
                                                }}>Xoa</p>
                                            </div>
                                        </div>
                                    })
                                ):null
                            ):(
                                <div className="table__main">
                                    <p style={{color:"rgb(205,217,226)",fontSize:"18px"}} >Khong co san pham nao.</p>
                                </div>
                            )
                        ):(
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
                        )}
                    </div>
                </div>
            </div>
            {_check_card?(
                <div className="cart__main--item">
                    <div className="item__decription">
                        <div className="item__decription--item">
                            {_cart_decription?._cards_respon?(
                                <p>Tong tien hang: {_total_price}</p>
                            ):null}
                        </div>
                        <div className="item__decription--item">
                            {_cart_decription?._cards_respon?(
                                <p>Phi ship cong them: {_cart_decription?._cards_respon._shipping_fee}</p>
                            ):null}
                        </div>
                    </div>
                </div>
            ):null}
            {_check_card?(
                <div className="cart__main--item">
                    <div className="item__address">
                        <select onChange={(e)=>{
                            _set_address_focus(e.target.value)
                        }}>
                            {_address?(
                                _address.map((data,index)=>(
                                    <option key={index} value={data._id}> {data._name} </option>
                                ))
                            ):(
                                <option>khong co thong tin</option>
                            )}
                        </select>
                    </div>
                    <div className="item__paymentmethod">
                        <select onChange={(e)=>{
                            _set_payment_method_focus(e.target.value)
                        }}>
                            {_payment_method?(
                                _payment_method.map((data,index)=>(
                                    <option key={index} value={data._id} > {data._name} </option>
                                ))
                            ):(
                                <option>khong co thong tin</option>
                            )}
                        </select>
                    </div>
                    <div className="item__phone">
                        <input type="number" onChange={(e)=>{
                            _set_phone_focus(e.target.value)
                        }} placeholder="Nhap so dien thoai"></input>
                    </div>
                </div>
            ):null}
            {_check_card?(
                <div className="cart__main--item">
                    <div className="item__apply">
                        <Button_2 value={"Dat hang"} _style2={{borderRadius:"5px"}} onclick_function={()=>{
                            let request = {"_id_card":_cart_decription._cards_respon._id,
                                "_id_address":_address_focus,
                                "_id_payment_method":_payment_method_focus,
                                "_phone_number":_phone_focus}
                                apply_cart(request).then((status)=>{
                                    if(status[1]){
                                        notification_active(true,"Dat hang thanh cong")
                                        load_cart().then((data)=>{
                                            if(data[1]){
                                                _set_cart_decription(data[0])
                                                _set_check_card(true)
                                                _set_loading(true)
                                                let a = 0
                                                data[0]._cards_respon._cards_desription.map((i)=>{
                                                    a+=(i._price*i._count)
                                                })
                                                _set_total_price(a)
                                            }else{
                                                _set_check_card(false)
                                                _set_loading(true)
                                            }
                                        })
                                    }else{
                                        notification_active(false,status[0])
                                }
                            })

                        }} />
                    </div>
                </div>
            ):null}
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
    </>
}