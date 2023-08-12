package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class us05_SetDef {

  BookPage bookPage = new BookPage();
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        bookPage = new BookPage();
        String query ="select name from book_categories where id = (select book_category_id from books where id = (select  book_id from book_borrow group by book_id\n" +
                "                                                                                            order by count(*) desc\n" +
                "                                                                                            limit 1));";

        DB_Util.runQuery(query);

    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedFromUser) {

        String actualFromDB = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualFromDB = " + actualFromDB);
        System.out.println("expectedFromUser = " + expectedFromUser);

        Assert.assertEquals(expectedFromUser,actualFromDB);


    }

}
