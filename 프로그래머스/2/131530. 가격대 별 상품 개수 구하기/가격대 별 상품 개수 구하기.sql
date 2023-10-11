-- 코드를 입력하세요
SELECT price div 10000 * 10000 as PRICE_GROUP, count(*) PRODUCTS
from product
group by PRICE_GROUP
order by PRICE_GROUP;

# select * from product;