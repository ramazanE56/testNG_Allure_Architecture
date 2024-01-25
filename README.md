<img src="pngs/readme.png" align="right" />
## User Test Scenarios Made on *trendyol.com* Site
We conducted our tests with three different scenarios on the Hepsiburada website.

- ***first scenario*** <br>

        registered_user_go_to_the_site_with_the_given_URL();
        click_on_the_login_button();
        user_clicks_on_the_email_box_and_enters_email();
        user_clicks_on_the_password_box_and_enters_the_password();
        user_clicks_on_submit_button();
        verifying_that_the_user_has_accessed_the_site();
        close_Driver();


- ***second scenario*** <br>


        registered_user_go_to_the_site_with_the_given_URL();
        click_on_the_login_button();
        user_clicks_on_the_email_box_and_enters_email();
        user_clicks_on_the_password_box_and_enters_the_password();
        user_clicks_on_submit_button();
        user_types_in_the_search_box_and_searches("maya");
        user_prints_the_number_of_all_results_and_verifies_that_they_contain_the_word("maya");
        close_Driver();


- ***third scenario*** <br>

        registered_user_go_to_the_site_with_the_given_URL();
        click_on_the_login_button();
        user_clicks_on_the_email_box_and_enters_email();
        user_clicks_on_the_password_box_and_enters_the_password();
        user_clicks_on_submit_button();
        user_types_in_the_search_box_and_searches("Java");
        user_clicks_on_the_first_product_that_appears();
        verifies_that_the_product_the_user_selected_is_the_same_product();
        user_adds_the_selected_product_to_the_shopping_cart();
        verifies_that_the_product_has_been_added_to_the_shopping_cart();
        quit_Driver();

### First scenario(smoke)= login test 
*** This test is a test of the registered user's ability to successfully enter the site.

### Second scenario(e2e)= product search test 
*** In this test, we test whether the product we are looking for is included in the results of any product we are looking for on our site.

### Third scenario(e2e)= Adding products to cart test 
*** In this test, we successfully add any product we are looking for to the shopping cart.


### First scenario

![.](/pngs/senario01.gif)





### Second scenario
![.](/pngs/senario02.gif)




### Third scenario
![.](/pngs/senario03.gif)




### Log Records
While our tests are running, log records can be seen on the console. These log records are also included in our test files <br>
We also recorded it in the ***Log*** file we created where it was.
![Log](/pngs/log.png "Showing Log Records on the Console")


### Excel Records
It automatically saves the test results to the Excel file **logFile.xlsx** under the headings time, test name, sattus, description.
![Log](/pngs/excel.png "test results excel record")


### PDF Records
It automatically saves the test results to the Excel file **logFilep.pdf** under the headings time, test name, sattus, description.
![Log](/pngs/pdf.png "test results pdf record")


### Allure Reports
We used **Allure-Repot** for detailed analysis of our tests.
![Log](/pngs/allure01.png "test results allure-results")
![Log](/pngs/allure02.png "test results allure-results")
![Log](/pngs/allure03.png "test results allure-results")
![Log](/pngs/allure04.png "test results allure-results")



### About the Framework;

> - The Project is Written in **Java** Programming Language.
> - **Selenium** and **TestNG** Frameworks were used in the scripts written on the web.
> - In the Written Scenarios, the **BDD** structure was used by adapting it to the **TestNG** Framework.
> - Framework Structure Designed with **Page Object Model (POM)**.
> - **Object Oriented Programming (OOP)** was used in the coding done within the framework structure.
> - **Allure Report** Used as Reporting Tool.
> - **Log4j** Library Used for Log Records was Integrated into **Allure Report**.
> - Locations of Test Scenarios in the project are collected in the **PagesLocate** Class.
> - In the project, Test Steps are collected in the **StepDefinitions**' Class. And this Class is extended to the **PagesLocate** Class.
> - In the Project, the User Password is kept in a file outside the Project Folder, and the Password in the **configurations.properties** File is encrypted using the **crypto.Cipher** Java Class and written to the Password in the **configurations.properties** File. In this way, user data security is ensured.
> - Other Data Used in the Tests were Obtained Using the **configurations2.properties** File.
> - Verifications have been made regarding the flow of the test with **Assertion**'s at each stage.


<img src="pngs/java.png" width="100px" height="100px" padding=10px align="left" />
<img src="pngs/selenium.png" width="100px" height="100px" padding=10px align="left" />
<img src="pngs/testng.png" width="150px" height="100px" padding=10px align="left" />
<img src="pngs/maven.png" width="200px" height="100px" padding=10px  align="left" />
<img src="pngs/allure.png" width="200px" height="100px" padding=10px  align="left" />