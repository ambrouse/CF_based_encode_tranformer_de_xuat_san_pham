import sleep from "./sleep"

async function translatetoglenavbar(index){
    if(index==1){
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle").classList.remove("unmidle")
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle").classList.add("midle")
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle span:nth-child(2)").classList.add("opacity_none")
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle span:nth-child(1)").classList.remove("cancel_animation__togle")
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle span:nth-child(3)").classList.remove("cancel_animation__togle_")
        await sleep(200)
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle span:nth-child(1)").classList.add("animation__togle")
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle span:nth-child(3)").classList.add("animation__togle_")
        await sleep(50)
        document.querySelector(".main .main__navbar .item__togle").classList.add("link__togle")
        
    }else{
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle span:nth-child(1)").classList.remove("animation__togle")
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle span:nth-child(3)").classList.remove("animation__togle_")
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle span:nth-child(1)").classList.add("cancel_animation__togle")
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle span:nth-child(3)").classList.add("cancel_animation__togle_")
        await sleep(200)
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle").classList.remove("midle")
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle").classList.add("unmidle")
        document.querySelector(".main .main__navbar .main__navbar--item:nth-child(2) .item__phone .item__phone--togle span:nth-child(2)").classList.remove("opacity_none")
        await sleep(50)
        document.querySelector(".main .main__navbar .item__togle").classList.remove("link__togle")
    }
}

export function hidden_navbar(){
    const observedDiv = document.querySelector('.main .main__navbar');
  
    const observer = new IntersectionObserver((entries, observer) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          entry.target.classList.remove('display__none');
        } else {
          entry.target.classList.add('display__none');
        }
      });
    }, { threshold: 0.7 });
    
    observer.observe(observedDiv);
  }

  export function set_height(){
    document.querySelector(".main__body").style.minHeight = "1000px"
  }

export default translatetoglenavbar