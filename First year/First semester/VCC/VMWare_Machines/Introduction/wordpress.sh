#!/bin/bash

DBNAME=$1
DBUSER=$2 
DBPASS=$3
DOMAIN=$4

echo "$DBNAME;$DBUSER;$DBPASS"

sudo mysql -e "use $DBNAME"
if [ $? != 0 ]; then
    echo "Create db $DBNAME"
    mysql -e "CREATE DATABASE $DBNAME DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;"
    mysql -e "CREATE USER '$DBUSER'@'%' IDENTIFIED WITH mysql_native_password BY '$DBPASS';"
    mysql -e "GRANT ALL ON $DBNAME.* TO $DBUSER@'%';"
    mysql -e "FLUSH PRIVILEGES;"
else
    echo "$DBNAME exists"
fi

mysql -u $DBUSER --password="$DBPASS" $DBNAME -e "select 1";
if [ $? == 0 ]; then
    echo "Installing"
    apt install -y php-curl php-gd php-mbstring php-xml php-xmlrpc php-soap php-intl php-zip
    a2enmod rewrite
    cd /tmp; curl -O https://wordpress.org/latest.tar.gz
    cd /var/www-$DOMAIN
    tar xvfz /tmp/latest.tar.gz --strip 1
    chown -R www-data:www-data /var/www-$DOMAIN
    find /var/www-$DOMAIN/ -type d -exec chmod 750 {} \;
    find /var/www-$DOMAIN/ -type f -exec chmod 640 {} \;
    apache2ctl restart
    systemctl reload apache2
    echo "Should be ok"
else
    echo "Problem with $DBUSER"
fi