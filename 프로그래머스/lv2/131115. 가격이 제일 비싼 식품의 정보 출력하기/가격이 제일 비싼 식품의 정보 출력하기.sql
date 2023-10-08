-- 코드를 입력하세요
# SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_CD, CATEGORY, max(PRICE) as PRICE
# from FOOD_PRODUCT;

# select * from food_product;

select * 
from food_product
where price = (select max(price) from food_product);