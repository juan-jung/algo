-- 코드를 입력하세요
SELECT BOOK_ID, AUTHOR_NAME, DATE_format(PUBLISHED_DATE,"%Y-%m-%d") as  PUBLISHED_DATE
from book join author
on book.author_id = author.author_id
where category = "경제"
order by published_date;

# select * from book;

# select * from author;