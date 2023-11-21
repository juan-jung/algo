-- 코드를 입력하세요
SELECT A.CATEGORY, sum(B.sales) TOTAL_SALES
from book A, (
                select book_id, sum(sales) sales
                from book_sales
                where sales_date like "2022-01%"
                group by book_id
             ) B
where A.book_id = B.book_id
group by A.category
order by A.category asc;