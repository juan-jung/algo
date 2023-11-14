-- 코드를 입력하세요
SELECT A.MEMBER_NAME, B.REVIEW_TEXT, date_format(B.REVIEW_DATE,"%Y-%m-%d")
from MEMBER_PROFILE A, REST_REVIEW B
where A.member_id = (
        select member_id
        from REST_REVIEW 
        group by member_id
        order by count(*) desc
        limit 1
    ) and
    A.member_id = B.member_id
order by B.review_date asc, B.review_text asc;





