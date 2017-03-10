TYPE=VIEW
query=select `vote`.`project_data`.`PROJECT_ID` AS `PROJECT_ID`,`vote`.`project_data`.`PROJECT_PASSWORD` AS `PROJECT_PASSWORD`,`vote`.`project_data`.`PROJECT_IMAGE` AS `PROJECT_IMAGE`,`vote`.`project_data`.`PROJECT_INTRODUCTION` AS `PROJECT_INTRODUCTION` from `vote`.`project_data` where isnull(`vote`.`project_data`.`PROJECT_NAME`)
md5=c6560d79ea31ce29b6dbe94e033d8ad5
updatable=1
algorithm=0
definer_user=vote
definer_host=127.0.0.1
suid=2
with_check_option=0
timestamp=2016-05-17 05:03:03
create-version=1
source=SELECT PROJECT_ID,PROJECT_PASSWORD,PROJECT_IMAGE,PROJECT_INTRODUCTION\nFROM project_data\nWHERE PROJECT_NAME IS NULL
client_cs_name=utf8
connection_cl_name=utf8_general_ci
view_body_utf8=select `vote`.`project_data`.`PROJECT_ID` AS `PROJECT_ID`,`vote`.`project_data`.`PROJECT_PASSWORD` AS `PROJECT_PASSWORD`,`vote`.`project_data`.`PROJECT_IMAGE` AS `PROJECT_IMAGE`,`vote`.`project_data`.`PROJECT_INTRODUCTION` AS `PROJECT_INTRODUCTION` from `vote`.`project_data` where isnull(`vote`.`project_data`.`PROJECT_NAME`)
