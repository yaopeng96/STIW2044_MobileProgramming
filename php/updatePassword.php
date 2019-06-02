<?php
error_reporting(0);
include_once("dbconnect.php");
$email = $_POST['email'];
$username = $_POST['username'];
$password = $_POST['password'];
$phone = $_POST['phone'];

 $sql = "INSERT INTO user(email,username,password,phone,bname) VALUES ('$email','$username','$password','$phone')";

    if ($conn->query($sql) === TRUE){
        echo "success";
    }else {
        echo "The username had been taken";
    }
    

	
?>