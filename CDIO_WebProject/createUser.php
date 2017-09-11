<?php

include 'Connection.php';

$conn = connect();


if (!empty($_POST['username']) && !empty($_POST['name']) && !empty($_POST['email']) && !empty($_POST['password']) && !empty($_POST['phone'])) {


    $username = mysqli_real_escape_string($conn, $_POST['username']);
    $name = mysqli_real_escape_string($conn, $_POST['name']);
    $email = mysqli_real_escape_string($conn, $_POST['email']);
    $password = mysqli_real_escape_string($conn, $_POST['password']);
    $phone = mysqli_real_escape_string($conn, $_POST['phone']);
    $role = "0";

    $query = sprintf("INSERT INTO user (User_Name, Name, Email, Phone, Role, Password) 
                                          VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
        $username, $name, $email, $phone, $role, $password);

    $conn->query($query);
    $conn->close();
    header('Location: index.php');
}

?>

