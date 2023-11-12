-- 코드를 입력하세요
SELECT A.FLAVOR
from FIRST_HALF A, ICECREAM_INFO B 
where A.Flavor = B. Flavor and B.INGREDIENT_TYPE = "fruit_based" and A.total_order > 3000
order by A.total_order desc;

