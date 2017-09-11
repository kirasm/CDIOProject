/**
 * Created by ldy on 25-04-2017.
 */

var username, name, phone, email, password = "";

function editUserButton() {

    var id = [];

    $("input:radio[name='radio']:checked").each(function (i, element) {

        id[i] = $(element).val();

    });

    if (confirm("Are you sure you want to edit this?")) {
        $.ajax({
            url: 'showUsers.php',
            method: 'GET',
            success: function (JSONObject) {

                var arrayLenght = Object.keys(JSONObject).length;

                for (var i = 0; i < arrayLenght; i++) {

                    if (JSONObject[i].username === id[0]) {

                        username = JSONObject[i].username;
                        name = JSONObject[i].name;
                        phone = JSONObject[i].phone;
                        email = JSONObject[i].email;
                        password = JSONObject[i].password;

                        $("#username").val(username);
                        $("#name").val(name);
                        $("#phone").val(phone);
                        $("#email").val(email);
                        $("#password").val(password);

                    }

                }

            }

        });

    }

}

function uploadEdit() {

    username = $("#username").val();
    name = $("#name").val();
    phone = $("#phone").val();
    email = $("#email").val();
    password = $("#password").val();

    if (username != "") {
        $.ajax({
            url: 'edit.php',
            method: 'POST',
            data: {username: username, name: name, email: email, password: password},
            success: function (thing) {

                location.reload();

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("Error: " + errorThrown + " " + XMLHttpRequest + " " + textStatus);

            }

        });

    }

}