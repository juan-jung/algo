-- 코드를 작성해주세요
SELECT 
    b.SCORE, 
    a.EMP_NO, 
    a.EMP_NAME, 
    a.POSITION, 
    a.EMAIL 
FROM 
    HR_EMPLOYEES a, 
    (
        SELECT EMP_NO, sum(SCORE) as SCORE
        FROM HR_GRADE
        WHERE YEAR = 2022
        GROUP BY EMP_NO
        ORDER BY SCORE desc
        limit 1
    ) b
WHERE a.EMP_NO = b.EMP_NO;


    
