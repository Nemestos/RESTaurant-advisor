# RESTaurant API

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
