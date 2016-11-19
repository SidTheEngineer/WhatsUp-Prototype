window.document.onload = function() {
    initMap();
}

function initMap() {
    // Create a map object and specify the DOM element for display.
    var map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 28.601660, lng: -81.200788},
        scrollwheel: false,
        zoom: 15
    });
}
