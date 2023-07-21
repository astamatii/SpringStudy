<h2>Test web messages using curl</h2>

<h3>POST via curl (windows)</h3>
<p><b>Positive:</b></p>
<pre>
curl -H "Content-Type: application/json" ^
</br>
-d "{\"name\": \"Fluffy\", \"email\": \"fluffy.@gmail.com\", \"dob\": \"1995-12-17\"}" ^
</br>
-X POST http://localhost:8080/api/v1/student
</br>
<pre>
<p><b>Negative (same email - IllegalStateException):</b></p>
<pre>
curl -H "Content-Type: application/json" ^
</br>
-d "{\"name\": \"Bunny\", \"email\": \"fluffy.@gmail.com\", \"dob\": \"1995-12-17\"}" ^
</br>
-X POST http://localhost:8080/api/v1/student
</br>
<pre>
</br>

<h3>DELETE via curl (windows)</h3>
<p><b>Positive:</b></p>
<pre>
curl -X DELETE http://localhost:8080/api/v1/student/1
</br>
<pre>
<p><b>Negative (inexistent student by ID - IllegalStateException):</b></p>
<pre>
curl -X DELETE http://localhost:8080/api/v1/student/4
</br>
<pre>
</br>

<h3>PUT via curl (windows)</h3>
<p><b>Positive:</b></p>
<pre>
curl -v -X PUT "http://localhost:8080/api/v1/student/1?name=Maria&email=maria@gmail.com"
</br>
<pre>
<p><b>Negative (same email - IllegalStateException):</b></p>
<pre>
curl -v -X PUT "http://localhost:8080/api/v1/student/1?name=Maria&email=maria@gmail.com"
</br>
<pre>
</br>

<h2>Tips</h2>
<h3>Create 2 instances of the web application on the server</h3>
<pre>
> springboot cd target
</br>
(moving to your map)
</br>
</br>
> target java -jar springbootfile-0.0.1-SNAPSHOT.jar  
</br>
(and then application is running with default port 8080)
</br>
</br>
> target java -jar springbootfile-0.0.1-SNAPSHOT.jar --server.port=8081 
</br>
(and then 2nd instance of application is running with port 8081)
</br>
</br>

</pre>
