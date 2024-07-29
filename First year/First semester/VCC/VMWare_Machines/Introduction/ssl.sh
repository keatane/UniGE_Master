#!/bin/bash
DOMAIN="$1"
PRIVKEY="/etc/ssl/private/$DOMAIN.key"
CERT=""/etc/ssl/certs/$DOMAIN.pem""

sed "s/DOMAIN/$DOMAIN/" /tmp/$DOMAIN-ssl.conf > /etc/apache2/sites-available/$DOMAIN-ssl.conf

openssl req -subj '/CN=test.local/O=VCC/C=IT' -new -newkey rsa:2048 -sha256 -days 3650 -nodes -x509 -keyout $PRIVKEY -out $CERT

# private key is stored in
chown root.ssl-cert $PRIVKEY
chown root.root $CERT
chmod 640 $PRIVKEY
chmod 644 $CERT

a2enmod ssl
a2ensite $DOMAIN-ssl
apache2ctl restart
systemctl reload apache2
