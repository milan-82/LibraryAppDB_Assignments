package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BookInfoSetDef {

    BookPage bookPage = new BookPage();
    String actualElements;
    String globalBookName;

    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {

        bookPage.search.sendKeys(bookName);

    }

    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button(String book) {

        bookPage.editBook(globalBookName).click();

    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        String query = "select * from books\n" +
                "where name = 'Clean Code'\n" +
                "AND id ='3890'";

        DB_Util.runQuery(query);

        List<String> expectedData = DB_Util.getRowDataAsList(1);

        Assert.assertEquals(expectedData, actualElements);


    }
}
