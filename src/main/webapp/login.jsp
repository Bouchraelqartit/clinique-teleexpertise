<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
</head>
<body>

    <h2>Connexion</h2>

    <form action="${pageContext.request.contextPath}/login" method="post">

        <label>Username :</label>
        <input type="text" name="username" required>

        <br><br>

        <label>Mot de passe :</label>
        <input type="password" name="password" required>

        <br><br>

        <button type="submit">Se connecter</button>

    </form>

</body>
</html>
