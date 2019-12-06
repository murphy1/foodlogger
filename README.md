# foodlogger
Reactive Spring Boot application for tracking daily food intake.

Uses the Nutritionix API for Nutritional data and Food data.
Powered By Nutritionix.

- FoodLogger helps you keep track of your nutritional intake

![Home](https://github.com/murphy1/foodlogger/blob/master/src/main/resources/static/homepage.JPG)

- Create your own goals and see your progress 

![Home](https://github.com/murphy1/foodlogger/blob/master/src/main/resources/static/dailygoals.JPG)

- See what you eat throughout the day

![Home](https://github.com/murphy1/foodlogger/blob/master/src/main/resources/static/foodconsumed.JPG)

- Easily search Nutritionix's massive database of foods to find what you eat

![Home](https://github.com/murphy1/foodlogger/blob/master/src/main/resources/static/searchPage.JPG)

![Home](https://github.com/murphy1/foodlogger/blob/master/src/main/resources/static/search.JPG)

- See whats in your food to maintain a healthier lifestyle

![Home](https://github.com/murphy1/foodlogger/blob/master/src/main/resources/static/values.JPG)

## Prerequisites

```
- MongoDB to store data

- Users will require an API key for Nutritionix. Please see the following:
  https://www.nutritionix.com/business/api

- You will need a Mail Server active to make use of the password reset functionality
```

## How to Run

```
1) Install an instance of MongoDB

2) Get an API key from Nutritionix
   
   Open the NutritionixServiceImpl and go to line 32

   Here you will need to enter the API keys (I have them set as environment variables)
   private String appId = System.getenv("APP_ID");
   private String appKey = System.getenv("APP_KEY");

3) Open the 'application.properties' file to configure the mail server
   
   spring.mail.host=smtp.gmail.com
   spring.mail.username=
   spring.mail.password=
   spring.mail.port=587
   spring.mail.properties.mail.smtp.auth=true
   spring.mail.properties.mail.smtp.starttls.enable=true

 You are now good to go.
```

## Further Information

- Feel free to reach me directly for further assistance. Email: smurphy54321@gmail.com

- Or open an Issue
