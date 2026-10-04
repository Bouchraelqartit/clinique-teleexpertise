
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
<h1>TEST NOUVELLE ATTENTE JSP</h1>
</head>

<body>

<h1>Patients en attente</h1>

<table border="1">

    <thead>
        <tr>
            <th>Nom</th>
            <th>Prénom</th>
            <th>NSS</th>
            <th>Heure d'arrivée</th>
            <th>Action</th>
        </tr>
    </thead>

    <tbody>

        <c:forEach var="patient" items="${patients}">

            <tr>

                <td>${patient.nom}</td>

                <td>${patient.prenom}</td>

                <td>${patient.numeroSecuriteSociale}</td>

                <td>${patient.heureArrivee}</td>

                <td>
<a href="${pageContext.request.contextPath}/generaliste/consultation?patientId=${patient.id}">
    Consulter
</a>

                </td>

            </tr>

        </c:forEach>

    </tbody>

</table>

</body>

</html>

