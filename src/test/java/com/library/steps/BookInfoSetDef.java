package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.hc.core5.util.Asserts;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookInfoSetDef {

    BookPage bookPage = new BookPage();
    String globalBookName;

    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {

        bookPage.bookSearch(bookName);

    }

    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        BrowserUtil.waitFor(2);
        bookPage.editBook(globalBookName).click();

    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        String queryForSpecificBook= "select * from books where name = 'Book Borrow 2'";

        DB_Util.runQuery(queryForSpecificBook);

        Map<String, String> mapDataFromDB = DB_Util.getRowMap(1);
        System.out.println(mapDataFromDB);

        // get teh name from DB
        String nameDB = mapDataFromDB.get("name");
        String nameUI = bookPage.getBookInfo("Book Name");
        Assert.assertEquals(nameDB,nameUI);
        //get year from db
        String yearDB = mapDataFromDB.get("year");
        //get year from ui
        String yearUI = bookPage.getBookInfo("Year");
        Assert.assertEquals(yearDB,yearUI);

        String actualAuthor = mapDataFromDB.get("author");
        String expectedAuthor = bookPage.getBookInfo("Author");
        Assert.assertEquals(expectedAuthor, actualAuthor);

    }
}
