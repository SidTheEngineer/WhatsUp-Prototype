var map = null;

function initMap() {

    // Location constants.
    var STUDENT_UNION = {
        lat: 28.601660,
        lng: -81.200788
    }

    // Create a map object and specify the DOM element for display.
        map = new google.maps.Map(document.getElementById('map'), {
        center: STUDENT_UNION,
        zoom: 15,
        maxZoom: 18,
        minZoom: 15,
        mapTypeControl: false,
        streetViewControl: false,
        zoomControl: false,
        backGroundColor: 'white',
    });

    google.maps.event.addListener(map,'center_changed',function() { checkBounds(); });
}

function checkBounds() { 

    var NORTHEAST_BOUND = {
        lat: 28.610801,
        lng: -81.187634
    }

    var SOUTHWEST_BOUND = {
        lat: 28.592263,
        lng: -81.207118
    }

    var allowedBounds = new google.maps.LatLngBounds(SOUTHWEST_BOUND, NORTHEAST_BOUND);

    if(! allowedBounds.contains(map.getCenter())) {
      var C = map.getCenter();
      var X = C.lng();
      var Y = C.lat();

      var AmaxX = allowedBounds.getNorthEast().lng();
      var AmaxY = allowedBounds.getNorthEast().lat();
      var AminX = allowedBounds.getSouthWest().lng();
      var AminY = allowedBounds.getSouthWest().lat();

      if (X < AminX) {X = AminX;}
      if (X > AmaxX) {X = AmaxX;}
      if (Y < AminY) {Y = AminY;}
      if (Y > AmaxY) {Y = AmaxY;}

      map.setCenter(new google.maps.LatLng(Y,X));
    }
}

