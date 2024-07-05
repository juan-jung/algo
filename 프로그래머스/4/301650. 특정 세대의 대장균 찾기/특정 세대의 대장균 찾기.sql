-- 코드를 작성해주세요
WITH RECURSIVE GEN_DATA AS(
    SELECT
        ID,
        1 as GEN
    FROM
        ECOLI_DATA
    WHERE
        PARENT_ID is null
    
    UNION ALL
    
    SELECT
        e.ID,
        g.GEN + 1 as GEN
    FROM
        ECOLI_DATA e, GEN_DATA g
    WHERE e.parent_ID = g.ID        
)

SELECT
    ID
FROM
    GEN_DATA
WHERE
    GEN = 3
ORDER BY
    ID asc;