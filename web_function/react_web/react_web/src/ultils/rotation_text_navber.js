export default function rotation_text_navber(){

    const a = document.querySelectorAll(".main .main__navbar .main__navbar--item:nth-child(2) .item__pc .item__pc--link")
    const b = document.querySelectorAll(".main .main__navbar .main__navbar--item:nth-child(2) .item__pc .item__pc--link a")
    
    for(let i = 0;i<b.length;i++){
        a[i].addEventListener('mouseenter', () => {
            b[i].classList.add("animation__3d--link")
          });
        a[i].addEventListener('mouseleave', () => {
            b[i].classList.remove("animation__3d--link")
          });
    }
}