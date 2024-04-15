SELECT 
    a.ID, 
    b.FISH_NAME, 
    a.LENGTH
FROM 
    FISH_INFO a, 
    FISH_NAME_INFO b
WHERE 
    a.FISH_TYPE = b.FISH_TYPE
    AND (a.FISH_TYPE, a.LENGTH) in (SELECT FISH_TYPE, MAX(LENGTH) FROM FISH_INFO GROUP BY FISH_TYPE)
ORDER BY a.ID;