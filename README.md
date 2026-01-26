# Grand-Plateau

## Aperçu du projet
> Cette application permet la gestion et le suivi de statistiques de cyclisme mondiales
> Elle a été conçue pour répondre au besoin d'un utilisateur unique (mon père).
Application de gestion et suivi de données cyclistes (Angular/Spring Boot/HSQLDB)

---

## Tech Stack
> - **Language** : Java 21
> - **Framework** : Spring Boot 4.0.1 (Web, Data JPA, Validation)
> - **Database** : HSQLCB
> - **Tools** : Maven 3.9.6, MapStruct 1.6.3

---

## Architecture

### Back
Ce projet implémente une Architecture Hexagonale (Ports and Adapters) pour découpler la logique métier des requis techniques.
Le code est séparé selon 2 grands domaines : administration et compétition.
Chacun de ces domaines possède son propre hexagone domain avec ses ports et son infrastructure pour une modularisation plus flexible.

#### Exemple du domaine Administration:
- **Application**
	- **Port**
		- **In** : Interface de services
		- **Out** : Interface de repositories
- **Domain** : Logique Java pure, incluant les modèles (Record) et les implémentations de services
- **Infrastructure**
	- **In.web** : Controlleurs REST, DTOs et mappeurs Web (DTO <-> Domain)
	- **Out.persistence** : Adapteurs SQL, Spring Data JPA repositories, entités et mappeurs de persistence. (Entity <-> Domain)

### Front
Côté front, le code est organisé selon les modules : core (intercepteurs si besoin, constantes d'environnement), features et shared.
Le module "features" regroupe l'ensemble des features isolées.
Le module "shared" contient toutes les données communes de l'application : composants réutilisables, données de test, modèles communs.

---

## Démarrer l'application


## Choix de design
TDD (Test Driven Development) : L'application a été codée en utilisant une approche TDD, s'assurant que chaque règle métier soit 
couverte par un test et que l'architecture reste claire et refactorable. (SOUS RESERVE)

Record Java : Utilisés pour les modèles et DTOs l'immutabilité.

MapStruct : choisi pour le mapping performant et type-safe entre les couches applicatives.

---