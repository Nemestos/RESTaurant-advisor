FROM php:8.0.0-fpm
ENV TZ=Europe/Paris
COPY rest-api/composer.lock rest-api/composer.json /var/www/
WORKDIR /var/www
RUN chmod 755 /var/log
# Install dependencies
RUN apt-get update && apt-get install -y \
    libzip-dev \
    openssl \
    libssl-dev \
    libcurl4-openssl-dev

RUN pecl install mongodb \
    &&  echo "extension=mongodb.so" > $PHP_INI_DIR/conf.d/mongo.ini

RUN apt-get clean && rm -rf /var/lib/apt/lists/*
RUN docker-php-ext-install zip exif pcntl

# Add user for laravel application
RUN groupadd -g 1000 www
RUN useradd -u 1000 -ms /bin/bash -g www www

# Copy existing application directory contents
COPY ./rest-api /var/www
COPY --chown=www:www ./rest-api /var/www
COPY --from=composer /usr/bin/composer /usr/bin/composer
CMD composer install ; composer update ; php-fpm

# Change current user to www
USER www

EXPOSE 9000
