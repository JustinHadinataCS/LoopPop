function toggleHighlight() {
    var span = document.getElementById('highlight');
    span.classList.toggle('highlight');
}

setInterval(toggleHighlight, 700);

let lastScrollPosition = 0;
let ticking = false;

function updatePositions() {
    let scrollPosition = window.scrollY;

    document.querySelector('.brand-container').style.transform = `translateX(${-scrollPosition * 0.3}px)`;
    document.querySelector('.image-container').style.transform = `translateY(${-scrollPosition * 0.6}px)`;
    document.querySelector('.brand-container-2').style.transform = `translateX(${-scrollPosition * 0.6}px)`;
    document.querySelector('.brand-container-4').style.transform = `translateX(${scrollPosition * 0.2}px)`;
    document.querySelector('.long-Bar').style.transform = `translateX(${-scrollPosition * 0.5}px)`;

    document.querySelector('.brand-container-5').style.transform = `translateY(${-scrollPosition * 0.63}px)`;


    ticking = false;
}

function onScroll() {
    lastScrollPosition = window.scrollY;

    if (!ticking) {
        window.requestAnimationFrame(updatePositions);
        ticking = true;
    }
}

window.addEventListener('scroll', onScroll);
AOS.init({
    duration: 400, // animation duration in milliseconds
    once: true, // whether animation should happen only once - while scrolling down
    mirror: false, // whether elements should animate out while scrolling past them
});
