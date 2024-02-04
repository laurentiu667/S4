let batterywidth = document.querySelector('.upBattery');
let buttonActiver = document.querySelector('.container-button');
function battery() {
    let timer = setInterval(function() {
        let targetWidth = 66;
        let currentWidth = parseInt(batterywidth.style.width) || 0; // Utilisez 0 si la largeur n'est pas définie
        let increment = 1;
        if (currentWidth < targetWidth) {
            currentWidth += increment;
            batterywidth.style.width = currentWidth + '%';
        } else {
            clearInterval(timer);
        }
    }, 1);
}
buttonActiver.addEventListener('click', () => {
    battery();
})

