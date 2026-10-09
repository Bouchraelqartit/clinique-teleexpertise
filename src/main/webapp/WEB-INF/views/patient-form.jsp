<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Nouveau patient | Clinique</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/app.css">
</head>
<body class="app-body">
<%@ include file="fragments/app-header.jspf" %>

<main class="page-wrap">
    <section class="page-heading">
        <div>
            <span class="page-overline">Accueil infirmier</span>
            <h1>Nouveau patient</h1>
            <p>Renseignez les informations du patient et ses constantes.</p>
        </div>
        <a class="secondary-button" href="${pageContext.request.contextPath}/infirmier/patients">← Retour aux patients</a>
    </section>

    <c:if test="${not empty param.success}">
        <div class="alert success" role="status">Patient enregistré avec succès.</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert" role="alert"><c:out value="${error}" /></div>
    </c:if>

    <div class="form-layout">
        <section class="content-card form-card" aria-labelledby="patient-form-title">
            <h2 id="patient-form-title">Informations médicales</h2>
            <p class="subtext">Les champs marqués sont nécessaires à l'enregistrement.</p>

            <form method="post" action="${pageContext.request.contextPath}/infirmier/patients/nouveau">
                <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">

                <div class="form-grid">
                    <div class="form-field">
                        <label for="prenom">Prénom</label>
                        <input id="prenom" type="text" name="prenom" autocomplete="given-name" placeholder="Ex. Lina" required>
                    </div>
                    <div class="form-field">
                        <label for="nom">Nom</label>
                        <input id="nom" type="text" name="nom" autocomplete="family-name" placeholder="Ex. Amrani" required>
                    </div>
                    <div class="form-field">
                        <label for="dateNaissance">Date de naissance</label>
                        <input id="dateNaissance" type="date" name="dateNaissance" required>
                    </div>
                    <div class="form-field">
                        <label for="nss">Numéro de sécurité sociale</label>
                        <input id="nss" type="text" name="nss" placeholder="Numéro NSS" required>
                    </div>
                    <div class="form-field">
                        <label for="tension">Tension artérielle</label>
                        <input id="tension" type="text" name="tension" placeholder="120/80" required>
                    </div>
                    <div class="form-field">
                        <label for="frequenceCardiaque">Fréquence cardiaque</label>
                        <input id="frequenceCardiaque" type="number" name="frequenceCardiaque" min="1" placeholder="bpm" required>
                    </div>
                    <div class="form-field">
                        <label for="temperature">Température</label>
                        <input id="temperature" type="number" step="0.1" name="temperature" placeholder="37.0 °C" required>
                    </div>
                    <div class="form-field">
                        <label for="frequenceRespiratoire">Fréquence respiratoire</label>
                        <input id="frequenceRespiratoire" type="number" name="frequenceRespiratoire" min="1" placeholder="par minute" required>
                    </div>
                </div>

                <div class="form-actions">
                    <a class="secondary-button" href="${pageContext.request.contextPath}/infirmier/patients">Annuler</a>
                    <button class="primary-button compact" type="submit">Enregistrer le patient <span aria-hidden="true">→</span></button>
                </div>
            </form>
        </section>

        <aside class="side-note">
            <span class="note-mark" aria-hidden="true">＋</span>
            <h3>Un accueil attentif</h3>
            <p>Vérifiez les informations d'identité et les constantes avant d'envoyer le dossier à l'équipe médicale.</p>
        </aside>
    </div>
</main>
<footer class="app-footer">Clinique · Les informations de santé sont confidentielles.</footer>
</body>
</html>
