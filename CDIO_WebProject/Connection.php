<?php

function connect()
{


    $servername = 'dyrsted.duckdns.org';
    $username = 'api';
    $password = '';
    $dbname = 'cdio_d2';

    // Create connection
    $conn = new mysqli($servername, $username, $password, $dbname);

    mysqli_set_charset($conn,"utf8");

    // Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    return $conn;

}

function console_log($data)
{
    echo '<script>';
    echo 'alert(' . $data . ')';
    echo '</script>';
}

?>