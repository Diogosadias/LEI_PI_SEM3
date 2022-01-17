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

##US301
###Analysis
![US301-SSD](docs/Sprint3/US301/US301-SSD.svg)
![US301-MD](docs/Sprint3/US301/US301-MD.svg)
###Design
![US301-SD](docs/Sprint3/US301/US301-SD.svg)
![US301-CD](docs/Sprint3/US301/US301-CD.svg)
###Implementation

####Test Description

According to the AC we want to :
* Implement a Graph with the Vertices of Cities/Capitals and Ports.
    * This graph needs to use an adjacency matrix representation.
* The Edges of the graph represent the connection between the Cities and Ports
    * Each Capital is connected to the nearest Port of its Country
    * Each Capital also connects with the Capital of the Countries it borders.
    * Each Port connects to all Ports of the Same Country.
    * Each Port connects with n-Number of Ports Closest to it. N Being defined in the program.
    * All Edges are weighed and the weigh represents the distance between the to Places that are connected in KM's

We need to Test:
* If the graph has Ports and Capitals at the Same time.
* If the Capital is connected to the nearest Country Port
    * if the Capital doesn't connect to a neighbour Port
    * if the Capital can update the Port it connects when a new closest Port is added - ?
* If the Capital is connected with all Capitals of Country it borders and if is not connected with a Country it has no border with
* If a Port is connected with all the Ports of the Same Country 
    * If, when inserted, it connectes with all the Ports of a country.
* If has a connection with the n necessary ports
* if the weigh is well-placed

**Tests should be done to basic Structure and Replicated with the Objects needed!**

---

##US401 <a name="us401"></a>
###Analysis
![US401-SSD](docs/Sprint4/US401/US401-SSD.svg)
![US401-MD](docs/Sprint4/US401/US401-DM.svg)
###Design
![US401-SD](docs/Sprint4/US401/US401-SD.svg)
![US401-CD](docs/Sprint4/US401/US401-CD.svg)
###Implementation

To verify which ports have great centrality, I need to verify certain conditions:
* We need to create an algorithm that from a given graph can build the shortest path of them all (Floyd-Warshall)
* We need to get the N number of vertices that present the greater centrality (have bigger outgoing edges), which are Ports



####Test Description

* Verify if algorithm creates the shortest Path of all graph.
* Verify if i get the list with the vertices with greater centrality
* Verify that the vertices obtained are ports


---


##US402 <a name="us402"></a>
###Analysis
![US402-SSD](docs/Sprint4/US402/US402-SSD.svg)
![US402-MD](docs/Sprint4/US402/US402-MD.svg)
###Design
![US402-SD](docs/Sprint4/US402/US402-SD.svg)
![US402-CD](docs/Sprint4/US402/US402-CD.svg)
###Implementation

* It's necessary to verify if locals are in the graph.
* It's necessary to get the shortest path of graph - FloydWarshall
* After getting the shortest path - Verify if path goes only through ports (maritime), only through cities (land) or both.

####Test Description


##US418 <a name="us418"></a>
###Analysis
![US418-SSD](docs/Sprint4/US418/US418-SSD.svg)
![US418-MD](docs/Sprint4/US418/US418-MD.svg)
###Design
![US418-SD](docs/Sprint4/US418/US418-SD.svg)
![US418-CD](docs/Sprint4/US418/US418-CD.svg)
###Implementation

####Test Description

##US420 <a name="us420"></a>
###Analysis
![US420-SSD](docs/Sprint4/US420/US420-SSD.svg)
![US420-MD](docs/Sprint4/US420/US420-MD.svg)
###Design
![US420-SD](docs/Sprint4/US420/US420-SD.svg)
![US420-CD](docs/Sprint4/US420/US420-CD.svg)
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