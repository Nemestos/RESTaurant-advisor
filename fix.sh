#!/bin/bash
docker-compose exec php php artisan key:generate
docker-compose exec php php artisan optimize