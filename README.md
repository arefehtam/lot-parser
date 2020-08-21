# lot-parser

In order to run this project:

* Install `sbt`. (For more info, click [here] [1])
* Set Project Structure (ctl + alt + shift + s):
    * Click on `+` to add new module
    * Select `Import Module` and navigate to where you clone the project and choose `lot-parser`
* In the Project Structure, switch to project tab, make sure the project has default JDK(prefer jdk-1.8.x)    
* Copy 'application.template.conf' and rename it as 'application.conf'  
* Change the path of data in `application.conf`  
* Run `sbt compile` at project root
* Rub `sbt run` at project root and choose `Application` when ask you select main class
* Run 'sbt test' and check all tests passed


[1]: https://www.scala-sbt.org/1.x/docs/Setup.html
