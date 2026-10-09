<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Consultation | Clinique</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/app.css">
</head>
<body class="app-body">
<%@ include file="fragments/app-header.jspf" %>

<main class="page-wrap">
    <section class="page-heading">
        <div>
            <span class="page-overline">Espace généraliste</span>
            <h1>Dossier de consultation</h1>
            <p>Consultez les informations du patient et complétez son dossier.</p>
        </div>
        <a class="secondary-button" href="${pageContext.request.contextPath}/generaliste/patients">← Retour à la file</a>
    </section>

    <c:if test="${not empty error}">
        <div class="alert" role="alert"><c:out value="${error}" /></div>
    </c:if>

    <section class="patient-summary" aria-label="Résumé du patient">
        <div class="summary-item"><span>Patient</span><strong><c:out value="${patient.prenom}" /> <c:out value="${patient.nom}" /></strong></div>
        <div class="summary-item"><span>Date de naissance</span><strong><c:out value="${patient.dateNaissance}" /></strong></div>
        <div class="summary-item"><span>Tension</span><strong><c:out value="${patient.tensionArterielle}" /></strong></div>
        <div class="summary-item"><span>Température</span><strong><c:out value="${patient.temperature}" /> °C</strong></div>
    </section>

    <div class="form-layout">
        <section class="content-card form-card" aria-labelledby="consultation-title">
            <h2 id="consultation-title">Compte rendu médical</h2>
            <p class="subtext">Complétez les éléments de la consultation avant de la clôturer.</p>

            <form method="post" action="${pageContext.request.contextPath}/generaliste/consultation">
                <input type="hidden" name="csrfToken" value="${csrfToken}">
                <input type="hidden" name="patientId" value="${patient.id}">

                <div class="form-field">
                    <label for="motif">Motif de consultation</label>
                    <textarea id="motif" name="motif" placeholder="Décrivez le motif de la consultation" required></textarea>
                </div>
                <div class="form-field">
                    <label for="observations">Observations cliniques</label>
                    <textarea id="observations" name="observations" placeholder="Observations complémentaires"></textarea>
                </div>
                <div class="form-field">
                    <label for="diagnostic">Diagnostic</label>
                    <textarea id="diagnostic" name="diagnostic" placeholder="Diagnostic retenu" required></textarea>
                </div>
                <div class="form-field">
                    <label for="traitement">Traitement et recommandations</label>
                    <textarea id="traitement" name="traitement" placeholder="Traitement ou recommandations"></textarea>
                </div>

                <div class="form-actions">
                    <a class="secondary-button" href="${pageContext.request.contextPath}/generaliste/patients">Annuler</a>
                    <button class="primary-button compact" type="submit">Clôturer la consultation <span aria-hidden="true">→</span></button>
                </div>
            </form>
        </section>

        <aside class="side-note">
            <span class="note-mark" aria-hidden="true">✚</span>
            <h3>Dossier patient</h3>
            <p><strong>NSS :</strong> <c:out value="${patient.numeroSecuriteSociale}" /></p>
            <p><strong>Fréquence cardiaque :</strong> <c:out value="${patient.frequenceCardiaque}" /> bpm</p>
            <p><strong>Fréquence respiratoire :</strong> <c:out value="${patient.frequenceRespiratoire}" /> /min</p>
        </aside>
    </div>
</main>
<footer class="app-footer">Clinique · Les informations de santé sont confidentielles.</footer>
</body>
</html>
