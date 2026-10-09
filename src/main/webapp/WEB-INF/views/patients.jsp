<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Patients du jour | Clinique</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/app.css">
</head>
<body class="app-body">
<%@ include file="fragments/app-header.jspf" %>

<main class="page-wrap">
    <section class="page-heading">
        <div>
            <span class="page-overline">Espace infirmier</span>
            <h1>Patients du jour</h1>
            <p>Retrouvez les patients accueillis et leurs premières constantes.</p>
        </div>
        <a class="primary-button compact" href="${pageContext.request.contextPath}/infirmier/patients/nouveau">
            <span aria-hidden="true">+</span> Enregistrer un patient
        </a>
    </section>

    <section class="content-card" aria-labelledby="patients-title">
        <div class="card-toolbar">
            <h2 id="patients-title">Accueil et constantes</h2>
            <span class="record-count">Patients enregistrés aujourd'hui</span>
        </div>

        <c:choose>
            <c:when test="${not empty patients}">
                <div class="table-scroll">
                    <table class="data-table">
                        <thead>
                        <tr>
                            <th>Patient</th>
                            <th>NSS</th>
                            <th>Arrivée</th>
                            <th>Tension</th>
                            <th>Fréquence cardiaque</th>
                            <th>Température</th>
                            <th>Fréquence respiratoire</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="patient" items="${patients}">
                            <tr>
                                <td><div class="person-cell"><span class="person-initial">＋</span><span><c:out value="${patient.prenom}" /> <c:out value="${patient.nom}" /></span></div></td>
                                <td><c:out value="${patient.numeroSecuriteSociale}" /></td>
                                <td><c:out value="${patient.heureArrivee}" /></td>
                                <td><span class="status-pill"><c:out value="${patient.tensionArterielle}" /></span></td>
                                <td><c:out value="${patient.frequenceCardiaque}" /> bpm</td>
                                <td><c:out value="${patient.temperature}" /> °C</td>
                                <td><c:out value="${patient.frequenceRespiratoire}" /> /min</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div class="empty-state">
                    <span class="empty-icon" aria-hidden="true">＋</span>
                    <strong>Aucun patient enregistré aujourd'hui</strong>
                    <span>Les nouveaux patients apparaîtront ici après leur enregistrement.</span>
                </div>
            </c:otherwise>
        </c:choose>
    </section>
</main>
<footer class="app-footer">Clinique · Des soins coordonnés, à chaque étape.</footer>
</body>
</html>
