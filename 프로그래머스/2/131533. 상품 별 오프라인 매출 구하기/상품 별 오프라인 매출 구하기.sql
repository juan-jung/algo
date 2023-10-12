-- 코드를 입력하세요
SELECT p.PRODUCT_CODE, p.price*sum(s.sales_amount) as SALES
from offline_sale s join product p
on s.product_id = p.product_id
group by s.product_id
order by SALES desc, PRODUCT_CODE asc;