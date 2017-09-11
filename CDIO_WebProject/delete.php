<?php

include 'Connection.php';

$choice = $_POST["tableChoice"];


if ($choice = 1) {

    if (isset($_POST["id"])) {

        $connection = connect();

        foreach ($_POST["id"] as $id) {

            $query = sprintf("DELETE FROM user WHERE User_Name = '%s'", $id);

            $connection->query($query);

        }
    }
} else
    if ($choice = 2) {

        if (isset($_POST["id"])) {

            $connection = connect();

            foreach ($_POST["id"] as $id) {

                $query = sprintf("DELETE FROM department WHERE Department_ID = '%s'", $id);

                $connection->query($query);

            }

            $connection->close();


        }
    }


?>

