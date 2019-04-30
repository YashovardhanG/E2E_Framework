Feature: Verify Login Functionality

Scenario Outline: User displayed successfully after Login
Given Browser is chrome
When Demo portal is launched
And User click on Login Link
Then  User Login Page is Opened
And User provides <username> and <password> and submit
Then User Login successfully
And <username> is displayed

Examples:
|username		|password	|
|aaa123@aaa.com |abcd@1234  |
|bbb1234@bbb.com |abcd@12345  | 
