-- 코드를 입력하세요
SELECT hour(DATETIME) as HOUR, count(*) as COUNT
from animal_outs
where hour(datetime) between 9 and 19
group by HOUR
order by hour asc;

# select * from animal_outs;