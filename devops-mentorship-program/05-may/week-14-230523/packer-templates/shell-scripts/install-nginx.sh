#/bin/bash

echo "This is script to install and setup nginx"
sudo yum install -y nginx
sudo systemctl start nginx
sudo systemctl enable nginx
