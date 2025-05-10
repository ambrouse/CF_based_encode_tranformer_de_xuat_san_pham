
export function scroll_wall(){
  /*
    animation cho wall
  */
  function scroll_img() {
    const scrollY = window.scrollY;
    document.querySelector('.home__main .home__main--item .item__wall .item__wall--background .background__img').style.transform = 'translateY('+(Math.round(-scrollY/3))+'px)'
  }
  const observer = new IntersectionObserver((entries, observer) => {
    if(!entries){return null}
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        window.addEventListener('scroll',scroll_img );
      }else {
        window.removeEventListener('scroll',scroll_img)
      }
    });
  }, { threshold: 0.2});
  const observedDiv = document.querySelector('.home__main .home__main--item .item__wall .item__wall--background');
  observer.observe(observedDiv);
  function scroll_wall_text() {
    const scrollY = window.scrollY;
    document.querySelector('.home__main .home__main--item .item__wall .item__wall--info .info__content').style.transform = 'translateY('+(Math.round(-scrollY/4))+'px)'
    document.querySelector('.home__main .home__main--item .item__wall .item__wall--info .info__title').style.transform = 'translateY('+(Math.round(-scrollY)/4)+'px)'
    document.querySelector('.home__main .home__main--item .item__wall .item__wall--info .info__contac').style.transform = 'translateY('+(Math.round(-scrollY)/8)+'px)'
  }
  const observer_wall_text = new IntersectionObserver((entries, observer) => {
    if(!entries){return null}
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        window.addEventListener('scroll',scroll_wall_text)
      }else {
        window.removeEventListener('scroll',scroll_wall_text)
      }
    });
  }, { threshold: 0.2});
  const observedDiv_wall_infor = document.querySelector('.home__main .home__main--item .item__wall .item__wall--info');
  observedDiv_wall_infor?observer_wall_text.observe(observedDiv_wall_infor):null
  
  
  /*
    animation cho block seasion
  */
  const observer_seasion_icon = new IntersectionObserver((entries, observer) => {
    if(!entries){return null}
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--title .title__icon .title__icon--img:nth-child(1)')?.classList.remove("title__icon--animation")
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--title .title__icon .title__icon--img:nth-child(2)')?.classList.remove("title__icon--animation")
      }else {
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--title .title__icon .title__icon--img:nth-child(1)')?.classList.add("title__icon--animation")
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--title .title__icon .title__icon--img:nth-child(2)')?.classList.add("title__icon--animation")
      }
    });
  }, { threshold: 0.2});
  const observedDiv_seasion_icon = document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--title .title__icon');
  observedDiv_seasion_icon?observer_seasion_icon.observe(observedDiv_seasion_icon):null;
  const observer_seasion_title = new IntersectionObserver((entries, observer) => {
    if(!entries){return null}
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--title .title__content p')?.classList.remove("title__content--animation")
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--title .title__content p')?.classList.remove("title__content--animation")
      }else {
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--title .title__content p')?.classList.add("title__content--animation")
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--title .title__content p')?.classList.add("title__content--animation")
      }
    });
  }, { threshold: 0.2});
  const observedDiv_seasion_title = document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--title .title__content');
  observedDiv_seasion_title?observer_seasion_title.observe(observedDiv_seasion_title):null;
  const observer_seasion_item = new IntersectionObserver((entries, observer) => {
    if(!entries){return null}
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--option .option__item:nth-child(1)')?.classList.remove("option__item--animationleft1")
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--option .option__item:nth-child(2)')?.classList.remove("option__item--animationleft2")
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--option .option__item:nth-child(3)')?.classList.remove("option__item--animationright1")
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--option .option__item:nth-child(4)')?.classList.remove("option__item--animationright2")
      }else {
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--option .option__item:nth-child(1)')?.classList.add("option__item--animationleft1")
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--option .option__item:nth-child(2)')?.classList.add("option__item--animationleft2")
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--option .option__item:nth-child(3)')?.classList.add("option__item--animationright1")
        document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--option .option__item:nth-child(4)')?.classList.add("option__item--animationright2")
      }
    });
  }, { threshold: 0.2});
  const observedDiv_seasion_item = document.querySelector('.home__main .home__main--item .item__seasion .item__seasion--option');
  observedDiv_seasion_item?observer_seasion_item.observe(observedDiv_seasion_item):null;
  
  
  
  /*
  animation cho block quality1 va quality2
  */
 const observer_quality = new IntersectionObserver((entries, observer) => {
  if(!entries){return null}
   entries.forEach(entry => {
     if (entry.isIntersecting) {
       document.querySelector('.home__main .home__main--item .item__quality .item__quality--decription')?.classList.remove("item__quality--animationdecrip")
       document.querySelector('.home__main .home__main--item .item__quality .item__quality--background')?.classList.remove("item__quality--animationback")
      }else {
       document.querySelector('.home__main .home__main--item .item__quality .item__quality--decription')?.classList.add("item__quality--animationdecrip")
       document.querySelector('.home__main .home__main--item .item__quality .item__quality--background')?.classList.add("item__quality--animationback")
     }
   });
 }, { threshold: 0.2});
 const observedDiv_quality = document.querySelector('.home__main .home__main--item .item__quality');
 observedDiv_quality?observer_quality.observe(observedDiv_quality):null;
 const observer_quality2 = new IntersectionObserver((entries, observer) => {
  if(!entries){return null}
   entries.forEach(entry => {
     if (entry.isIntersecting) {
       document.querySelector('.home__main .home__main--item:nth-child(6) .item__quality .item__quality--decription')?.classList.remove("item__quality--animationback")
       document.querySelector('.home__main .home__main--item:nth-child(6) .item__quality .item__quality--background')?.classList.remove("item__quality--animationdecrip")
      }else {
       document.querySelector('.home__main .home__main--item:nth-child(6) .item__quality .item__quality--decription')?.classList.add("item__quality--animationback")
       document.querySelector('.home__main .home__main--item:nth-child(6) .item__quality .item__quality--background')?.classList.add("item__quality--animationdecrip")
     }
   });
 }, { threshold: 0.2});
 const observedDiv_quality2 = document.querySelector('.home__main .home__main--item:nth-child(6) .item__quality');
 observedDiv_quality2?observer_quality2.observe(observedDiv_quality2):null;
 
 
 /*
 animation cho block slide 1 va slide 2
 */
