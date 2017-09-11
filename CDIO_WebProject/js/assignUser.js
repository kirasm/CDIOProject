function init_assignUser() {

    $(".js-data-example-ajax").select2({
        placeholder: 'Select an option',
        width: '50pc',
        ajax: {
            url: "./assignUser.php",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    q: params.term // search term
                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (obj) {
                        return {id: obj.username, text: obj.username};
                    })
                };
            },
            cache: true
        },

        minimumInputLength: 1
    });

    $("#submit").click(function () {

        var userlist = $(".js-data-example-ajax").val();
        $.ajax({
            url: 'assignUser.php',
            method: 'POST',
            data: {users: userlist},
            success: function () {
                $("select").val(null).trigger("change");
            }

        });
    });
}
