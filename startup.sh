mkdir -p ./docker/mysql/data
mkdir -p ./docker/mysql/conf/sql
sudo chmod -R 777 ./docker/mysql
sudo chmod -R 755 ./docker/mysql/conf/*.cnf

docker-compose up -d

echo "sleep 20s"
sleep 20s

#  계정 생성
docker exec -it mysql-mars-master mysql -uroot -proot -e " \
CREATE USER 'master'@'%' IDENTIFIED WITH mysql_native_password BY 'master'; \
GRANT REPLICATION SLAVE ON *.* TO 'master'@'%'; \
FLUSH PRIVILEGES;"

# 계정 생성 확인
docker exec -it mysql-mars-master mysql -uroot -proot -Dmysql -e "select user,host from user;"

# db 생성

docker cp ./docker/mysql/conf/sql/init-master.sql mysql-mars-master:/tmp/init-master.sql
docker exec -it mysql-mars-master mysql -uroot -proot -Dmysql -e "source /tmp/init-master.sql"
