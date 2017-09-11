<?php

// To edit the user, we copy the info on the user from the DB and make a new user
// with the edits in it.

include 'Connection.php';

$conn = connect();

$usernameEdit = $_POST["username"];
$nameEdit = $_POST["name"];
$emailEdit = $_POST["email"];
$passwordEdit = $_POST["password"];
$phoneEdit = $_POST["phone"];
$roleEdit = "0";

$name = "";
$email = "";
$password = "";
$phone = "";
$role = "";


$query = sprintf("SELECT * FROM user WHERE User_Name = '%s'", $usernameEdit);

$result = $conn->query($query);

$conn->close();


if ($result->num_rows > 0) {
    // output data of each row
    while ($row = $result->fetch_assoc()) {

        $username = $row['User_Name'];
        $name = $row['Name'];
        $email = $row['Email'];
        $password = $row['Password'];
        $phone = $row['Phone'];
        $role = $row['Role'];
    }

}

echo "OLd Phone" . $phone;
echo "NEw PHone" . $phoneEdit;

$conn = connect();

// Name has been changed
if ($name != $nameEdit) {

    $query = sprintf("UPDATE user SET Name = '%s' WHERE User_Name = '%s'"
        , $nameEdit, $usernameEdit);
    $conn->query($query);
    echo "Editing Name";
}

// Email has been changed
if ($email != $emailEdit) {

    $query = sprintf("UPDATE user SET Email = '%s' WHERE User_Name = '%s'"
        , $emailEdit, $usernameEdit);
    $conn->query($query);
    echo "Editing Email";
}

// Phone number has been changed
if ($phone != $phoneEdit) {

    $query = sprintf("UPDATE user SET Phone = '%s' WHERE User_Name = '%s'"
        , $phoneEdit, $usernameEdit);
    $conn->query($query);
    echo "Editing Phone";
}

// Password has been changed
if ($password != $passwordEdit) {

    $query = sprintf("UPDATE user SET Password = '%s' WHERE User_Name = '%s'"
        , $passwordEdit, $usernameEdit);
    $conn->query($query);
    echo "Editing Phone";
}

$conn->close();

?>