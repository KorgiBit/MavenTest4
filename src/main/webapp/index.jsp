<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <title>Add Car</title>
</head>
<body>
    <form id="carForm" onsubmit="submitForm(event)">
    Car Mark : <input type="text" id="carMarkName" name="carMarkName"><br>
    Car Model : <input type="text" id="carModelName" name="carModelName"><br>
    Year : <input type="text" id="year" name="year"><br>
    Price : <input type="text" id="price" name="price"><br>
    <input type="submit" value= "Add Car">
    </form>
    <script src="js/script.js"></script>
</body>
</html>
