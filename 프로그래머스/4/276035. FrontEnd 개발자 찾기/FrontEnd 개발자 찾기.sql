-- 코드를 작성해주세요
SELECT 
    d.ID,
    d.EMAIL,
    d.FIRST_NAME,
    d.LAST_NAME
FROM 
    DEVELOPERS d 
WHERE EXISTS
    (SELECT 1 FROM SKILLCODES s WHERE s.CATEGORY = "Front End" and d.SKILL_CODE & s.CODE > 0)
ORDER BY d.ID asc;

