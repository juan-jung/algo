-- 코드를 입력하세요
SELECT A.PRODUCT_ID, A.PRODUCT_NAME, B.amount * A.price as TOTAL_SALES
from food_product A, (select product_id, sum(amount) as amount 
                      from food_order 
                      where produce_date like "2022-05-%"
                      group by product_id 
                      ) B
where A.product_id = B.product_id
order by TOTAL_SALES desc, product_id asc;
                      
# 생산일자 2022 5
# 총매출 기준 내림차순, 식품ID 오름차순
