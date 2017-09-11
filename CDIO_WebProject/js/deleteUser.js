/**
 * Created by ldy on 25-04-2017.
 */
function deleteUserButton() {

    var id = [];
    var tableChoice = "";

    $('.delete_customer').each(function (i, element) {
        if (element.checked == true) {
            id[i] = $(element).val();
            tableChoice = 1;

        }
    });

    if (id.length == 0) {
        alert("Please Select at least one checkbox");
    }

    else {

        if (confirm("Are you sure you want to delete this?")) {
            $.ajax({
                url: 'delete.php',
                method: 'POST',
                data: {id: id, tableChoice: tableChoice},
                success: function () {
                    $.each(id, function (i, element) {

                        $('*').filter(function () {
                            return this.id == element;
                        }).remove();

                    });
                }

            });

        }

    }

}