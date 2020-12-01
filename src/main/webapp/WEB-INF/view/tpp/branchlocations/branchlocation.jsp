<!DOCTYPE html>
    <html>

    <head>
        <style>
            /*  <span class="metadata-marker" style="display: none;" data-region_tag="css"></span>       Set the size of the div element that contains the map */
            #map {
                height: 400px;
                /* The height is 400 pixels */
                width: 100%;
                /* The width is the width of the web page */
            }
        </style>
              <script type="text/javascript">
$("#loading-text").text('Fetching Branch from Bank');
</script>
        <script>
            var map;
            var InforObj = [];
            var centerCords = {
                lat: 51.50853,
                lng: -0.12574
            };
            var markersOnMap = [{
                    placeName: "Liverpool",
                    content: '<div id="content"><label>Branch Name</label><br>Radio City Tower Branch<br><label>Street Name</label><br>Radio City Tower Road<br><label>Working Hours</label><br>9 am to 5 pm<br></div>',
                    LatLng: [{
                        lat: 53.406315,
                        lng: -2.981979
                    }]
                },
                {
                	placeName: "Portsmouth",
                    content: '<div id="content"><label>Branch Name</label><br>Spinnaker Tower Branch<br><label>Street Name</label><br>Spinnaker Tower Road<br><label>Working Hours</label><br>9:30 am to 5:30 pm<br></div>',
                    LatLng: [{
                        lat: 50.795574,
                        lng: -1.108517
                    }]
                },
                {
                	placeName: "Glasgow",
                    content: '<div id="content"><label>Branch Name</label><br>Glasgow Tower Branch<br><label>Street Name</label><br>Glasgow Tower Road<br><label>Working Hours</label><br>10 am to 7 pm<br></div>', 
                    LatLng: [{
                        lat: 51.7789454,
                        lng: -1.2657953
                    }]
                },
                {
                	placeName: "Northampton",
                    content: '<div id="content"><label>Branch Name</label><br>National Lift Tower Branch<br><label>Street Name</label><br>Tower Square Road<br><label>Working Hours</label><br>9:30 am to 6:30 pm<br></div>', 
                    LatLng: [{
                        lat: 51.7789454,
                        lng: -1.2657953
                    }]
                },
                {
                	placeName: "Lancashire",
                    content: '<div id="content"><label>Branch Name</label><br>Blackpool Tower Branch<br><label>Street Name</label><br>Blackpool Road<br><label>Working Hours</label><br>11 am to 7 pm<br></div>',
                    LatLng: [{
                        lat: 51.7789454,
                        lng: -1.2657953
                    }]
                }
            ];

            window.onload = function () {
                initMap();
            };

            function addMarkerInfo() {
                for (var i = 0; i < markersOnMap.length; i++) {
                    var contentString = markersOnMap[i].content;

                    const marker = new google.maps.Marker({
                        position: markersOnMap[i].LatLng[0],
                        map: map
                    });

                    const infowindow = new google.maps.InfoWindow({
                        content: contentString,
                        maxWidth: 200
                    });

                    marker.addListener('click', function () {
                        closeOtherInfo();
                        infowindow.open(marker.get('map'), marker);
                        InforObj[0] = infowindow;
                    });
                    // marker.addListener('mouseover', function () {
                    //     closeOtherInfo();
                    //     infowindow.open(marker.get('map'), marker);
                    //     InforObj[0] = infowindow;
                    // });
                    // marker.addListener('mouseout', function () {
                    //     closeOtherInfo();
                    //     infowindow.close();
                    //     InforObj[0] = infowindow;
                    // });
                }
            }

            function closeOtherInfo() {
                if (InforObj.length > 0) {
                    /* detach the info-window from the marker ... undocumented in the API docs */
                    InforObj[0].set("marker", null);
                    /* and close it */
                    InforObj[0].close();
                    /* blank the array */
                    InforObj.length = 0;
                }
            }

            function initMap() {
                map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 4,
                    center: centerCords
                });
                addMarkerInfo();
            }
        </script>
    </head>

    <body>
        <h3>Branch Details</h3>
        <!--The div element for the map -->
        <div id="map"></div>

        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDTuS0AatOA6j0DzCZLLympHOOMpp84kvA"></script>

    </body>

    </html>