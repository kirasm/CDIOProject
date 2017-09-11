<?php

include 'Connection.php';


$personArray = array();


if ((empty($_POST['deps']) OR ($_POST['deps'] == "")) AND $_COOKIE["role"] <= 2) {
    $conn = connect();

    $query = sprintf("CALL Get_All_Users_In_Dep('%s');", $_COOKIE["department"]);

    $result = $conn->query($query);

    while ($row = $result->fetch_assoc()) {

        $personObject = (object)[
            'username' => (string)$row['User_Name'],
            'name' => (string)$row['Name'],
            'phone' => (string)$row['Phone'],
            'email' => (string)$row['Email'],
            'password' => (string)$row['Password'],
            'role' => (string)$row['Role'],
        ];

        $personArray[] = $personObject;

    }
    //$result->close();

    $conn->close();


    header("Content-Type: application/json;charset=utf-8");
    echo json_encode($personArray);
    //echo "[]";
} elseif (!empty($_POST['deps']) AND ($_POST['deps'] != "")) {


    foreach ($_POST['deps'] as $value) {
        $conn = connect();


        $query = sprintf("CALL Get_All_Users_In_Dep('%s');", $value);

        $result = $conn->query($query);

        while ($row = $result->fetch_assoc()) {

            $personObject = (object)[
                'username' => (string)$row['User_Name'],
                'name' => (string)$row['Name'],
                'phone' => (string)$row['Phone'],
                'email' => (string)$row['Email'],
                'password' => (string)$row['Password'],
                'role' => (string)$row['Role'],
            ];

            $personArray[] = $personObject;

        }
        //$result->close();

        $conn->close();

    }
    header("Content-Type: application/json;charset=utf-8");
    echo json_encode($personArray);
} else {
    header("Content-Type: application/json;charset=utf-8");
    echo "[]";
}





