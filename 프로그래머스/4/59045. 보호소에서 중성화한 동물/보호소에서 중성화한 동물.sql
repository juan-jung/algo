-- 시설에서 중성화가 진행된
SELECT ANIMAL_ID, ANIMAL_TYPE, NAME
from ANIMAL_OUTS 
where animal_id in (
        select animal_id
        from animal_ins
        where SEX_UPON_INTAKE like "Intact%"
    ) and
    SEX_UPON_OUTCOME not like "Intact%"
order by animal_id;