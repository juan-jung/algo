-- 코드를 작성해주세요
SELECT 
    E.EMP_NO,
    E.EMP_NAME,
    CASE
        WHEN G.SCORE >= 96 THEN "S"
        WHEN G.SCORE >= 90 THEN "A"
        WHEN G.SCORE >= 80 THEN "B"
        ELSE "C"
    END AS GRADE,
    CASE
        WHEN G.SCORE >= 96 THEN E.SAL * 0.2
        WHEN G.SCORE >= 90 THEN E.SAL * 0.15
        WHEN G.SCORE >= 80 THEN E.SAL * 0.1
        ELSE 0
    END AS BONUS
FROM
    HR_EMPLOYEES E,
    (
        SELECT EMP_NO, SUM(SCORE)/2 as SCORE
        FROM HR_GRADE
        GROUP BY EMP_NO
    ) AS G
WHERE 
    E.EMP_NO = G.EMP_NO
ORDER BY 
    E.EMP_NO asc;

    