import sleep from "./sleep"

async function translateloginform(index){
    if(index==0){
        document.getElementsByClassName("form__container--item")[0].classList.add("translate__container--hidden")
        document.getElementsByClassName("form__container--item")[1].classList.remove("translate__container--hidden")
        document.getElementsByClassName("form__container--item")[1].classList.add("translate__container--block")
        document.getElementsByClassName("form__container--item")[0].classList.remove("translate__container--block")
        document.getElementsByClassName("form__background--item")[0].classList.add("translate__backgritem--moveright")
        document.getElementsByClassName("form__background--item")[1].classList.remove("translate__backgritem--moveleft")
        await sleep(300)
        document.getElementsByClassName("form__background--item")[0].classList.add("translate__backgritem--hidden")
        document.getElementsByClassName("form__background--item")[1].classList.remove("translate__backgritem--hidden")
        document.getElementsByClassName("form__background")[0].classList.add("translate__backgr--moveleft");
    }else{
        document.getElementsByClassName("form__container--item")[1].classList.add("translate__container--hidden")
        document.getElementsByClassName("form__container--item")[0].classList.remove("translate__container--hidden")
        document.getElementsByClassName("form__container--item")[0].classList.add("translate__container--block")
        document.getElementsByClassName("form__container--item")[1].classList.remove("translate__container--block")
        document.getElementsByClassName("form__background--item")[1].classList.add("translate__backgritem--moveleft")
        document.getElementsByClassName("form__background--item")[0].classList.remove("translate__backgritem--moveright")
        await sleep(300)
        document.getElementsByClassName("form__background--item")[1].classList.add("translate__backgritem--hidden")
        document.getElementsByClassName("form__background--item")[0].classList.remove("translate__backgritem--hidden")
        document.getElementsByClassName("form__background")[0].classList.remove("translate__backgr--moveleft");
    }
}

export default translateloginform