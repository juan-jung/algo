select 
    A.CAR_ID, 
    A.CAR_TYPE,
    round((1-C.discount_rate/100)*A.daily_fee*30,0)  as FEE
from CAR_RENTAL_COMPANY_CAR A, (
        select car_type, discount_rate 
        from CAR_RENTAL_COMPANY_DISCOUNT_PLAN
        where duration_type = "30일 이상"
    ) C
where 
    A.car_id not in (
        select distinct car_id 
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY 
        where 
            start_date between "2022-11-01" and "2022-11-30" 
            or end_date between "2022-11-01" and "2022-11-30"
            or (start_date <"2022-11-01" and end_date > "2022-11-30")
        ) and
    A.car_type in ("세단", "SUV") and
    A.car_type = C.car_type
    and (1-C.discount_rate/100)*A.daily_fee*30 between 500000 and 2000000
order by Fee desc, A.car_type asc, A.car_id desc
;


# select * from CAR_RENTAL_COMPANY_RENTAL_HISTORY  where car_id in (27, 18) 
# order by  car_id, start_date;





