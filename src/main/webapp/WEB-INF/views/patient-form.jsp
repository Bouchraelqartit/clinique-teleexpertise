<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Enregistrer un patient</title>
</head>

<body>

<h1>Enregistrer un patient</h1>

<% if (request.getAttribute("error") != null) { %>
    <p><%= request.getAttribute("error") %></p>
<% } %>

<% if ("true".equals(request.getParameter("success"))) { %>
    <p>Patient enregistré avec succès.</p>
<% } %>

<form method="post"
      action="${pageContext.request.contextPath}/infirmier/patients/nouveau">

    <input type="hidden"
           name="csrfToken"
           value="${sessionScope.csrfToken}">

    <label>Nom :</label>
    <input type="text" name="nom" required>

    <br><br>

    <label>Prénom :</label>
    <input type="text" name="prenom" required>

    <br><br>

    <label>Date de naissance :</label>
    <input type="date" name="dateNaissance" required>

    <br><br>

    <label>NSS :</label>
    <input type="text" name="nss" required>

    <br><br>

    <label>Tension :</label>
    <input type="text" name="tension" placeholder="120/80" required>

    <br><br>

    <label>Fréquence cardiaque :</label>
    <input type="number" name="frequenceCardiaque" required>

    <br><br>

    <label>Température :</label>
    <input type="number" step="0.1" name="temperature" required>

    <br><br>

    <label>Fréquence respiratoire :</label>
    <input type="number" name="frequenceRespiratoire" required>

    <br><br>

    <button type="submit">Enregistrer</button>

</form>

</body>
</html>