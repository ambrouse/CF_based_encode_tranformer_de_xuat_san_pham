export async function notification_active(mode,text){
    if(mode){
        document.querySelector(".notification").classList.remove("notification__errmode")
        document.querySelector(".notification .notification__box--title p").innerHTML = "Success"
    }else{
        document.querySelector(".notification .notification__box--title p").innerHTML = "Failure"
        document.querySelector(".notification").classList.add("notification__errmode")
    }
    document.querySelector(".notification .notification__box--content p").innerHTML = text
    document.querySelector(".notification").classList.add("notification__active")
    document.querySelector(".notification .notification__box").classList.add("notification__boxactive")
    document.querySelector(".notification .notification__box--tick").classList.add("notification__box--tickactive")
    document.querySelector(".notification .notification__box--title").classList.add("notification__box--titleactive")
    document.querySelector(".notification .notification__box--content").classList.add("notification__box--contentactive")
    document.querySelector(".notification .notification__box--out").classList.add("notification__box--outactive") 
}

export async function notification_closs(){
    document.querySelector(".notification").classList.remove("notification__errmode")
    document.querySelector(".notification").classList.remove("notification__active")
    document.querySelector(".notification .notification__box--content p").innerHTML = ""
    document.querySelector(".notification .notification__box--title p").innerHTML = ""
    document.querySelector(".notification .notification__box").classList.remove("notification__boxactive")
    document.querySelector(".notification .notification__box--tick").classList.remove("notification__box--tickactive")
    document.querySelector(".notification .notification__box--title").classList.remove("notification__box--titleactive")
    document.querySelector(".notification .notification__box--content").classList.remove("notification__box--contentactive")
    document.querySelector(".notification .notification__box--out").classList.remove("notification__box--outactive") 
}