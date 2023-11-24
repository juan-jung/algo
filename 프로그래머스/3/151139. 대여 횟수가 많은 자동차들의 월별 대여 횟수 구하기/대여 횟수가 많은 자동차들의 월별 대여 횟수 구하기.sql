-- 코드를 입력하세요
SELECT month(start_date) MONTH, CAR_ID, count(*) RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY 
where 
    car_id in (select car_id
               from CAR_RENTAL_COMPANY_RENTAL_HISTORY 
               where start_date between "2022-08-01" and "2022-10-31"
               group by car_id
               having count(*) >= 5) and
    month(start_date) in (8,9,10)
group by month(start_date), car_id
having count(*) > 0
order by month(start_date) asc, car_id desc;

