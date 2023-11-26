-- 코드를 입력하세요
SELECT year(sales_date) YEAR, month(sales_date) MONTH, count(distinct A.user_id) PUCHASED_USERS, round(count( distinct A.user_id)/C.cnt,1) as PUCHASED_RATIO
from USER_INFO A , ONLINE_SALE B, (select count(*) cnt from user_info where year(joined) = 2021) C
where year(A.joined) = 2021 and A.user_id = B.user_id
group by YEAR, MONTH
order by YEAR, MONTH;
