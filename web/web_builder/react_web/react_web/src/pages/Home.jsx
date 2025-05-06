import { useEffect, useState } from 'react'
import '../assets/style/home/home.css'
import Button_1 from '../component/button/Button_1'
import {scroll_wall} from '../ultils/home_animation'
import {load_home_api} from '../api/home/home_api'
import { useNavigate } from 'react-router-dom';


export default function Home(){

    const [_list_product,_set_list_product] = useState()
    const [_list_product_recoment,_set_list_product_recoment] = useState(null)
    const navigate = useNavigate();
    const [_loading,_set_loading] = useState(false)
    const [_not_recoment,_set_not_recoment] = useState()

    useEffect(()=>{
        scroll_wall()
        load_home_api().then((data)=>{
            if(data[1]){
                _set_list_product(data[0]._homes_respon)
                _set_list_product_recoment(data[0]._recoment_products_respon)
                _set_loading(true)
                _set_not_recoment(Array.from({ length: (Math.floor(window.innerWidth/300))}, (_, i) => i))
            }else{
                _set_loading(true)
                _set_not_recoment(Array.from({ length: (Math.floor(window.innerWidth/300))}, (_, i) => i))
            }
        })
    },[])
    
    console.log(_not_recoment)
    return <>
        <div className="home__main">
            <div className="home__main--item">
                <div className="item__wall">
                    <div className="item__wall--info">
                        <div className="info__title">
                            <h1>Lorem ipsum dolor sit amet </h1>
                        </div>
                        <div className="info__content">
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Iure numquam consequuntur rem ducimus nesciunt tempore ex voluptate</p>
                        </div>
                        <div className="info__contac">
                            <Button_1 value={"Contact Here."}></Button_1>
                        </div>
                    </div>
                    <div className="item__wall--background">
                        <img className="background__img" src='./img/4670245-removebg-preview.png'></img>
                    </div>
                </div>
            </div>
            <div className="home__main--item">
                <div className="item__seasion">
                    <div className="item__seasion--title">
                        <div className="title__icon">
                            <div className="title__icon--img">
                                <img src="./img/5932305-removebg-preview.png" alt="" />
                            </div>
                            <div className="title__icon--img">
                                <img src="/img/untitled-design-14--removebg-preview.png" alt="" />
                            </div>
                        </div>
                        <div className="title__content">
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Nobis, velit commodi odio dicta fuga nemo, at architecto voluptatum temporibus sit voluptates! Praesentium, nostrum hic? Accusamus aperiam culpa magni labore porro.</p>
                        </div>
                    </div>
                    <div className="item__seasion--option">
                        <div className="option__item">
                            <div className="option__item--img">
                                <img src="./img/muaxuan.jpg" alt="" />
                            </div>
                            <div className="option__item--content">
                                <p>Bo suu tpa mua xuan</p>
                            </div>
                        </div>
                        <div className="option__item">
                            <div className="option__item--img">
                                <img src="./img/muaha.jpg" alt="" />
                            </div>
                            <div className="option__item--content">
                                <p>Bo suu tpa mua ha</p>
                            </div>
                        </div>
                        <div className="option__item">
                            <div className="option__item--img">
                                <img src="./img/muathu.jpg" alt="" />
                            </div>
                            <div className="option__item--content">
                                <p>Bo suu tpa mua thu</p>
                            </div>
                        </div>
                        <div className="option__item">
                            <div className="option__item--img">
                                <img src="./img/muadong.jpg" alt="" />
                            </div>
                            <div className="option__item--content">
                                <p>Bo suu tpa mua dong</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="home__main--item">
                <div className="item__quality">
                    <div className="item__quality--background">
                        <div className="background__img">
                            <img src="./img/1448069.jpg" alt="" />
                        </div>
                    </div>
                    <div className="item__quality--decription">
                        <div className="decription__title">
                            <p>Lorem ipsum dolor sit, amet </p>
                        </div>
                        <div className="decription__content">
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Error voluptas non doloribus rerum dolorum rem deserunt molestias, natus dolore ab nihil at ad quae quibusdam sed officiis incidunt ipsam. Rem!</p>
                        </div>
                        <div className="decription__contact">
                            <Button_1 value={"Contact"}></Button_1>
                        </div>
                    </div>
                </div>
            </div>
            <div className="home__main--item">
                <div className="item__carousel">
                    <div className="item__carousel--title">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit</p>
                    </div>
                    <div className="item__carousel--slide">
                        <div className="slide__main">
                            {
                                _loading?(
                                    _list_product?(
                                        _list_product.map((data,i)=>{
                                            return <div className="slide__main--item" key={i} onClick={()=>{navigate('/decription-product/'+data._id_product)}}>
                                                    <div className="item__main">
                                                        <div className="item__main--img">
                                                            <img src={data._img_product?"http://localhost:9005/product-service/api/v1/get-img/"+data._img_product:"/img/muathu.jpg"} alt="" />
                                                        </div>
                                                        <div className="item__main--decription">
                                                            <div className="decription__item">
                                                                <p>{data._name_product}</p>
                                                                <p>Type: {data._type_product}</p>
                                                                <p>Style: {data._style_product}</p>
                                                                <p>Rate: {data._rate}/10</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                        })
                                    ):(_not_recoment?(
                                        _not_recoment.map((data,i)=>{
                                            return <div className="slide__main--item" key={i}>
                                                    <div className="item__main">
                                                        <p>Loading</p>
                                                    </div>
                                                </div>
                                        })
                                    ):null)
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
                                )
                            }

                        </div>
                    <div className="slide__button">
                        <div className="slide__button--img" >
                            <img src="/img/right-arrow.png" alt="" />
                        </div>
                        <div className="slide__button--img">
                            <img src="/img/right-arrow.png" alt="" />
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <div className="home__main--item">
                <div className="item__carousel">
                    <div className="item__carousel--title">
                        <p >Lorem ipsum dolor sit amet consectetur adipisicing elit</p>
                    </div>
                    <div className="item__carousel--slide">
                        <div className="slide__main">
                        {
                            _loading?(
                                _list_product_recoment?(
                                    _list_product_recoment.map((data,i)=>{
                                        return <div className="slide__main--item" key={i} onClick={()=>{
                                            navigate('/decription-product/'+data._id_product)
                                        }}>
                                                <div className="item__main">
                                                    <div className="item__main--img">
                                                        <img src={data._img_product?"http://localhost:9005/product-service/api/v1/get-img/"+data._img_product:"/img/muathu.jpg"} alt="" />
                                                    </div>
                                                    <div className="item__main--decription">
                                                        <div className="decription__item">
                                                            <p>{data._name_product}</p>
                                                            <p>Type: {data._type_product}</p>
                                                            <p>Style: {data._style_product}</p>
                                                            <p>Rate: {data._rate}/10</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                    })
                                ):(_not_recoment?(
                                    _not_recoment.map((data,i)=>{
                                        return <div className="slide__main--item" key={i}>
                                                <div className="item__main">
                                                    <p>Loading</p>
                                                </div>
                                            </div>
                                    })
                                ):null)
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
                    <div className="slide__button">
                        <div className="slide__button--img" >
                            <img src="/img/right-arrow.png" alt="" />
                        </div>
                        <div className="slide__button--img">
                            <img src="/img/right-arrow.png" alt="" />
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <div className="home__main--item">
                <div className="item__quality">
                    <div className="item__quality--decription">
                        <div className="decription__title">
                            <p>Lorem ipsum dolor sit, amet </p>
                        </div>
                        <div className="decription__content">
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Error voluptas non doloribus rerum dolorum rem deserunt molestias, natus dolore ab nihil at ad quae quibusdam sed officiis incidunt ipsam. Rem!</p>
                        </div>
                        <div className="decription__contact">
                            <Button_1 value={"Contact"}></Button_1>
                        </div>
                    </div>
                    <div className="item__quality--background">
                        <div className="background__img">
                            <img src="./img/5932208.jpg" alt="" />
                        </div>
                    </div>
                </div>
            </div>
            <div className="home__main--item">
                <div className="item__goodbye">
                    <div className="item__goodbye--title">
                        <p>Lorem ipsum dolor sit amet consectetur </p>
                    </div>
                    <div className="item__goodbye--content">
                        <p>Lorem ipsum dolor sit amet consectetur </p>
                    </div>
                    <div className="item__goodbye--contact">
                        <Button_1 value="Contact"></Button_1>
                    </div>
                </div>
            </div>
        </div>
    </>

}