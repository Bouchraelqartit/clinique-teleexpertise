<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Patients en attente | Clinique</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/app.css">
</head>
<body class="app-body">
<%@ include file="fragments/app-header.jspf" %>

<main class="page-wrap">
    <section class="page-heading">
        <div>
            <span class="page-overline">Espace généraliste</span>
            <h1>À prendre en charge</h1>
            <p>Les patients attendent votre évaluation médicale.</p>
        </div>
        <span class="status-pill">File d'attente</span>
    </section>

    <section class="content-card" aria-labelledby="queue-title">
        <div class="card-toolbar">
            <h2 id="queue-title">Patients en attente</h2>
            <span class="record-count">Ouvrez un dossier pour commencer la consultation</span>
        </div>

        <c:choose>
            <c:when test="${not empty patients}">
                <div class="table-scroll">
                    <table class="data-table">
                        <thead>
                        <tr>
                            <th>Patient</th>
                            <th>NSS</th>
                            <th>Heure d'arrivée</th>
                            <th>Statut</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="patient" items="${patients}">
                            <tr>
                                <td><div class="person-cell"><span class="person-initial">＋</span><span><c:out value="${patient.prenom}" /> <c:out value="${patient.nom}" /></span></div></td>
                                <td><c:out value="${patient.numeroSecuriteSociale}" /></td>
                                <td><c:out value="${patient.heureArrivee}" /></td>
                                <td><span class="status-pill">En attente</span></td>
                                <td><a class="table-action" href="${pageContext.request.contextPath}/generaliste/consultation?patientId=${patient.id}">Ouvrir le dossier <span aria-hidden="true">→</span></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div class="empty-state">
                    <span class="empty-icon" aria-hidden="true">✓</span>
                    <strong>La file est à jour</strong>
                    <span>Aucun patient n'attend une consultation pour le moment.</span>
                </div>
            </c:otherwise>
        </c:choose>
    </section>
</main>
<footer class="app-footer">Clinique · Des soins coordonnés, à chaque étape.</footer>
</body>
</html>
