# G031 - Integrative Project Report

#Introduction
#Index
* Sprint 1 - From US101 to US110
* Sprint 2 - From US201 to US210

##US101
###Analysis
![US101-SSD](docs/Sprint1/US101/US101-SSD.svg)
![US101-MD](docs/Sprint1/US101/US101-MD.svg)
###Design
![US101-SD](docs/Sprint1/US101/US101-SD.svg)
![readCreateInsert](docs/Sprint1/US101/readCreateInsert.svg)
![US101-CD](docs/Sprint1/US101/US101-CD.svg)
###Implementation

####Test Description

---
##US102
###Analysis
![US102-SSD](docs/Sprint1/US102/US102-SSD.svg)
![US102-MD](docs/Sprint1/US102/US102-MD.svg)
###Design
![US102-SD](docs/Sprint1/US102/US102-SD.svg)
![US102-CD](docs/Sprint1/US102/US102-CD.svg)
###Implementation

####Test Description

---

##US103
###Analysis

###Design

###Implementation

####Test Description
By the description provided by the client they will want to have this implemented:
* Access position Messages of ship given period of time or date 
    * Information Structure need to be well organized - Test!
    * Period must be valid - Test!
    * Date must be valid - Test!
    * If Ship has not moved - information must be provided - Test!
    * Output must be well-designed and in order - Test!
    * Messages need to be translated to position effectively - Test!
* Access to Ship Information
    * Assured by US101 Tests

---

##US201 <a name="us201"></a>
###Analysis
![US201-SSD](docs/Sprint 2/US201/US201-SSD.svg)
![US201-MD](docs/Sprint 2/US201/US201-MD.svg)
###Design
![US201-SD](docs/Sprint 2/US201/US201-SD.svg)
![US201-CD](docs/Sprint 2/US201/US201-CD.svg)
###Implementation

1. We will need to import the Ports from file
  1. We will need, for each Port, to verify its values in construction
  1. We will need to insert Port in Class and use Coordinates to Compare
1. We will need to add them to Database


####Test Description
Only information to be presented is success/not success - We need to text this output
  * Test if Ports where imported successfully!
      * Added to the Database.
  * Test if Ports where not imported!

We need to check if 2D-Tree is Balanced:
  * Test 2DTree Methods
    * Insert
    * Remove
    * Find
  

---

##US202 <a name="us202"></a>
###Analysis
![US202-SSD](docs/Sprint 2/US202/US202-SSD.svg)
![US202-MD](docs/Sprint 2/US202/US202-MD.svg)
###Design
![US202-SD](docs/Sprint 2/US202/US202-SD.svg)
![US202-CD](docs/Sprint 2/US202/US202-CDx.svg)
###Implementation

####Test Description

---

##US204 <a name="us204"></a>
###Analysis
![US204-SSD](docs/Sprint 2/US204/US204-SSD.jpg)
![US204-MD](docs/Sprint 2/US204/US204-MD.jpg)
###Design
![US204-SD](docs/Sprint 2/US204/US204-SD.svg)
![US204-CD](docs/Sprint 2/US204/US204-CD.svg)
###Implementation

####Test Description

---

##US205 <a name="us205"></a>
###Analysis
![US205-SSD](docs/Sprint 2/US205/US205-SSD.svg)
![US205-MD](docs/Sprint 2/US205/US205-MD.svg)
###Design
![US205-SD](docs/Sprint 2/US205/US205-SD.svg)
![US205-CD](docs/Sprint 2/US205/US205-CD.svg)
###Implementation

####Test Description

---

##US206 <a name="us206"></a>
###Analysis
![US206-SSD](docs/Sprint 2/US206/US206-SSD.svg)
![US206-MD](docs/Sprint 2/US206/US206-MD.svg)
###Design
![US206-SD](docs/Sprint 2/US206/US206-SD.svg)
![US206-CD](docs/Sprint 2/US206/US206-CD.svg)
###Implementation

####Test Description

---

##US207 <a name="us207"></a>
###Analysis
![US207-SSD](docs/Sprint 2/US207/US207-SSD.svg)
![US207-MD](docs/Sprint 2/US207/US207-MD.svg)
###Design
![US207-SD](docs/Sprint 2/US207/US207-SD.svg)
![US207-CD](docs/Sprint 2/US207/US207-CD.svg)
###Implementation

