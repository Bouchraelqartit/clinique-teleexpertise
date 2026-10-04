<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Consultation</title>
</head>

<body>

<h1>Consultation du patient</h1>

<c:if test="${not empty error}">
    <p style="color:red;">
        ${error}
    </p>
</c:if>

<h2>Informations du patient</h2>

<table border="1">

    <tr>
        <th>Nom</th>
        <td>${patient.nom}</td>
    </tr>

    <tr>
        <th>Prénom</th>
        <td>${patient.prenom}</td>
    </tr>

    <tr>
        <th>Date de naissance</th>
        <td>${patient.dateNaissance}</td>
    </tr>

    <tr>
        <th>NSS</th>
        <td>${patient.numeroSecuriteSociale}</td>
    </tr>

    <tr>
        <th>Tension</th>
        <td>${patient.tensionArterielle}</td>
    </tr>

    <tr>
        <th>Fréquence cardiaque</th>
        <td>${patient.frequenceCardiaque}</td>
    </tr>

    <tr>
        <th>Température</th>
        <td>${patient.temperature}</td>
    </tr>

    <tr>
        <th>Fréquence respiratoire</th>
        <td>${patient.frequenceRespiratoire}</td>
    </tr>

</table>

<h2>Formulaire de consultation</h2>

<form method="post"
      action="${pageContext.request.contextPath}/generaliste/consultation">

    <input type="hidden"
           name="csrfToken"
           value="${csrfToken}">

    <input type="hidden"
           name="patientId"
           value="${patient.id}">

    <label for="motif">Motif :</label>
    <br>
    <textarea id="motif"
              name="motif"
              required></textarea>

    <br><br>

    <label for="observations">Observations :</label>
    <br>
    <textarea id="observations"
              name="observations"></textarea>

    <br><br>

    <label for="diagnostic">Diagnostic :</label>
    <br>
    <textarea id="diagnostic"
              name="diagnostic"
              required></textarea>

    <br><br>

    <label for="traitement">Traitement :</label>
    <br>
    <textarea id="traitement"
              name="traitement"></textarea>

    <br><br>

    <button type="submit">
        Clôturer
    </button>

</form>

</body>

</html>
