-- 코드를 작성해주세요
SELECT t.ITEM_ID, i.ITEM_NAME
FROM ITEM_TREE t, ITEM_INFO i
WHERE t.ITEM_ID = i.ITEM_ID
AND t.PARENT_ITEM_ID is null
ORDER BY t.ITEM_ID asc;