Simple API consumer for Android.  Probably a bit rough.

It consumes the API created by this project:

https://github.com/jmkapp/kotlin-spring-boot-greeting

After you have loaded the Spring Boot app into a cloud service like AWS Elastic Beanstalk, change the URL in the `ApiService.kt` file to your URL.

This app takes the advice given here:

http://stackoverflow.com/a/21284021/3551058

to use `IntentService` rather than `AsyncTask`.
