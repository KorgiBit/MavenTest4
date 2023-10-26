function submitForm(event) {
  event.preventDefault()
  
  let carMarkName = document.getElementById("carMarkName").value
  let carModelName = document.getElementById("carModelName").value
  let year = document.getElementById("year").value
  let price = document.getElementById("price").value

  let data = {
    carMarkName: carMarkName,
    carModelName: carModelName,
    year: year,
    price: parseInt(price)
  }
  fetch('/MavenTest4-1.0/addCar',{
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(data)
  }
  ).then( response => response.text())
  .then(data => alert(data))
  .catch(error => alert('Error: ' + error))
}
