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
  && docker-php-ext-install \
    zip \
    exif \
    pcntl \
  && pecl install mongodb \
      &&  echo "extension=mongodb.so" > $PHP_INI_DIR/conf.d/mongo.ini \
  && rm -rf /tmp/* /var/lib/apt/lists/*



# Copy existing application directory contents
WORKDIR /var/www

COPY  ./rest-api /var/www
COPY --from=vendor /usr/bin/composer /usr/bin/composer
RUN composer update --no-scripts
RUN php artisan vendor:publish --provider="Tymon\JWTAuth\Providers\LaravelServiceProvider"
RUN php artisan jwt:secret
RUN php artisan key:generate
RUN chown -R www-data:www-data /var/www/storage




EXPOSE 9000
