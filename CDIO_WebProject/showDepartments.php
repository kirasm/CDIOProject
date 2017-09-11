<?php

include 'Connection.php';

$departmentArray = array();


if ((empty($_POST['deps']) OR ($_POST['deps'] == "")) AND $_COOKIE["role"] <= 2) {
    $conn = connect();


    $query = sprintf("SELECT * FROM department WHERE Department_ID = '%s'", $_COOKIE["department"]);


    $result = $conn->query($query);

    while ($row = $result->fetch_assoc()) {

        $departmentObject = (object)[
            'departmentID' => (string)$row['Department_ID'],
            'addressID' => (string)$row['Address_ID'],
            'name' => (string)$row['Name'],
            'type' => (string)$row['Type'],
            'ownerID' => (string)$row['Owner_Department_ID']

        ];
        $departmentArray[] = $departmentObject;
    }

    $result->close();


    $conn->close();


    header("Content-Type: application/json;charset=utf-8");
    echo json_encode($departmentArray);
    //echo "[]";
} elseif (!empty($_POST['deps']) AND $_POST['deps'] != "") {
    $conn = connect();

    foreach ($_POST['deps'] as $value) {

        $query = sprintf("SELECT * FROM department WHERE Department_ID = '%s'", $value);

        $result = $conn->query($query);

        while ($row = $result->fetch_assoc()) {

            $departmentObject = (object)[
                'departmentID' => (string)$row['Department_ID'],
                'addressID' => (string)$row['Address_ID'],
                'name' => (string)$row['Name'],
                'type' => (string)$row['Type'],
                'ownerID' => (string)$row['Owner_Department_ID']

            ];
            $departmentArray[] = $departmentObject;
        }

        $result->close();

    }
    $conn->close();


    header("Content-Type: application/json;charset=utf-8");
    echo json_encode($departmentArray);

} else {
    header("Content-Type: application/json;charset=utf-8");
    echo "[]";
}
