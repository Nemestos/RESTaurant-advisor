FROM composer:latest as vendor
WORKDIR /app
COPY ./rest-api/composer.json /app
COPY ./rest-api/composer.lock* /app

RUN composer install  \
    --ignore-platform-reqs \
    --no-dev \
    --no-ansi\
    --no-interaction \
    --no-scripts


FROM php:8.0.0-fpm as builder
# Install dependencies
RUN apt-get update && apt-get install -y --no-install-recommends --no-install-suggests   \
    libzip-dev \
    openssl \
    libssl-dev \
    libcurl4-openssl-dev \
    zsh\
    libpq-dev\
  && docker-php-ext-install \
    zip \
    pdo \
    pdo_pgsql\
  && rm -rf /tmp/* /var/lib/apt/lists/*



# Copy existing application directory contents
WORKDIR /var/www

COPY --chown=www-data:www-data ./rest-api /var/www
COPY --from=vendor /usr/bin/composer /usr/bin/composer

#RUN php artisan mongodb-passport:install
RUN composer update
RUN php artisan key:generate
RUN php artisan config:cache
RUN php artisan cache:clear
RUN chmod -R 777 storage/
RUN composer dump-autoload
CMD ["php","artisan","serve"]
EXPOSE 8080
