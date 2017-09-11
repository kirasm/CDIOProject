$(document).ready(function () {
    $.fn.select2.defaults.reset();


    if (getCookie("username") == "") {
        window.location.href = "index.php";

        return;
    }

    if (getCookie("department") == "undefined" && getCookie("role") != 5) {

        $("#mainWindow").load("missingInfo.html", function (response, status, xhr) {
            if (status === "success") {
                topNavRender();
                $("#assignUser").parent().remove();
                $("#sensors").parent().remove();
            }
        });
        return;
    }


    $("#dashboard").click(function () {
        $("#mainWindow").load("dashboardContent.html", function (response, status, xhr) {
            if (status === "success") {

                init_dashboard();
            }

        });
    });


    $("#signup").click(function () {

        $("#mainWindow").load("signUp.html", function (response, status, xhr) {
            if (status == "success") {
            }

        });
    });

    if (getCookie("role") < 2) {
        $("#assignUser").parent().remove();

    } else {

        $("#assignUser").click(function () {

            $("#mainWindow").load("assignUser.html", function (response, status, xhr) {
                if (status == "success") {

                    init_assignUser();
                }

            });
        });
    }


    $("#dashboard").click();

});