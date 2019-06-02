<?php
error_reporting(0);
include_once("dbconnect.php");
$email = $_POST['email'];
$username = $_POST['username'];
$phone = $_POST['phone'];

$sql = "UPDATE user SET username ='$username' ,phone = '$phone' WHERE email = '$email'";
if ($conn->query($sql) === TRUE)
{
    echo "success";
}
else
    {
    echo "failed";
}

$conn->close();
?>