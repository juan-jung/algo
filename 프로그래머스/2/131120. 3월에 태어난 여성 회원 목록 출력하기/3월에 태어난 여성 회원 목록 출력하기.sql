-- 코드를 입력하세요
SELECT MEMBER_ID, MEMBER_NAME, GENDER, date_format(DATE_OF_BIRTH,"%Y-%m-%d") as DATE_OF_BIRTH
from MEMBER_PROFILE 
where month(DATE_OF_BIRTH) = 3 and tlno is not null and gender = "W"
order by member_id asc;


# select month(date_of_birth) as m
# from member_profile
# where month(date_of_birth) > 1;

# select * from member_profile;