# Courses Management

* A simple Object-Oriented-Programming project in Java for students and courses management.

### Info
* It mimics a relational database with two entities (students and courses)
* Of course, data is volatile (no database is used)
* It supports basic operations upon these objects, by running the students/Run java file.
    * get all students
    * get all courses
    * get one student's courses
    * add, delete student
    * add, delete course
    * attach course to student
    * de-attach course from student

### Usage
Inside the root directory of the repo
* javac students/models/Course.java
* javac students/models/Student.java
* javac students/endpoints/Services.java
* javac students/endpoints/Controllers.java
* javac students/Run.java
<br />
**and finally** 
<br />
* java students.Run

### Files structure
* `students/Run.java`: <br />
    * the main class that remains up, receives the commands, passes them to `endpoints/Controller.java` and receives and shows the output
* `students/models`: <br/>
    * the two .java files for `creating` the Students and Courses `classes`
* `students/endpoints`: <br />
    * the two .java files for handling the user requests coming from the main Run class 
    * `Controllers.java`: handles the input-command and uses a dictionary to call the corresponding method of the `Services.java` file, providing the neccessary arguments
    * `Services.java`: contains the core methods that operate on the data and return the output

- - -

* Developer: Giannis Athanasiou
* Github Username: John-Atha
* Email: giannisj3@gmail.com
