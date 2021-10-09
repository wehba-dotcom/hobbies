
const tabs= document.querySelectorAll("[data-tab-target]");
const tabcontens= document.querySelectorAll("[data-tab-content]")
tabs.forEach(tab => {
    tab.addEventListener("click",() => {
        const target= document.querySelector(tab.dataset.tabTarget)
        tabcontens.forEach(tabcontent =>{
            tabcontent.classList.remove("active")
        })
        tabs.forEach(tab =>{
            tab.classList.remove("active")
        })
        tab.classList.add("active")
        target.classList.add("active")
    })
});

async function getAllPerson() {
    var url1="https://www.test.alltoone.dk/allinone/api/person/all";
    var url2="http://localhost:8080/hobbies/api/person/all";
    var response = await fetch(url2);
    var data = await response.json();
    let personTable = document.getElementById("searsh")
    const tabelArray = data.map(person=>
        `<tr>
            <td>${person.id}</td>
            <td>${person.email}</td>
            <td>${person.firstName}</td> 
            <td>${person.lastName}</td>
         </tr>`)
    personTable.innerHTML = tabelArray.join('');
}
var persondel=document.getElementById("hint");

persondel.addEventListener("click",(event)=>{
    event.preventDefault();
    getAllPerson();
});
async function getAllHoppy() {
    var url1="https://www.test.alltoone.dk/allinone/api/hoppy/all";
    var url2="http://localhost:8080/hobbies/api/hoppy/all";
    var response = await fetch(url2);
    var data1 = await response.json();
    let hoppyTable = document.getElementById("searsh1")
    const tabelArray1 = data1.map(hoppy=>
        `<tr>
            <td>${hoppy.id}</td>
            <td>${hoppy.name}</td>
            <td>${hoppy.description}</td> 
         </tr>`)
 hoppyTable.innerHTML = tabelArray1.join('');
}
var hoppydel=document.getElementById("hint1");
hoppydel.addEventListener("click",ev =>{
    ev.preventDefault();
    getAllHoppy();
} )

async function addPerson() {

    let options = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
           email: document.getElementById("email").value,
            firstName: document.getElementById("fname").value,
            lastName: document.getElementById("lname").value
        })
    }
    fetch("http://localhost:8080/hobbies/api/person/add",options);
}
var persondel1=document.getElementById("add");
persondel1.addEventListener("click",(event)=>{
    event.preventDefault();
    addPerson();
});
persondel.addEventListener("click",(event)=>{
    event.preventDefault();
    getAllPerson();
});
async function addHoppy() {

    let options = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name: document.getElementById("name").value,
          description: document.getElementById("description").value,
        })
    }
    fetch("http://localhost:8080/hobbies/api/hoppy/add",options);
}
let hoppydel1= document.getElementById("add1");
hoppydel1.addEventListener("click",(ev)=>{
    ev.preventDefault();
    addHoppy();
})
async function getAllAddress() {
    var url1="https://www.test.alltoone.dk/allinone/api/address/all";
    var url2="http://localhost:8080/hobbies/api/address/all";
    var response = await fetch(url2);
    var data3 = await response.json();
    let addressTable = document.getElementById("searsh3")
    const tabelArray3 = data3.map(address=>
        `<tr>
            <td>${address.id}</td>
            <td>${address.street}</td>
            <td>${address.hoseNumber}</td> 
         </tr>`)
    addressTable.innerHTML = tabelArray3.join('');
}
var addressdel=document.getElementById("hint3");
addressdel.addEventListener("click",(ev) =>{
    ev.preventDefault();
    getAllAddress();
} )

async function addAddress() {

    let options = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            street: document.getElementById("street").value,
            hoseNumber: document.getElementById("hoseNumber").value,

        })
    }
    fetch("http://localhost:8080/hobbies/api/address/add",options);
}
var addressdel1=document.getElementById("add3");
addressdel1.addEventListener("click",(event)=>{
    event.preventDefault();
    addAddress();
});
