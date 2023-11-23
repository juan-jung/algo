-- 코드를 입력하세요
select 
    distinct A.car_id, 
    case
        when B.N is null then "대여 가능"
        when B.N = "대여 불가" then "대여중"
        end as AVAILABILITY
from 
    CAR_RENTAL_COMPANY_RENTAL_HISTORY A left outer join
    (
        SELECT distinct car_id, "대여 불가" N
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY 
        where "2022-10-16" between start_date and end_date
    ) B
on A.car_id = B.car_id
order by A.car_id desc;

