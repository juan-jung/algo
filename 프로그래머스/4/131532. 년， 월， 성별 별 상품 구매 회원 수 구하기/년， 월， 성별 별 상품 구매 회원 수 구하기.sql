-- 코드를 입력하세요
SELECT year(A.SALES_DATE) YEAR, month(A.SALES_DATE) MONTH,	B.GENDER, count(distinct A.user_id) USERS
from ONLINE_SALE A, user_info B
where A.user_id = B.user_id and B.gender is not null
group by year, month, gender
order by year, month, gender;