<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>authorization Page</title>
</head>
<body>
<h2>login</h2>
<form method="post" action="/login">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    username:
    <input type="text" name="username" required>
    <br>
    password:
    <input type="password" name="password" required>
    <br>
    <input type="submit" value="login">
</form>
<p>press >> <a href="/register">here</a> << if you haven't registered yet <3</p>
</body>
</html>