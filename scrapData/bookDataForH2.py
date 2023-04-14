import jaydebeapi
import pandas as pd

# H2 데이터베이스 연결
h2_jar_path = "D:/study/h2/bin/h2-2.1.214.jar"  # H2 JAR 파일의 경로를 입력하세요
h2_url = "jdbc:h2:tcp://localhost/~/test"  # H2 데이터베이스 URL
h2_user = "sa"  # H2 사용자 이름
h2_password = ""  # H2 비밀번호
conn = jaydebeapi.connect("org.h2.Driver", h2_url, [h2_user, h2_password], h2_jar_path)

# 데이터 불러오기
df1 = pd.read_csv('한빛네트워크IT책정보_02_17.csv')
df2 = pd.read_csv('한빛네트워크IT책정보_새부사항_02_17.csv')

df1.drop(labels=['책_상세_주소'], axis=1, inplace=True)
df2.drop(labels=['저자'], axis=1, inplace=True)

df3 = pd.concat([df1, df2], axis=1, ignore_index=True)

df3.columns = ['product_title','product_writer','product_price','product_img','product_subtitle','product_date', 'product_page', 'product_code']
df3 = df3[['product_title','product_subtitle','product_writer','product_price','product_img','product_date','product_page', 'product_code']]

df3['product_subtitle'] = df3['product_subtitle'].fillna('no')
df3['product_price'] = df3['product_price'].str.replace(',', '')
df3['product_title'] = df3['product_title'].str.replace("'", "''")
df3['product_price'] = df3['product_price'].astype(int)

cursor = conn.cursor()

insert_data_query = '''
INSERT INTO product (
    product_title, product_subtitle, product_writer,
    product_price, product_img, product_date,
    product_page, product_code
) VALUES (?, ?, ?, ?, ?, ?, ?, ?);
'''

for oneitem in range(len(df3)):
    product_code_cleaned = df3.loc[oneitem][7].replace("\n", "").replace("\t", "")
    data_tuple = (
        df3.loc[oneitem][0],
        df3.loc[oneitem][1],
        df3.loc[oneitem][2],
        df3.loc[oneitem][3],
        df3.loc[oneitem][4],
        df3.loc[oneitem][5],
        df3.loc[oneitem][6],
        product_code_cleaned
    )
    cursor.execute(insert_data_query, data_tuple)
    print(f"Inserted row {oneitem + 1} into the product table.")  # 각 행이 삽입되었음을 나타냅니다.

conn.commit()

# 데이터베이스에서 결과를 확인합니다.
cursor.execute("SELECT * FROM product;")
rows = cursor.fetchall()
print("Product table rows:")
for row in rows:
    print(row)

# 연결 종료
conn.close()
