FROM composer:latest as vendor
WORKDIR /var/www
COPY rest-api/composer.json composer.json
COPY rest-api/composer.lock composer.lock
RUN composer install \
    --no-interaction \
    --no-plugins \
    --no-scripts \
    --no-dev \
    --prefer-dist \
    --ignore-platform-reqs
COPY ./rest-api .

FROM php:8.0.0-fpm as builder

# Install dependencies
RUN apt-get update && apt-get install -y --no-install-recommends --no-install-suggests   \
    libzip-dev \
    openssl \
    libssl-dev \
    libcurl4-openssl-dev \
  && docker-php-ext-install \
    zip \
    exif \
    pcntl \
  && pecl install mongodb \
      &&  echo "extension=mongodb.so" > $PHP_INI_DIR/conf.d/mongo.ini \
  && rm -rf /tmp/* /var/lib/apt/lists/*


# Add user for laravel application
RUN groupadd -g 1000 www
RUN useradd -u 1000 -ms /bin/bash -g www www

# Copy existing application directory contents

COPY --chown=www:www ./rest-api /var/www
COPY --from=composer /usr/bin/composer /usr/bin/composer
COPY --from=vendor /var/www/vendor/ ./rest-api/vendor/
COPY ./rest-api .
RUN php artisan vendor:publish --provider="Tymon\JWTAuth\Providers\LaravelServiceProvider"
RUN php artisan jwt:secret

# Change current user to www
USER www

EXPOSE 9000
