# lot-parser

In order to run this project:

* Install `sbt`. (For more info, click [here] [1])
* Set Project Structure (ctl + alt + shift + s):
    * Click on `+` to add new module
    * Select `Import Module` and navigate to where you clone the project and choose `lot-parser` 
* Copy 'application.template.conf' and rename it as 'application.conf'    
* Run `sbt compile` at project root
* Rub `sbt run` at project root and choose `Application` when ask you select main class
* Run 'sbt test' and check all tests passed

[1]: https://www.scala-sbt.org/1.x/docs/Setup.html
