select count(id)
from users; -- actual

select count(*) users; -- expected

select count(distinct id)
from users;

-- US02
SELECT COUNT(*)
FROM book_borrow
WHERE is_returned = 0;

-- US03
select name
from book_categories;

#us04
select * from books
where name = 'Book Borrow 2';

select b.name,b_c.name from books b
inner join book_categories b_c
on b.book_category_id = b_c.id = b_c.id
where b.name = 'Book Borrow 2';

#us05.feature
#I have the most popular book id
select  book_id  from book_borrow group by book_id
order by count(*) desc
limit 1;

#in order to get the category name I need to find the category id of that book
select book_category_id from books where id = (select  book_id from book_borrow group by book_id
                                order by count(*) desc
                                limit 1);

select name from book_categories where id = (select book_category_id from books where id = (select  book_id from book_borrow group by book_id
                                                                                            order by count(*) desc
                                                                                            limit 1));



#us06
select name, isbn,year,author from books
where name = 'Book Borrow 2';

