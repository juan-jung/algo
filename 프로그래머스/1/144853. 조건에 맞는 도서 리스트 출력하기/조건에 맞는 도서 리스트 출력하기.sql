-- 코드를 입력하세요
SELECT BOOK_ID, date_format(PUBLISHED_DATE,"%Y-%m-%d") PUBLISHED_DATE
# select *
from book
where year(published_date) = 2021 and category = "인문"
order by published_date asc;

# select year("2022-02-01");