<?php
/**
 * Created by IntelliJ IDEA.
 * User: ldy
 * Date: 16-06-2017
 * Time: 00:25
 */

include 'Connection.php';


$depList = array();

if ($_COOKIE["role"] != 5) {
    array_push($depList, ['departmentID' => $_COOKIE["department"]]);

}


if ($_COOKIE["role"] >= 3 && $_COOKIE["role"] != 5) {
    $conn = connect();


    $query = sprintf("SELECT Owner_Department_ID FROM department WHERE Department_ID = '%s'", $_COOKIE["department"]);
    $result = $conn->query($query);
    $row = $result->fetch_assoc();
    $owner_dep = $row['Owner_Department_ID'];
    $query = sprintf("SELECT Department_ID FROM department WHERE Owner_Department_ID = '%s' AND Department_ID != '%s' ", $owner_dep, $_COOKIE["department"]);

    $result = $conn->query($query);

    while ($row = $result->fetch_assoc()) {
        array_push($depList, ['departmentID' => $row['Department_ID']]);
    }
    $result->close();

}


if ($_COOKIE["role"] == 5) {
    $conn = connect();

    $query = sprintf("SELECT Department_ID FROM department");

    $result = $conn->query($query);

    while ($row = $result->fetch_assoc()) {
        array_push($depList, ['departmentID' => $row['Department_ID']]);
    }
    $result->close();

}

header("Content-Type: application/json;charset=utf-8");
echo json_encode($depList);