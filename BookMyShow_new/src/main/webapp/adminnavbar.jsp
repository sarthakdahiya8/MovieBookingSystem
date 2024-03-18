<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Navbar</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="AdminHomePage.jsp">Hi admin!!</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="AdminHomePage.jsp">Home <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="adminseemovie.jsp">See Movies</a>
      <a class="nav-item nav-link" href="adminseatsbooked.jsp">Seats booked</a>
      <a class="nav-item nav-link " href="logout.jsp">Logout</a>
    </div>
  </div>
</nav>

</body>
</html>
