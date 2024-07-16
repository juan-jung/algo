-- 코드를 입력하세요
SELECT
    a.APNT_NO,
    p.PT_NAME,
    p.PT_NO,
    a.MCDP_CD,
    d.DR_NAME,
    a.APNT_YMD
FROM
    PATIENT p,
    DOCTOR d,
    APPOINTMENT a
WHERE
    YEAR(a.APNT_YMD) = 2022 AND   
    MONTH(a.APNT_YMD) = 4 AND 
    DAY(a.APNT_YMD) = 13 AND
    a.APNT_CNCL_YN = "N" AND
    a.MCDP_CD  = "CS" AND
    a.MDDR_ID  = d.DR_ID AND
    a.PT_NO = p.PT_NO
ORDER BY
    APNT_YMD asc
;
    
