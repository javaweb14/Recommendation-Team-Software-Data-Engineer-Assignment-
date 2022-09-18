#import needed libraries
from sqlalchemy import create_engine
import pandas as pd
from sqlalchemy import inspect

pwd = "123456"
uid = "postgres"
server = "localhost"

#extract data from postgres
def extract():
    try:
        engine = create_engine(f'postgresql://{uid}:{pwd}@{server}:5435/data-db')
        insp = inspect(engine)
        print(insp.get_table_names())
        src_tables = insp.get_table_names()
        for tbl in src_tables:
            df = pd.read_sql_query(f'select * FROM {tbl}',engine)       
            load(tbl,df)
    except Exception as e:
        print("Data extract error: " + str(e))

#load data to postgres
def load(tbl,df):
    try:
        rows_imported = 0
        engine = create_engine(f'postgresql://{uid}:{pwd}@{server}:5435/destination-db')
        print(f'importing rows {rows_imported} to {rows_imported + len(df)} for table {tbl}')
        # save df to postgres
        df.to_sql(f'{tbl}', engine, if_exists='replace', index=False)
        rows_imported += len(df)      
        print("data imported successful")
    except Exception as e:
        print("Data load error: " + str(e))

try:
    #call extract function
    extract()  
except Exception as e:
    print("Error while extracting data: " + str(e))