####Test Description

---

##US208 <a name="us208"></a>
###Analysis
![US208-SSD](docs/Sprint 2/US208/US208-SSD.jpg)
![US208-MD](docs/Sprint 2/US208/US208-MD.jpg)
###Design
![US208-SD](docs/Sprint 2/US208/US208-SD.svg)
![US208-CD](docs/Sprint 2/US208/US208-CD.svg)
###Implementation

####Test Description

---

##US209 <a name="us209"></a>
###Analysis
![US209-SSD](docs/Sprint 2/US209/US209-SSD.jpg)
![US209-MD](docs/Sprint 2/US209/US209-MD.jpg)
###Design
![US209-SD](docs/Sprint 2/US209/US209-SD.svg)
![US209-CD](docs/Sprint 2/US209/US209-CD.svg)
###Implementation

####Test Description

---

##US210 <a name="us210"></a>
###Analysis
![US210-SSD](docs/Sprint 2/US210/US210-SSD.svg)
![US210-MD](docs/Sprint 2/US210/US210-MD.svg)
###Design
![US210-SD](docs/Sprint 2/US210/US210-SD.svg)
![US210-CD](docs/Sprint 2/US210/US210-CD.svg)
###Implementation

####Test Description



---
##Domain Model

![Domain Model](docs/DM.svg)

---

##Class Diagram

![Class Diagram](docs/CD.svg)

---
---
---
# README

This is the repository template used for student repositories in LAPR Projets.

## Java source files

Java source and test files are located in folder src.

## Maven file

Pom.xml file controls the project build.

### Notes
In this file, DO NOT EDIT the following elements:

* groupID
* artifactID
* version
* properties

Beside, students can only add dependencies to the specified section of this file.

## Eclipse files

The following files are solely used by Eclipse IDE:

* .classpath
* .project

## IntelliJ Idea IDE files

The following folder is solely used by Intellij Idea IDE :

* .idea

# How was the .gitignore file generated?
.gitignore file was generated based on https://www.gitignore.io/ with the following keywords:

  - Java
  - Maven
  - Eclipse
  - NetBeans
  - Intellij

# Who do I talk to?
In case you have any problem, please email Nuno Bettencourt (nmb@isep.ipp.pt).

# How do I use Maven?

## How to run unit tests?

Execute the "test" goals.

```shell
$ mvn test
```
## How to generate the javadoc for source code?

Execute the "javadoc:javadoc" goal.

```shell
$ mvn javadoc:javadoc
```
This generates the source code javadoc in folder "target/site/apidocs/index.html".

## How to generate the javadoc for test cases code?

Execute the "javadoc:test-javadoc" goal.

```shell
$ mvn javadoc:test-javadoc
```
This generates the test cases javadoc in folder "target/site/testapidocs/index.html".

## How to generate Jacoco's Code Coverage Report?

Execute the "jacoco:report" goal.

```shell
$ mvn test jacoco:report
```

This generates a jacoco code coverage report in folder "target/site/jacoco/index.html".

## How to generate PIT Mutation Code Coverage?

Execute the "org.pitest:pitest-maven:mutationCoverage" goal.

```shell
$ mvn test org.pitest:pitest-maven:mutationCoverage
```
This generates a PIT Mutation coverage report in folder "target/pit-reports/YYYYMMDDHHMI".

## How to combine different maven goals in one step?

You can combine different maven goals in the same command. For example, to locally run your project just like on jenkins, use:

```shell
$ mvn clean test jacoco:report org.pitest:pitest-maven:mutationCoverage
```
## How to perform a faster pit mutation analysis?

Do not clean build => remove "clean"

Reuse the previous report => add "-Dsonar.pitest.mode=reuseReport"

Use more threads to perform the analysis. The number is dependent on each computer CPU => add "-Dthreads=4"

Temporarily remove timestamps from reports.

Example:
```shell
$ mvn test jacoco:report org.pitest:pitest-maven:mutationCoverage -DhistoryInputFile=target/fasterPitMutationTesting-history.txt -DhistoryOutputFile=target/fasterPitMutationTesting-history.txt -Dsonar.pitest.mode=reuseReport -Dthreads=4 -DtimestampedReports=false
```
## Where do I configure my database connection?

Each group should configure their database connection on the file:
* src/main/resources/application.properties