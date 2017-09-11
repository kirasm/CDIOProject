/**
 * Created by razze on 03-06-2017.
 */

function credentialCheck() {

    var fileName = "token";

    var username = $("#userName").val();
    var password = $("#password").val();

    $.ajax({

        url: fileName + '.php',
        method: 'POST',
        data: {user: username, pass: password},
        dataType: 'json',
        success: function (JSONObject) {

            setCookie("role", JSONObject[0].role, 300000000000);
            setCookie("username", JSONObject[0].username, 300000000000);
            setCookie("name", JSONObject[0].name, 300000000000);
            setCookie("department", JSONObject[0].department, 300000000000);

            // similar behavior as clicking on a link
            window.location.href = "dashboard.html";


        },

        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("Check your password or your internet connection lol  " + textStatus + " " + errorThrown + " " + XMLHttpRequest);

        }

    });

}




