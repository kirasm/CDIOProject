/**
 * Created by Rasmus on 6/6/2017.
 */
function prettyFloat(x, nbDec) {
    if (!nbDec) nbDec = 100;
    var a = Math.abs(x);
    var e = Math.floor(a);
    var d = Math.round((a - e) * nbDec);
    if (d == nbDec) {
        d = 0;
        e++;
    }
    var signStr = (x < 0) ? "-" : " ";
    var decStr = d.toString();
    var tmp = 10;
    while (tmp < nbDec && d * tmp < nbDec) {
        decStr = "0" + decStr;
        tmp *= 10;
    }
    var eStr = e.toString();
    return signStr + eStr + "." + decStr;
}

// Allows the user to press enter to Log In
$('#password').keydown(function (e) {
    if (e.keyCode == 13) {
        credentialCheck();
    }
});
$('#userName').keydown(function (e) {
    if (e.keyCode == 13) {
        credentialCheck();
    }
});

function logout() {
    setCookie("username", "", -9999999);
    setCookie("role", "", -999999);
    setCookie("name", "", -9999999);
    setCookie("department", "", -999999);
}

function getCurrentDate() {

    var d = new Date();

    d = d.getFullYear() + "-" + ('0' + (d.getMonth() + 1)).slice(-2) + "-" + ('0' + d.getDate()).slice(-2) + " " + ('0' + d.getHours()).slice(-2) + ":" + ('0' + d.getMinutes()).slice(-2) + ":" + ('0' + d.getSeconds()).slice(-2);

    return d

}


function reloadPage() {
    window.location.reload();
}


