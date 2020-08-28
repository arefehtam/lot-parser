# lot-parser

This is written in `scala`.
In order to run this project(preferably install InteliJIdea community edition):

* In case InteliJ has been installed: 
    * Set Project Structure (ctl + alt + shift + s):
        * Click on `+` to add new module
        * Select `Import Module` and navigate to where you clone the project and choose `lot-parser`
    * In the Project Structure, switch to project tab, make sure the project has default JDK(prefer jdk-1.8.x)    
* Copy 'application.template.conf' and rename it as 'application.conf'  
* Change the path of data in `application.conf`  
* Run `sbt compile` at project root
* Make sure the file `application.conf` is copied in target path: target/scala-2*/classes, otherwise,
copy it manually
* Rub `sbt run` at project root and choose `Application` when ask you select main class
* Run 'sbt test' and check all tests passed

# Prerequisite
* Install `sbt`. (For more info, click [here] [1])

[1]: https://www.scala-sbt.org/1.x/docs/Setup.html
