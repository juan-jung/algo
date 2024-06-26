-- 코드를 입력하세요
(
    SELECT date_format(SALES_DATE,"%Y-%m-%d") sales_date, PRODUCT_ID, USER_ID, SALES_AMOUNT
    from ONLINE_SALE 
    where SALES_DATE like "2022-03%"
    union all
    SELECT date_format(SALES_DATE,"%Y-%m-%d") sales_date, PRODUCT_ID, null USER_ID, SALES_AMOUNT
    from OFFLINE_SALE 
    where SALES_DATE like "2022-03%"
)
order by sales_date asc, product_id asc, user_id asc;

