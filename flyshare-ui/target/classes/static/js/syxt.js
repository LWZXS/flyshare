
var swiper = new Swiper('.swiper-banner', {
    wrapperClass: 'swiper-wrapper2',
    pagination: '.swiper-pagination2',
    grabCursor: true,
    paginationClickable: true,
    autoplayDisableOnInteraction: false,

    nextButton: '.swiper-button-next',
    prevButton: '.swiper-button-prev',
    direction: 'horizontal',
    autoplay: 3000,
    loop: true
});

var swiper2 = new Swiper('.swiper-container', {
    wrapperClass: 'swiper-wrapper1',
    pagination: '.swiper-pagination1',
    grabCursor: true,
    mousewheelControl: true,
    paginationClickable: true,
    // nextButton: '.swiper-button-next',
    // prevButton: '.swiper-button-prev',
    direction: 'vertical'
});
var swiper3 = new Swiper('.swiper-small', {
	  	grabCursor: true,
	    paginationClickable: true,
	    autoplayDisableOnInteraction: false,
	    paginationClickable: true,
	    direction: 'horizontal',
	    autoplay: 3000,
});
// document.querySelector('.swiper-button-down').addEventListener('click', function () {
//     swiper2.slideTo(1, 1000, true);
// })