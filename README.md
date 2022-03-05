<h1 align="center">Four Metal</h1> 
<p align="center">
  <a href="#">
    <img src="Logo/avec%20fonds/logo%20renard.png" alt="Computer" width=128 height=128>
  </a>
</p>

# Infos techniques

L'API est host sur le port 81(pour éviter les conflits)

## Lancer le projet

```shell
cd docker
docker-compose up --build --force-recreate
```

## Lancer les tests

```shell
cd docker
docker-compose up --build --force-recreate
```

```shell
docker-compose exec php zsh
php artisan route:cache
php artisan test
```

## Features
- possibilité de protéger des routes avec la librairie Sanctum(avec gestion des abilités)
- test fonctionnels mis en place(qui portent sur toute les routes de l'api dans la plupart des users cases)
- Operations CRUD sur les differentes ressources attendus
- Conteneurisé par Docker et lançable facilement
- Utilisation d'un Design Pattern Adapter pour convertir la réponse de l'API de generations d'URLS d'image en quelque chose de comprehensible et ainsi plusieurs API de différentes sources peuvent être utilisés sans changer l'interface de Base 
- Seeders fonctionnels avec des factorys cohérentes
- Utilisation des JsonResources pour permettre de convertir un modèle Laravel en quelque chose d'utilisable par quelqu'un qui utilise l'API(exemple : obfusquer le password hashé)

## Valeur ajoutée
- ***Controllers/AuthController***(gestion de tout ce qui est en rapport avec l'authentification, la generation de tokens et la verificaiton des droits)
- ***Controllers/MenuController***(CRUD basique pour gerer les menus)
- ***Controllers/RestaurantController***(CRUD basique pour gerer les restaurants)

- ***Controllers/UsersController***(permet de retourner tout les utilisateurs)

- ***Middleware/CheckMenuRestaurant***(très utile pour verifier pour chaque route, si existence, les ids passé, et ainsi prevenir avant de guérir)

- ***Resources/*\***(toute les resources json retournées par l'API comprenant entre autre les choses utiles par l'utilisateur et le client)