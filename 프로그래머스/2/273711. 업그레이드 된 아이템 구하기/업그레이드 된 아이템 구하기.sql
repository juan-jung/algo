-- tree에서 부모id가있는경우 해당 해당 부모아이템은 업그레이드가 가능함
SELECT i.ITEM_ID, i.ITEM_NAME, i.RARITY
FROM ITEM_INFO i, ITEM_TREE t, ITEM_INFO i2
WHERE i.ITEM_ID = t.ITEM_ID 
AND t.PARENT_ITEM_ID is not null 
AND i2.ITEM_ID = t.PARENT_ITEM_ID 
AND i2.RARITY = "RARE"
ORDER BY i.ITEM_ID desc;