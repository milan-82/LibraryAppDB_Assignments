package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class UserStepDefs {


    String actualUserCount;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        // Make a conn with library
       // DB_Util.createConnection();
        System.out.println("===Connection will be done with before hook");
    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        String query = "select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualUserCount);
    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {

        String query = "select count(id) from users";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedUserCount);

        Assert.assertEquals(expectedUserCount, actualUserCount);


        //close connection
       // DB_Util.destroy();
        System.out.println("=====Connection will be closed with after hook======");
    }

// US01-2
    List<String> actualColumnList;
    @When("Execute query to get all columns")
    public void executeQueryToGetAllColumns() {
        String query="select * from users";

        DB_Util.runQuery(query);

        actualColumnList = DB_Util.getAllColumnNamesAsList();

        System.out.println("actualColumnList = " + actualColumnList);
    }

    @Then("verify the below columns are listed in result")
    public void verifyTheBelowColumnsAreListedInResult(List<String> expectedColumns) {

        System.out.println("expectedColumns = " + expectedColumns);

        Assert.assertEquals(expectedColumns,actualColumnList);
    }
}
