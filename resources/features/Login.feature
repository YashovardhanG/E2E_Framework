Feature: Verify Login Functionality

Scenario: User displayed successfully after Login
Given Browser is chrome
When Demo portal is launched
And User click on Login Link
Then  User Login Page is Opened
And User provides "aaa123@aaa.com" and "abcd@1234" and submit
Then User Login successfully
And "aaa123@aaa.com" is displayed

Scenario: Demo Portal Title displayed successfully after Login
Given Browser is chrome
When Demo portal is launched
And User click on Login Link
Then  User Login Page is Opened
And User provides "bbb123@bbb.com" and "abcd@1234" and submit
Then User Login successfully
And Expected title is displayed
