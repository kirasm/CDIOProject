/**
 * Created by Rasmus on 6/9/2017.
 */



function departmentTableRender() {

    if (getCookie("role") > 1) {


        var headerArray = ["Department ID", "Address ID", "Name", "Type", "Owner ID", "Edit"]

        var table = document.getElementById("departmentTable");

        $("#departmentTable").empty();


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
            url: 'showDepartments.php',
            method: 'POST',
            dataType: 'json',
            data: {
                deps: $("#selectDep").val()
            },
            success: function (JSONObject) {

                var arrayLenght = Object.keys(JSONObject).length;

                for (var i = 0; i < arrayLenght; i++) {

                    var row = table.insertRow(i + 1);

                    var DepartmentIDCell = row.insertCell(0);
                    var AddressIDCell = row.insertCell(1);
                    var NameCell = row.insertCell(2);
                    var TypeCell = row.insertCell(3);
                    var OwnerIDCell = row.insertCell(4);
                    var EditCell = row.insertCell(5);


                    DepartmentIDCell.innerHTML = JSONObject[i].departmentID;
                    AddressIDCell.innerHTML = JSONObject[i].addressID;
                    NameCell.innerHTML = JSONObject[i].name;
                    TypeCell.innerHTML = JSONObject[i].type;
                    OwnerIDCell.innerHTML = JSONObject[i].ownerID;
                    EditCell.innerHTML = "<td>" + "<input type='radio'" + 'class="select_department_edit"' + 'name="radio"' + 'value="' + JSONObject[i].departmentID + '">';


                }

            }
        });

    }

}










