# Recommendation-Team-Software-Data-Engineer-Assignment-

i used docker-compose.yml and i changed postgres port as 5435 because of port is using on my local laptop.i configured all projects which use it according to 5435 port.

--ETL PIPELİNE 

1 - firstly you need to create destination-db database with below command

CREATE DATABASE destination-db;

--you should run below commands 

pip install sqlalchemy

pip install pandas

pip install psycopg2

2- after installing libraries run below command

python etl.py


please make sure postgres(5435) and kafka are running before the following items

after run etl.py you can run Stream-Reader-App ,  View-Producer-App and API projects

when run API project request can be made via below link

http://localhost:8087/swagger-ui.html#/browsing-history-controller

![image](https://user-images.githubusercontent.com/35763530/191609842-eb41e1fe-f317-4b68-a113-e600ed50cd74.png)
