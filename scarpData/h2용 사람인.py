# h2Shops.py
import pandas as pd
import jaydebeapi

df1 = pd.read_csv('사람인_공고별_데이터_01.csv')
df2 = pd.read_csv('사람인_공고별_데이터_03.csv')

df2.drop(labels=['지원페이지', 'html_text'], axis=1, inplace=True)

df3 = pd.concat([df1, df2], axis=1, ignore_index=True)

print(df1.info())
print(df2.info())

df3.columns = ['jinfoField', 'companyname', 'infoname', 'ftaglist', 'qualificat', 'worktype', 'workarea', 'Deadline', 'infolink', 'pagecode']

print(df3.info())
print(df3.head())
print(df3.loc[0]['infolink'])

conn = None
cur = None

try:
    # Replace the path with the location of your h2 jar file.
    conn = jaydebeapi.connect("org.h2.Driver", "jdbc:h2:tcp://localhost/~/test", ["sa", ""], "D:/study/h2/bin/h2-2.1.214.jar")
    cur = conn.cursor()

    for oneitem in range(len(df3)):
        print(oneitem)
        df3.loc[oneitem]['ftaglist'] = str(df3.loc[oneitem]['ftaglist']).replace("[", '').replace("]", '').replace("'",
                                                                                                                   '')
        df3.loc[oneitem]['qualificat'] = str(df3.loc[oneitem]['qualificat']).replace("[", '').replace("]", '').replace(
            "'", '')
        df3.loc[oneitem]['Deadline'] = str(df3.loc[oneitem]['Deadline']).replace("[", '').replace("]", '').replace("'",
                                                                                                                   '')
        df3.loc[oneitem]['infoname'] = str(df3.loc[oneitem]['infoname']).replace("[", '').replace("]", '').replace("'",
                                                                                                                   '"')
        sql = f"""
        INSERT INTO job_info (job_info_Field, job_info_company_name, job_info_posting_name, job_info_field_tag_list, job_info_Qualification, job_info_work_type, job_info_work_area, job_info_Deadline, job_info_page_code, job_info_link)
        VALUES (?,?,?,?,?,?,?,?,?,?)
        """

        print(sql)
        data = str(df3.loc[oneitem]['pagecode'])

        cur.execute(sql, (df3.loc[oneitem]['jinfoField'], df3.loc[oneitem]['companyname'], df3.loc[oneitem]['infoname'],
                          df3.loc[oneitem]['ftaglist'], df3.loc[oneitem]['qualificat'], df3.loc[oneitem]['worktype'],
                          df3.loc[oneitem]['workarea'], df3.loc[oneitem]['Deadline'], data,
                          df3.loc[oneitem]['infolink']))

    conn.commit()
except Exception as err:
    if conn is not None:
        conn.rollback()
    print(err)

finally:
    if cur is not None:
        cur.close()

    if conn is not None:
        conn.close()

print('finished')
