SELECT A.AUTHOR_ID, B.AUTHOR_NAME, A.CATEGORY, sum(C.sales * A.price) as TOTAL_SALES
from BOOK A, AUTHOR B, (select BOOK_ID, sum(SALES) sales
                       from BOOK_SALES
                       where SALES_DATE BETWEEN "2022-01-01" and "2022-01-31"
                       group by BOOK_ID) C
where A.book_id = C.book_id and A.author_id = B.author_id
group by A.author_id, A.category
order by A.author_id asc, A.category desc;