const observer_slide1 = new IntersectionObserver((entries, observer) => {
  if(!entries){return null}
  entries.forEach(entry => {
    let infex_ = true;
    if (entry.isIntersecting) {
      document.querySelector(".home__main .home__main--item:nth-child(4) .item__carousel .item__carousel--title p")?.classList.remove('item__carousel--titleanimation')
      document.querySelectorAll(".home__main .home__main--item:nth-child(4) .item__carousel .item__carousel--slide .slide__main .slide__main--item").forEach((i)=>{
        if(infex_){
          i?.classList.remove("slide__main--itemanimationbottom")
          infex_=false
        }else{
          i?.classList.remove("slide__main--itemanimationtop")
          infex_=true
        }
      })
    }else {
      document.querySelector(".home__main .home__main--item:nth-child(4) .item__carousel .item__carousel--title p")?.classList.add('item__carousel--titleanimation')
      document.querySelectorAll(".home__main .home__main--item:nth-child(4) .item__carousel .item__carousel--slide .slide__main .slide__main--item").forEach((i)=>{
        if(infex_){
          i?.classList.add("slide__main--itemanimationbottom")
          infex_=false
        }else{
          i?.classList.add("slide__main--itemanimationtop")
          infex_=true
        }
      })
    }
  });
}, { threshold: 0.2});
const observedDiv_slide1 = document.querySelector('.home__main .home__main--item:nth-child(4) .item__carousel .item__carousel--slide .slide__main');
observedDiv_slide1?observer_slide1.observe(observedDiv_slide1):null;
const observer_slide2 = new IntersectionObserver((entries, observer) => {
  if(!entries){return null}
  entries.forEach(entry => {
    let infex_ = true;
    if (entry.isIntersecting) {
      document.querySelector(".home__main .home__main--item:nth-child(5) .item__carousel .item__carousel--title p")?.classList.remove('item__carousel--titleanimation')
      document.querySelectorAll(".home__main .home__main--item:nth-child(5) .item__carousel .item__carousel--slide .slide__main .slide__main--item").forEach((i)=>{
        if(infex_){
          i?.classList.remove("slide__main--itemanimationbottom")
          infex_=false
        }else{
          i?.classList.remove("slide__main--itemanimationtop")
          infex_=true
        }
      })
    }else {
      document.querySelector(".home__main .home__main--item:nth-child(5) .item__carousel .item__carousel--title p")?.classList.add('item__carousel--titleanimation')
      document.querySelectorAll(".home__main .home__main--item:nth-child(5) .item__carousel .item__carousel--slide .slide__main .slide__main--item").forEach((i)=>{
        if(infex_){
          i?.classList.add("slide__main--itemanimationbottom")
          infex_=false
        }else{
          i?.classList.add("slide__main--itemanimationtop")
          infex_=true
        }
      })
    }
  });
}, { threshold: 0.2});
const observedDiv_slide2 = document.querySelector('.home__main .home__main--item:nth-child(5) .item__carousel .item__carousel--slide .slide__main');
observedDiv_slide2?observer_slide2.observe(observedDiv_slide2):null;


  if(window.innerWidth>900){
    document.querySelectorAll(".home__main .home__main--item .item__carousel").forEach((i)=>{
      i.style.width = (Math.floor(window.innerWidth/300)*300)+"px"
    })
  }else if(window.innerWidth>700){
    document.querySelectorAll(".home__main .home__main--item .item__carousel").forEach((i)=>{
      i.style.width = (Math.floor(window.innerWidth/200)*200)+"px"
    })
  }else{
    document.querySelectorAll(".home__main .home__main--item .item__carousel").forEach((i)=>{
      i.style.width = (Math.floor(window.innerWidth/150)*150)+"px"
    })
  }

  
  document.querySelector(".home__main .home__main--item:nth-child(4) .item__carousel .slide__button .slide__button--img:nth-child(1)").addEventListener('click',()=>{  
    console.log(document.querySelector(".home__main .home__main--item:nth-child(4) .item__carousel .item__carousel--slide .slide__main").scrollLeft)
    document.querySelector(".home__main .home__main--item:nth-child(4) .item__carousel .item__carousel--slide .slide__main").scrollLeft=document.querySelector(".home__main .home__main--item:nth-child(4) .item__carousel .item__carousel--slide .slide__main").scrollLeft+document.querySelector(".home__main .home__main--item:nth-child(4) .item__carousel .item__carousel--slide .slide__main .slide__main--item").clientWidth
  })
  document.querySelector(".home__main .home__main--item:nth-child(4) .item__carousel .slide__button .slide__button--img:nth-child(2)").addEventListener('click',()=>{
    document.querySelector(".home__main .home__main--item:nth-child(4) .item__carousel .item__carousel--slide .slide__main").scrollLeft=document.querySelector(".home__main .home__main--item:nth-child(4) .item__carousel .item__carousel--slide .slide__main").scrollLeft-document.querySelector(".home__main .home__main--item:nth-child(4) .item__carousel .item__carousel--slide .slide__main .slide__main--item").clientWidth
  })
  document.querySelector(".home__main .home__main--item:nth-child(5) .item__carousel .slide__button .slide__button--img:nth-child(1)").addEventListener('click',()=>{  
    document.querySelector(".home__main .home__main--item:nth-child(5) .item__carousel .item__carousel--slide .slide__main").scrollLeft=document.querySelector(".home__main .home__main--item:nth-child(5) .item__carousel .item__carousel--slide .slide__main").scrollLeft+document.querySelector(".home__main .home__main--item:nth-child(5) .item__carousel .item__carousel--slide .slide__main .slide__main--item").clientWidth
  })
  document.querySelector(".home__main .home__main--item:nth-child(5) .item__carousel .slide__button .slide__button--img:nth-child(2)").addEventListener('click',()=>{
    document.querySelector(".home__main .home__main--item:nth-child(5) .item__carousel .item__carousel--slide .slide__main").scrollLeft=document.querySelector(".home__main .home__main--item:nth-child(5) .item__carousel .item__carousel--slide .slide__main").scrollLeft-document.querySelector(".home__main .home__main--item:nth-child(5) .item__carousel .item__carousel--slide .slide__main .slide__main--item").clientWidth
  })

  /*
    animaton goodbye block
  */
    const observer_goodbye = new IntersectionObserver((entries, observer) => {
      if(!entries){return null}
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          document.querySelector(".home__main .home__main--item .item__goodbye .item__goodbye--contact")?.classList.remove("item__contact--animation")
          document.querySelector(".home__main .home__main--item .item__goodbye .item__goodbye--content")?.classList.remove("item__content--animation")
          document.querySelector(".home__main .home__main--item .item__goodbye .item__goodbye--title")?.classList.remove("item__title--animation")
        }else {
           document.querySelector(".home__main .home__main--item .item__goodbye .item__goodbye--contact")?.classList.add("item__contact--animation")
           document.querySelector(".home__main .home__main--item .item__goodbye .item__goodbye--content")?.classList.add("item__content--animation")
           document.querySelector(".home__main .home__main--item .item__goodbye .item__goodbye--title")?.classList.add("item__title--animation")
        }
      });
    }, { threshold: 0.6});
    const observedDiv_goodbye = document.querySelector('.home__main .home__main--item .item__goodbye');
    observedDiv_goodbye?observer_goodbye.observe(observedDiv_goodbye):null;

}

