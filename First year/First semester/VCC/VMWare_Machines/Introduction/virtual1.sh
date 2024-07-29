#!/bin/bash

# uses a DOMAIN variable for specifying the domain name handled by the VH
DOMAIN="$1"


sed "s/DOMAIN/$DOMAIN/" /tmp/$DOMAIN.conf > /etc/apache2/sites-available/$DOMAIN.conf


# creates a directory /var/www-[nameofthedomain]
cd /var
test -d /var/www-$DOMAIN || mkdir /var/www-$DOMAIN

# changes the owner in vagrant.vagrant
chown vagrant.vagrant www-$DOMAIN

a2ensite $DOMAIN.conf

apache2ctl restart
systemctl reload apache2

#echo "<h2>Hello</h2>" > /var/www-$DOMAIN/index.html