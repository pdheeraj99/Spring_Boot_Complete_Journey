**OBJECT MAPPER**

**Scenario 1: Converting Java Objects to JSON**
=====================================================

### Situation

You have a Java object, and you want to convert it to a JSON string to send it over the network or store it in a file.

### Step-by-Step Code Explanation

#### Step 1: Create a Java Object
```java
Alien al = new Alien(1, "Dheeraj", "Earth");
```
Create a Java object of type `Alien` with `id=1`, `name="Dheeraj"`, and `planet="Earth"`.

#### Step 2: Create an ObjectMapper
```java
ObjectMapper mapper = new ObjectMapper();
```
Create an instance of `ObjectMapper`, which is used to convert the Java object to a JSON string.

#### Step 3: Convert the Java Object to a JSON String
```java
String json = mapper.writeValueAsString(al);
```
Use the `writeValueAsString()` method of the `ObjectMapper` to convert the `Alien` object to a JSON string.

#### Step 4: Print the JSON String
```java
System.out.println("JSON is " + json);
```
Print the resulting JSON string to the console.

### Example Code
```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Alien al = new Alien(1, "Dheeraj", "Earth");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(al);
        System.out.println("JSON is " + json);
    }
}
```
### Output
```
JSON is {"id":1,"name":"Dheeraj","planet":"Earth"}
```
---

**Scenario 2: Converting JSON to Java Objects**
=====================================================

### Situation

You receive a JSON string from a web service or a file, and you want to convert it to a Java object.

### Step-by-Step Code Explanation

#### Step 1: Create a JSON String
```java
String json = "{\"id\":1,\"name\":\"Dheeraj\",\"planet\":\"Earth\"}";
```
Create a JSON string that represents an `Alien` object.

#### Step 2: Create an ObjectMapper
```java
ObjectMapper mapper = new ObjectMapper();
```
Create an instance of `ObjectMapper`, which is used to convert the JSON string to a Java object.

#### Step 3: Convert the JSON String to a Java Object
```java
Alien al = mapper.readValue(json, Alien.class);
```
Use the `readValue()` method of the `ObjectMapper` to convert the JSON string to an `Alien` object.

#### Step 4: Print the Java Object
```java
System.out.println(al);
```
Print the resulting `Alien` object to the console.

### Example Code
```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\"id\":1,\"name\":\"Dheeraj\",\"planet\":\"Earth\"}";
        ObjectMapper mapper = new ObjectMapper();
        Alien al = mapper.readValue(json, Alien.class);
        System.out.println(al);
    }
}
```
### Output
```
Alien [id=1, name=Dheeraj, planet=Earth]
```
---

**Scenario 3: Customizing Serialization and Deserialization**
=============================================================

### Situation

You want to customize the way your Java objects are serialized or deserialized to/from JSON.

### Step-by-Step Code Explanation

#### Step 1: Add Annotations to the Java Object
```java
public class Alien {
    @JsonProperty("alien_id")
    private int id;
    // ...
}
```
Add the `@JsonProperty` annotation to the `id` field of the `Alien` class to customize the name of the JSON property.

#### Step 2: Create an ObjectMapper
```java
ObjectMapper mapper = new ObjectMapper();
```
Create an instance of `ObjectMapper`, which is used to convert the Java object to a JSON string.

#### Step 3: Convert the Java Object to a JSON String
```java
String json = mapper.writeValueAsString(al);
```
Use the `writeValueAsString()` method of the `ObjectMapper` to convert the `Alien` object to a JSON string.

#### Step 4: Print the JSON String
```java
System.out.println("JSON is " + json);
```
Print the resulting JSON string to the console.

### Example Code
```java
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Alien al = new Alien(1, "Dheeraj", "Earth");
        ObjectMapper mapper = new ObjectMapper();
       String json = mapper.writeValueAsString(al);
        System.out.println("JSON is " + json);
    }
}

JSON is {"alien_id":1,"name":"Dheeraj","planet":"Earth"}



Here is the rest of the explanation:

### Example Code
```java
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Alien al = new Alien(1, "Dheeraj", "Earth");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(al);
        System.out.println("JSON is " + json);
    }
}
```
### Output
```
JSON is {"alien_id":1,"name":"Dheeraj","planet":"Earth"}
```
---

**Scenario 4: Handling Complex Data Structures**
=====================================================

### Situation

You have complex data structures like lists, maps, or nested objects, and you want to serialize or deserialize them to/from JSON.

### Step-by-Step Code Explanation

#### Step 1: Create a Complex Data Structure
```java
List<Alien> aliens = Arrays.asList(new Alien(1, "Dheeraj", "Earth"), new Alien(2, "John", "Mars"));
```
Create a list of `Alien` objects.

#### Step 2: Create an ObjectMapper
```java
ObjectMapper mapper = new ObjectMapper();
```
Create an instance of `ObjectMapper`, which is used to convert the complex data structure to a JSON string.

#### Step 3: Convert the Complex Data Structure to a JSON String
```java
String json = mapper.writeValueAsString(aliens);
```
Use the `writeValueAsString()` method of the `ObjectMapper` to convert the list of `Alien` objects to a JSON string.

#### Step 4: Print the JSON String
```java
System.out.println("JSON is " + json);
```
Print the resulting JSON string to the console.

### Example Code
```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        List<Alien> aliens = Arrays.asList(new Alien(1, "Dheeraj", "Earth"), new Alien(2, "John", "Mars"));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(aliens);
        System.out.println("JSON is " + json);
    }
}
```
### Output
```
JSON is [{"id":1,"name":"Dheeraj","planet":"Earth"},{"id":2,"name":"John","planet":"Mars"}]
```
---

**Scenario 5: Handling Dates and Timestamps**
=============================================

### Situation

You have date or timestamp fields in your Java objects, and you want to serialize or deserialize them to/from JSON.

### Step-by-Step Code Explanation

#### Step 1: Create a Java Object with a Date Field
```java
public class Alien {
    private Date birthDate;
    // ...
}
```
Create a Java object with a `birthDate` field of type `Date`.

#### Step 2: Create an ObjectMapper
```java
ObjectMapper mapper = new ObjectMapper();
```
Create an instance of `ObjectMapper`, which is used to convert the Java object to a JSON string.

#### Step 3: Configure the ObjectMapper to Handle Dates
```java
mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
```
Configure the `ObjectMapper` to handle dates in the format "yyyy-MM-dd".

#### Step 4: Convert the Java Object to a JSON String
```java
String json = mapper.writeValueAsString(al);
```
Use the `writeValueAsString()` method of the `ObjectMapper` to convert the `Alien` object to a JSON string.

#### Step 5: Print the JSON String
```java
System.out.println("JSON is " + json);
```
Print the resulting JSON string to the console.

### Example Code
```java
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Alien al = new Alien(new Date());
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        String json = mapper.writeValueAsString(al);
        System.out.println("JSON is " + json);
    }
}
```
### Output
```
JSON is {"birthDate":"2023-03-15"}
```
I hope this helps! Let me know if you have any questions or need further clarification.