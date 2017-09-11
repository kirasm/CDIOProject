<?php
/**
 * Created by IntelliJ IDEA.
 * User: ldylab
 * Date: 13-06-2017
 * Time: 13:18
 */
include 'Connection.php';


//echo "test";

if (!empty($_POST['users'])) {
    $conn = connect();

    $query = sprintf('SELECT Department_ID FROM employed WHERE User_Name = "%s";', $_COOKIE['username']);
    $result = $conn->query($query);
    while ($row = $result->fetch_assoc()) {

        $departmentID = $row['Department_ID'];
    };

    $users = $_POST['users'];
    foreach ($users as $user) {

        $query = sprintf('INSERT INTO employed (User_Name,Department_ID) VALUES ("%s","%s");', $user, $departmentID);
        $result = $conn->query($query);

    }
    $conn->close();

    //header("Content-Type: application/json;charset=utf-8");
    //echo json_encode(true);
}


if (!empty($_GET['q'])) {
    $conn = connect();

    $personArray = array();

    $q = mysqli_real_escape_string($conn, $_GET['q']);

    $q = $q . "%";

    $query = sprintf('SELECT
  t1.User_Name,
  t1.`Name`
FROM
  `user` AS t1
  LEFT JOIN employed AS t2
    ON t1.User_Name = t2.User_Name
WHERE
  t2.User_Name IS NULL AND
  t1.User_Name LIKE "%s";', $q);


    $result = $conn->query($query);


    while ($row = $result->fetch_assoc()) {

        $username = $row['User_Name'];
        $name = $row["Name"];

        $personObject = (object)[
            'username' => (string)$row['User_Name'],
            'name' => (string)$row['Name']
        ];

        array_push($personArray, $personObject);
    }
    $conn->close();

    header("Content-Type: application/json;charset=utf-8");
    echo json_encode($personArray);
}



