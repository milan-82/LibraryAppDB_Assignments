select  count(id) from users; -- actual

select count(*) users; -- expected

select * from users;

-- US02
SELECT COUNT(*) FROM book_borrow
WHERE is_returned=0;

-- US03
select name from book_categories;