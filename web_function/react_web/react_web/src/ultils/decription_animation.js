import sleep from '../ultils/sleep'

export function load_page_animation(){
    window.scrollTo(0, 0);
    document.querySelector(".main__decription .main__decription--item .item__background .item__background--header .header__title h1").classList.remove("top__bottom")
    document.querySelector(".main__decription .main__decription--item .item__background .item__background--body .body__img .body__img--slide .slide__main .slide__main--item").classList.remove("bottom__top")
    let index_ = 0
    document.querySelectorAll(".main__decription .main__decription--item .item__background .item__background--body .body__content .body__content--item").forEach((i)=>{
        if(index_%2==0){
            i.classList.remove("left__right")
        }else{
            i.classList.remove("right__left")
        }
        index_+=1
    })
    document.querySelector(".main__decription .main__decription--item .item__background .item__background--manager .manager__slide .manager__slide--main .main__item").classList.remove("bottom__top")
}

export function move_left_img(){
    const box = ".main__decription .main__decription--item .item__background .item__background--body .body__img .body__img--slide .slide__main"
    document.querySelector(box).scrollLeft = document.querySelector(box).scrollLeft + document.querySelector(box).clientWidth
}
export function move_right_img(){
    const box = ".main__decription .main__decription--item .item__background .item__background--body .body__img .body__img--slide .slide__main"
    document.querySelector(box).scrollLeft = document.querySelector(box).scrollLeft - document.querySelector(box).clientWidth
}
export function move_left_manager(){
    const box = ".main__decription .main__decription--item .item__background .item__background--manager .manager__slide .manager__slide--main"
    document.querySelector(box).scrollLeft = document.querySelector(box).scrollLeft + document.querySelector(box).clientWidth
}
export function move_right_manager(){
    const box = ".main__decription .main__decription--item .item__background .item__background--manager .manager__slide .manager__slide--main"
    document.querySelector(box).scrollLeft = document.querySelector(box).scrollLeft - document.querySelector(box).clientWidth
}

export function animation(){
    const box = document.querySelectorAll(".main__decription .main__decription--item .item__coment .item__coment--main .main__item")
    let index = 1
      box.forEach((i)=>{
        if(document.querySelector(".main__decription .main__decription--item .item__coment .item__coment--main .main__item:nth-child("+index+") .main__item--content .content__text")){
          if(document.querySelector(".main__decription .main__decription--item .item__coment .item__coment--main .main__item:nth-child("+index+") .main__item--content .content__more")){
            if(document.querySelector(".main__decription .main__decription--item .item__coment .item__coment--main .main__item:nth-child("+index+") .main__item--content .content__text").clientHeight<100){
              document.querySelector(".main__decription .main__decription--item .item__coment .item__coment--main .main__item:nth-child("+index+") .main__item--content .content__more").classList.add("display__none")
            }else{
                document.querySelector(".main__decription .main__decription--item .item__coment .item__coment--main .main__item:nth-child("+index+") .main__item--content .content__text").classList.add("content__textheight")
            }
        }}
        index+=1
      })
  }