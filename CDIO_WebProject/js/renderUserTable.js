function userTableRender() {
    var headerArray;

    if (getCookie("role") > 1) {
        var headerArray = ["Username", "Name", "Email", "Password", "Phone", "Role", "Edit", "Delete"];

    } else {
        var headerArray = ["Username", "Name", "Email", "Password", "Phone", "Role"];

        $("#userhandle").remove();
    }
    var table = document.getElementById("userTable");

    $("#userTable").empty();


// Create an empty <thead> element and add it to the table:
    var header = table.createTHead();

// Create an empty <tr> element and add it to the first position of <thead>:
    var row = header.insertRow(0);

// Creates the headers for the columns in the table
    for (var i = 0; i < headerArray.length; i++) {

        var cell = row.insertCell(i);
        // Add some bold text in the new cell:
        cell.innerHTML = "<b>" + headerArray[i] + "</b>";

    }

    $.ajax({
        url: './showUsers.php',
        method: 'POST',
        dataType: 'json',
        data: {
            deps: $("#selectDep").val()
        },
        success: function (JSONObject) {

            var arrayLenght = Object.keys(JSONObject).length;

            var j = 1;

            for (var i = 0; i < arrayLenght; i++) {


                if (JSONObject[i].role <= getCookie("role")) {

                    var row = table.insertRow(j);

                    var UsernameCell    = row.insertCell(0);
                    var NameCell        = row.insertCell(1);
                    var EmailCell       = row.insertCell(2);
                    var PasswordCell    = row.insertCell(3);
                    var PhoneCell       = row.insertCell(4);
                    var RoleCell        = row.insertCell(5);

                    if (getCookie("role") > 1) {
                        var EditCell    = row.insertCell(6);
                        var DeleteCell  = row.insertCell(7);
                    }

                    UsernameCell.innerHTML  = JSONObject[i].username;
                    NameCell.innerHTML      = JSONObject[i].name;
                    EmailCell.innerHTML     = JSONObject[i].email;
                    PasswordCell.innerHTML  = JSONObject[i].password;
                    PhoneCell.innerHTML     = JSONObject[i].phone;
                    RoleCell.innerHTML      = JSONObject[i].role;
                    if (getCookie("role") > 1) {

                        EditCell.innerHTML = "<td>" + "<input type='radio'" + 'class="select_user_edit"' + 'name="radio"' + 'value="' + JSONObject[i].username + '">';
                        DeleteCell.innerHTML = "<td>" + "<input type='checkbox'" + 'class="delete_customer"' + 'name="hey"' + 'value="' + JSONObject[i].username + '">';
                    }
                    j++;
                }


            }

        }
    });


}













