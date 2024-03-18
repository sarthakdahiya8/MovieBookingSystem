<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<form action='registercon' method="post">


<div class="container">
  <div >
    <label >Name</label>
    <input type="text" class="form-control" id="inputAddress" placeholder="Enter Your Name" name= "username">
  </div>
  <br>
    <div>
    <label >Email</label>
    <input type="email" class="form-control" id="inputEmail" name="useremail">
  </div>
   <br>
  <div >
    <label >Password</label>
    <input type="password" class="form-control" id="inputPassword" name="userpass">
  </div>
   <br>
  <div >
    <label>Mobile</label>
    <input type="number" class="form-control" id="inputmobile" name="usermobile">
  </div>
   <br>
  <div >
  <label>Age</label>
    <input type="number" class="form-control" id="inputage" name="userage">
  </div>
   <br>
  <div >
  <label>Gender</label>
    <input type="text" class="form-control" id="inputgender" name="usergender">
  </div>
  <br>

<div>
  <button type="submit">Register</button>
</div>
</div>


</form></center>

</body>
</html>