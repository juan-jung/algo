-- 코드를 입력하세요
SELECT 
    HISTORY_ID,
    CAR_ID,
    date_format(start_date,"%Y-%m-%d") START_DATE,
    date_format(end_date,"%Y-%m-%d") END_DATE, 
    case 
        when timestampdiff(day,start_date,end_date) >= 29 then "장기 대여" 
        else "단기 대여" 
    end as RENT_TYPE
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where start_date like "%2022-09%"
order by history_id desc;



# select history_id, start_date, timestampdiff(day,start_date,end_date) from CAR_RENTAL_COMPANY_RENTAL_HISTORY;

# select * from CAR_RENTAL_COMPANY_RENTAL_HISTORY where start_date like "%2022-09%";