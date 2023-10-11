-- 코드를 입력하세요
SELECT u.USER_ID, u.NICKNAME, sum(b.price) as TOTAL_SALES
from USED_GOODS_BOARD b join USED_GOODS_USER u
on b.WRITER_ID	 = u.USER_ID
where b.status = "DONE"
group by b.WRITER_ID
having TOTAL_SALES >= 700000
order by TOTAL_SALES;

# select writer_id, sum(price) from USED_GOODS_BOARD
# group by writer_id;
