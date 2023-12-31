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
        String queryForSpecificBook = "select * from books where name = 'Book Borrow 2'";

        DB_Util.runQuery(queryForSpecificBook);

        Map<String, String> mapDataFromDB = DB_Util.getRowMap(1);
        System.out.println(mapDataFromDB);

        // get teh name from DB
        String nameDB = mapDataFromDB.get("name");
        String nameUI = bookPage.getBookInfo("Book Name");
        Assert.assertEquals(nameDB, nameUI);
        //get year from db
        String yearDB = mapDataFromDB.get("year");
        //get year from ui
        String yearUI = bookPage.getBookInfo("Year");
        Assert.assertEquals(yearDB, yearUI);

        String actualAuthor = mapDataFromDB.get("author");
        String expectedAuthor = bookPage.getBookInfo("Author");
        Assert.assertEquals(expectedAuthor, actualAuthor);

    }

    //US06

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {

        bookPage.addBook.click();
    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String name) {

        bookPage.bookName.sendKeys(name);

    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {

        bookPage.isbn.sendKeys(isbn);
    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {

        bookPage.year.sendKeys(year);

    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {

        bookPage.author.sendKeys(author);
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String category) {

        BrowserUtil.selectOptionDropdown(bookPage.categoryDropdown, category);

    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {

        bookPage.saveChanges.click();
    }

    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String expectedMessage) {

        String actualMessage = bookPage.toastMessage.getText();
        Assert.assertEquals(expectedMessage, actualMessage);


        Assert.assertTrue(bookPage.toastMessage.isDisplayed());

    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBookName) {

        String query = "select name, author, isbn from books\n" +
                "where name = '" + expectedBookName + "'";

        DB_Util.runQuery(query);

        Map<String, String> rowMap = DB_Util.getRowMap(1);

        String actualBookName = rowMap.get("name");

        Assert.assertEquals(expectedBookName, actualBookName);


    }

}
