<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="theme-color" content="#a83b1d">
    <title>Connexion | Clinique</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/app.css">
</head>
<body class="login-shell">
<div class="login-page">
    <header class="topbar">
        <a class="brand" href="${pageContext.request.contextPath}/login.jsp" aria-label="Clinique, accueil">
            <span class="brand-mark" aria-hidden="true">+</span>
            <span>Clinique</span>
        </a>
        <nav class="top-links" aria-label="Navigation principale">
            <a href="#services">Services</a>
            <a href="#equipe">Notre équipe</a>
            <a href="#acces">Téléexpertise</a>
            <a class="outline-link" href="#acces">Accès professionnel</a>
        </nav>
    </header>

    <main class="login-main">
        <div class="hero-art" aria-hidden="true">
            <div class="pulse-card">
                <span class="pulse-icon">+</span>
                <span><strong>Suivi coordonné</strong><small>Votre santé, en équipe</small></span>
            </div>
        </div>

        <section class="hero-copy" id="services">
            <span class="eyebrow">Soins &amp; téléexpertise</span>
            <h1>La santé,<br>plus proche.<br><span>Ensemble.</span></h1>
            <p>Une équipe médicale connectée pour accompagner chaque patient avec attention, clarté et confiance.</p>
        </section>

        <section class="login-card" id="acces" aria-labelledby="login-title">
            <p class="card-kicker">Espace professionnel</p>
            <h2 id="login-title">Bon retour</h2>
            <p class="card-intro">Connectez-vous pour accéder à votre espace de travail.</p>

            <% if ("1".equals(request.getParameter("error"))) { %>
                <div class="alert" role="alert">Identifiant ou mot de passe incorrect.</div>
            <% } %>
            <% if (request.getAttribute("loginError") != null) { %>
                <div class="alert" role="alert"><%= request.getAttribute("loginError") %></div>
            <% } %>

            <form action="${pageContext.request.contextPath}/login" method="post">
                <input type="hidden" name="csrfToken" value="${requestScope.csrfToken}">

                <div class="form-field">
                    <label for="username">Identifiant</label>
                    <input id="username" type="text" name="username" autocomplete="username" placeholder="Votre identifiant" required autofocus>
                </div>

                <div class="form-field">
                    <label for="password">Mot de passe</label>
                    <input id="password" type="password" name="password" autocomplete="current-password" placeholder="Votre mot de passe" required>
                </div>

                <button class="primary-button" type="submit">Se connecter <span aria-hidden="true">→</span></button>
                <p class="form-foot">Accès réservé aux membres de l’équipe médicale.</p>
            </form>
        </section>
    </main>

    <footer class="login-footer" id="equipe">
        <span>Clinique · Téléexpertise</span>
        <span>Des soins coordonnés, à chaque étape.</span>
        <span>© 2026</span>
    </footer>
</div>
</body>
</html>
