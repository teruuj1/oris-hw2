<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>registration Page</title>
</head>
<body>
<h2>registration</h2>
<form method="post" action="/register">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    what's your name?
    <input type="text" name="name" required>
    <br>
    <p>now, please, create your username and password:</p>
    username:
    <input type="text" name="username" required>
    <br>
    password:
    <input type="password" name="password" required>
    <br>
    <input type="submit" value="register">
</form>
</body>
</html>