/**
 * Created by Rasmus on 6/2/2017.
 */

var notif = 0;

function sensorRender(role, department) {

    // Made as an array to be able to reuse, if we want more parameters for the sensordata
    // F.ex. a start and an enddate for a dataset
    var dataConfig = {};


    if (role > 0) {
        dataConfig = {dataConfig: "currentSensorsDepartment", dep: department};
    }
    if (role > 4) {
        dataConfig = {dataConfig: "currentSensorsAll"};
    }

    $.ajax({
        url: 'sensorData.php',
        method: 'POST',
        data: dataConfig,
        success: function (JSONObject) {

            var arrayLenght = Object.keys(JSONObject).length;

            $('#sensors').empty();
            $('#messages').empty();


            notif = 0;

            // Renders the list
            for (var i = 0; i < arrayLenght; i++) {

                loadList(JSONObject[i]);
            }

            getNotifications(department);

            $(".sensor").click(function () {
                var sensorID = this.getAttribute("data-id");

                $("#mainWindow").load("charts.html", function (response, status, xhr) {
                    if (status == "success") {

                        loadSensorHeader(sensorID, JSONObject);
                        loadCharts();
                        reload(sensorID);
                        $("#updateChart").click(function () {
                            reload(sensorID);
                        });
                    }

                });
            });
        },

        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("Error RenderSensors: " + errorThrown);
        }

    });

}
function loadSensorHeader(sensorID, JSONObject) {

    for (var i=0; i < JSONObject.length; i++) {

        if (JSONObject[i].sensorID == sensorID) {
            var selectedSensor = JSONObject[i];
        }
    }


}


function loadNotifications(JSONObject) {

    $('#notifNumber').empty();

    var ulSensor = document.getElementById("messages");
    var spanNotif = document.getElementById("notifNumber");
    var liSensor = document.createElement("li");
    var a = document.createElement("a");
    var span = document.createElement("span");
    var text1 = document.createTextNode(JSONObject.ApplianceID);
    var text2 = document.createTextNode(prettyFloat(JSONObject.Value, 2));
    var text3 = document.createTextNode(++notif);
    spanNotif.appendChild(text3);

    var button = document.createElement("button");
    button.setAttribute("class", "deleteNotification");
    var text2B = document.createTextNode("X");
    button.appendChild(text2B);

    $('#notificationNumber').empty();

    liSensor.setAttribute('id', JSONObject.sensorID);

    span.appendChild(text2);
    span.setAttribute("class", "temperatureText");

    liSensor.appendChild(span);
    a.appendChild(text1);
    a.appendChild(button);
    a.appendChild(span);


    liSensor.appendChild(a);

    ulSensor.appendChild(liSensor);

}

function loadList(JSONObject) {


    var sensorNameText = document.createTextNode(JSONObject.ApplianceID);
    var text1 = document.createTextNode(prettyFloat(JSONObject.Value, 2));

    var ulSensor = document.getElementById("sensors");
    var liSensor = document.createElement("li");
    var a = document.createElement("a");
    var temperatureText = document.createElement("span");

    liSensor.setAttribute('data-id', JSONObject.sensorID);
    liSensor.setAttribute('class', "sensor");

    ulSensor.setAttribute("class", "nav child_menu");

    temperatureText.appendChild(text1);

    var sensorName = document.createElement("a");
    a.appendChild(sensorNameText);
    a.setAttribute("class", "sensorName");

    temperatureText.setAttribute("class", "temperatureText");


    //a.appendChild(sensorName);
    a.appendChild(temperatureText);

    liSensor.appendChild(a);
    ulSensor.appendChild(liSensor);

}

function getNotifications(department) {


    $.ajax({

        url: 'alarmData.php',
        method: 'POST',
        dataType: 'json',
        data: {dep: department},
        success: function (JSONObject) {

            for (var i = 0; i < JSONObject.length; i++) {

                loadNotifications(JSONObject[i]);
            }


        },

        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("Error Notifications" + textStatus + " " + errorThrown + " " + XMLHttpRequest);

        }

    });


}


