FROM mysql:5.6

EXPOSE 3306

#MySQL設定ファイルをイメージ内にコピー
ADD ./my.cnf /etc/mysql/my.cnf

CMD [ "mysqld" ]