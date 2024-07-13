# SQL 基础语法

```sql
SELECT column1, column2, column3
FROM table_name;
where condition1 AND condition2;
```

```sql
-- 仅仅返回不同值
SELECT DISTINCT column1, column2, column3
FROM table_name;
```

`WHERE` 子句用于过滤数据：
`=`, `!=`, `<`, `>`, `<=`, `>=`, `BETWEEN`, `LIKE`, `IN`, `NOT IN`, `IS NULL`, `IS NOT NULL`

```sql
-- 排序
SELECT column1, column2, column3
FROM table_name
ORDER BY column1 ASC|DESC;
```
