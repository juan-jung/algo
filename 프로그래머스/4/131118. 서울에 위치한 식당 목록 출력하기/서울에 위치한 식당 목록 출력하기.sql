-- 코드를 입력하세요
SELECT A.REST_ID, A.REST_NAME, A.FOOD_TYPE, A.FAVORITES, A.ADDRESS, round(B.SCORE,2) score
from REST_INFO A, (
                    select rest_id, avg(review_score) as score
                    from REST_REVIEW 
                    group by rest_id
                  ) B
where A.rest_id = B.rest_id and
      A.address like "서울%"
order by score desc, A.favorites desc;
