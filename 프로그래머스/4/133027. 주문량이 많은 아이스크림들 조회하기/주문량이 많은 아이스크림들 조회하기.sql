-- 코드를 입력하세요
select f.flavor
from first_half f join (select flavor, sum(total_order) total_order from july group by flavor) j
on f.flavor = j.flavor
order by f.total_order + j.total_order desc
limit 3;


# select flavor, sum(total_order) from july group by flavor;