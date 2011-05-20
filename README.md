Projet ECOM
===========

## DESCRIPTION

Le projet eCOM consiste à concevoir et développer une application de commerce électronique.
Une motivation principale du projet eCOM est qu'il intègre dans sa mise en œuvre différentes thématiques étudiées dans les formations en informatique : en particulier, interfaces homme-machine, applications et architectures réparties, base de données. Les aspects relatifs au génie logiciel sont également présents puisque le projet eCOM comprend la conception et la réalisation d'un produit logiciel qui satisfait certains critères de qualité (documentation, respect des normes, etc).

L'intégration de différentes thématiques présente les atouts suivants :

* Valorisation des enseignements acquis dans les différentes thématiques au profit d'un projet important et réaliste.
* Appréhension des (inter)dépendances entre les thématiques.
* Communications entre les différents réalisateurs du projet.

En outre, une deuxième motivation forte du projet eCOM est qu'il intègre des technologies et des standards récents et largement utilisés dans le domaine des applications Internet. Ce projet permet plus précisément de se placer en tant qu'utilisateurs de la technologie JavaEE (Java 2 Enterprise Edition) destinée à la réalisation de serveurs d'information ou de serveurs de commerce électronique à base de composants distribués, transactionnels et persistants.  

Les réalisateurs sont confrontés par ce biais à la manipulation de mécanismes et de fonctions générales d'un système distribués : gestion de la désignation de composants distribués, configuration d'une application distribuée, association de propriétés non fonctionnelles aux composants (persistance, transactions), gestion des images persistantes des composants (liaisons avec une base de données).

Le projet se décompose en différentes parties intervenant dans la programmation et dans le déploiement d'une application JavaEE, classiquement qualifiée de n-tiers :

* une partie navigateur (chez le client)
* une partie serveur Web avec la programmation et le déploiement de servlets,
* une partie logique applicative avec la programmation et le déploiement des EJB ("cœur du serveur JavaEE")
* une partie base de données


## Fonctionnalités du Shell (Client et Admin)

* `Help` liste les commandes disponibles avec leur brève description. 
* `output XML|TEXT` positionne le format de sortie. Par défaut : TEXT. 
* `batch -stopOnError cmds.txt` Non finie. 
* `language en|fr|es|it` positionne la langue utilisée pour les sorties 
* `begin` démarre une transaction
* `commit` valide la transaction courante 
* `rollback|abort` abandonne la transaction courante 
* `account` affichage d’account(s) 
* `store` liste tous les magasins 
* `product` liste tous les produits 
* `product --store 1001` liste les produits du magasin 1001 
* `product --name "Machine à laver" liste les produits dont le nom est celui passé
en paramêtre 
* `product --like Machin%` liste les produits dont le nom est semblable 
* `product –-save <name> <price>` enregistre un produit 
* product -price 1000.00` liste les produits de prix inférieur 
* product -price 100.00 1000.00` liste les produits dans un intervalle de prix 
* `cart` affiche le contenu du caddie (et le prix total) 
* `cart --add 101` ajoute le produit 101 dans le caddie 
* `cart --add 102` ajoute le produit 102 dans le caddie 
* `cart --add 103` ajoute le produit 103 dans le caddie 
* `cart --remove 102` ajoute le produit 102 dans le caddie 
* `cart --buy FR7630003003930003728602468` achète le contenu du caddie avec le
compte FR7630003003930003728602468 
* `quit` quitte le client ecom (et abandonne le ou les transactions non validées)

## Technologies utilisées

* MAVEN : pour gérer les dépendances et le déploiement
* SPRING MVC : pour l’interface web client
* JPA/HIBERNATE : gestion de la persistance
* Sencha : pour l’interface web d’administration

## Modules du projet

### ECOM-lib-shell
API que nous avons développé nous-mêmes pour l’aide à la création de commandes dans les applications « ECOM- appclient ». Les commandes reprennent la composition des commandes

### ECOM-ejb
Gestion des EJB
### ECOM-arq
Les test des beans sont fait par l’intermédiaire de notre projet « Arquillian », afin de pouvoir réaliser des tests dans le conteneur d’EJB une fois que la partie logique EJB de notre application est déployée.
### ECOM-APPCLIENT-CUSTOMER
Client lourd dédié pour les clients. Cette application repose sur l’application « ECOM-lib-shell » qui lui sert de support pour la création de commandes.
### ECOM-APPCLIENT-ADMIN
Client lourd dédié pour l’administration. Cette application repose sur l’application « ECOM-lib-shell » qui lui sert de support pour la création de commandes. Elle reprend également certaines commandes déjà existantes dans l’application « ECOM-appclient- customer ». Afin de pouvoir faire du « Re-use », nous avons adapté l’architecture des deux clients lourds et le mode d’appel aux beans sessions stateful, de façon à ce que le client lourd dédié aux clients puisse fournir des commandes génériques aux deux.

### ECOM-web
L’application web du projet ECOM fait usage du framework Spring MVC. L’utilisation de ce framework permet de faciliter la gestion des dépendances via un conteneur d’injection léger. Un des grands intérêts de Spring est l’existence d’une servlet appelée DispatcherServlet qui traite toutes les requêtes pour les rediriger vers le contrôleur qui a été déclaré capable d’effectuer un traitement pour cette requête. Pour être capable d’utiliser un EJB de type session, il faut l’avoir déclaré au préalable dans le fichier applicationContext.xml puis déclarer un objet en utilisant l’annotation `@EJB`. Le framework est piloté par les annotations Java qui permettent de définir les contrôleurs, et le mapping entre les URI et les ressources.
### ECOM-web-admin
Bien qu’incomplète, l’interface d’administration est entièrement réalisée avec Sencha 4 (anciennement ExtJS) et offre une API Restful. La gestion des produits et catégories est terminée et une base de CRUD (Create, Read, Update, Delete) permet d’implémenter facilement la gestion des clients, magasins, etc.
### ECOM-ear
Le projet ECOM-ear est une archive qui regroupe les EJB, l’application web et l’interface d’administration en trois projets séparés. Ces projets sont déployés tous ensemble sur le serveur GlassFish.