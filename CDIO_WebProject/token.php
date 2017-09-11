<?php
header("Content-Type: application/json;charset=utf-8");

include 'Connection.php';

$conn = connect();

$username = $_POST["user"];
$password = $_POST["pass"];


$query = sprintf("SELECT user.User_Name, Role, Name, Department_ID FROM user INNER JOIN employed ON user.User_Name = employed.User_Name 
                          WHERE user.User_Name = '%s' AND Password = '%s'", $username, $password);

$result = $conn->query($query);

if (mysqli_num_rows($result) > 0) {

    while ($row = $result->fetch_assoc()) {

        $usernameObject = (object)[

            'username' => (string)$row['User_Name'],
            'name' => (string)$row['Name'],
            'role' => (string)$row['Role'],
            'department' => (string)$row['Department_ID'],

        ];

        $usernameArray[] = $usernameObject;

    }

} else {

    $query = sprintf("SELECT user.User_Name, Role, Name FROM user WHERE user.User_Name = '%s' AND Password = '%s'", $username, $password);

    $result = $conn->query($query);

    while ($row = $result->fetch_assoc()) {

        $usernameObject = (object)[

            'username' => (string)$row['User_Name'],
            'name' => (string)$row['Name'],
            'role' => (string)$row['Role'],

        ];

        $usernameArray[] = $usernameObject;

    }

}

$conn->close();

echo json_encode($usernameArray);