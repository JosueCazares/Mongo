
async function cargaLogIn() {
    const response = await fetch("html/login.html");
    const html = await response.text();
    document.getElementById('main').innerHTML = html;
}
async function cargaSignIn() {
    const response = await fetch("html/signin.html");
    const html = await response.text();
    document.getElementById('main').innerHTML = html;
}
async function cargaForm() {
    const response = await fetch("html/formArticulo.html");
    const html = await response.text();
    document.getElementById('main').innerHTML = html;
    getAllArt();
}
async function cargaArtc() {
    const response = await fetch("html/articulos.html");
    const html = await response.text();
    document.getElementById('main').innerHTML = html;
    getAllArt();
}

async function alertin() {
    const response = await fetch("index.html");
    const html = await response.text();
    document.getElementById('cont').innerHTML = html;
    console.log("a");
}

//let article;

//function getAll() {
//
//    let ruta = "http://localhost:8080/MONGO/api/article/getAllArt";
//    fetch(ruta,
//            {method: "GET",
//                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
//                body: new URLSearchParams(param)
//            }
//    ).then(response => response.json())
//            .then(response => {
//                console.log(response);


//                console.log(response);
//
//                empleados = response;
//                let datos = "";
//                let i = 0;
//                empleados.forEach((empleado) => {
//                    let dato1 = empleado.idEmpleado + "<br>"
//                            + empleado.codigo + "<br>"
//                            + empleado.fechaIngreso + "<br>"
//                            + empleado.puesto + "<br>"
//                            + empleado.salarioBruto + "<br>";
//                    //+ empleado.activo+"<br>";
//                    let dato2 = empleado.persona.nombre + "<br>"
//                            + empleado.persona.apPat + "<br>"
//                            + empleado.persona.apMat + "<br>";
//                    let dato3 = empleado.persona.genero + "<br>"
//                            + empleado.persona.fechaNac + "<br>"
//                            + empleado.persona.rfc + "<br>"
//                            + empleado.persona.curp + "<br>";
//                    let dato4 = empleado.persona.domicilio + "<br>"
//                            + empleado.persona.cpPersona + "<br>"
//                            + empleado.persona.ciudad + "<br>"
//                            + empleado.persona.estado + "<br>"
//                            + empleado.persona.telefono + "<br>";
//                    let dato5 = empleado.user.id + "<br>"
//                            + empleado.user.usuario + "<br>"
//                    let dato6 = empleado.sucursal.nombre + "<br>"
//                            + empleado.sucursal.domicilio + "<br>"
//                            + empleado.sucursal.colonia + "<br>"
//                            + empleado.sucursal.codigoPostal + "<br>"
//                            + empleado.sucursal.ciudad + "<br>"
//                            + empleado.sucursal.estado + "<br>"
//                            + empleado.sucursal.telefono + "<br>";
//                    datos += "<tr>";
//                    datos += "<td>" + dato1 + "</td>";
//                    datos += "<td>" + dato2 + "</td>";
//                    datos += "<td>" + dato3 + "</td>";
//                    datos += "<td>" + dato4 + "</td>";
//                    datos += "<td>" + dato5 + "</td>";
//                    datos += "<td>" + dato6 + "</td>";
//                    if (empleado.activo === 0) {
//                        datos += "<td> <button type='button' class='btn btn-light' onclick='test(" + empleado.persona.nombre + ")'> Modificar</button> </td>";
//                        datos += "<td> <button type='button' class='btn btn-success' onclick='activarEmpl(" + empleado.idEmpleado + ")'> Activar</button> </td>";
//
//                    } else if (empleado.activo === 1) {
//
//                        datos += "<td> <button type='button' class='buton' onclick='test(" + i + ")'> Modificar</button> </td>";
//                        datos += "<td> <button type='button' class='buton' onclick='eliminarEmpl(" + empleado.idEmpleado + ")'> Eliminar</button> </td>";
//                    }
//                    i++;
//                });
//                document.getElementById("tbEmpleados").innerHTML = datos;

//            });
//}

//function pr1get() {
//    let ruta = "http://localhost:8080/MONGO/api/article/getAllArt";
//    fetch(ruta, {
//        method: "GET",
//        headers: {
//            'Content-Type': 'application/json' // Cambiado a 'application/json' ya que no estás enviando datos de formulario
//        }
//    }).then(response => response.json())
//            .then(response => {
//                console.log(response);
//                console.log(response[0].title);
//                articles = response;
////          let data="";
//                let i = 0;
//                articles.forEach((article) => {
//                    let title = article.title;
//                    let descr = article.description;
//                    let img = article.image;
//                    let datePost = article.datePost;
//                    let user = article.usuario;
//                    //let commet = article hacer otro cilo para los comentarios y asi poder generar varios en el articulo
////              alert(title);
//                    let cont = document.createElement("div class='art'");
//                });
//            })
//            .catch(error => {
//                console.error('Error fetching data:', error);
//            });
//}
//function getAllArt1() {
//    let ruta = "http://localhost:8080/MONGO/api/article/getAllArt";
//    fetch(ruta, {
//        method: "GET",
//        headers: {
//            'Content-Type': 'application/json'
//        }
//    })
//            .then(response => response.json())
//            .then(articles => {
//                // Crear un contenedor para los artículos
//                let container = document.createElement('div');
//                container.classList.add('art-conta'); // Añadir una clase al contenedor
//
//                // Iterar sobre cada artículo en la respuesta
//                articles.forEach(article => {
//                    // Crear un div para cada artículo
//                    let articleDiv = document.createElement('div');
//                    articleDiv.classList.add('article'); // Añadir una clase al div del artículo
//
//                    // Crear elementos para mostrar los datos del artículo
//                    let titleElement = document.createElement('h2');
//                    titleElement.textContent = article.title;
//
//                    let dateElement = document.createElement('p');
//                    dateElement.textContent = "Date: " + article.datePost;
//
//                    let descriptionElement = document.createElement('p');
//                    descriptionElement.textContent = "Description: " + article.description;
//
//                    // Agregar los elementos al div del artículo
//                    articleDiv.appendChild(titleElement);
//                    articleDiv.appendChild(dateElement);
//                    articleDiv.appendChild(descriptionElement);
//
//                    // Agregar el div del artículo al contenedor
//                    container.appendChild(articleDiv);
//                });
//// Obtener el elemento main y agregar el contenedor de artículos
//                document.getElementById('arts').appendChild(container);
//
//                // Obtener el elemento body y agregar el contenedor de artículos
////        document.body.appendChild(container);
//            })
//            .catch(error => {
//                console.error('Error fetching data:', error);
//            });
//}
//function getAllArt1() {
//    let ruta = "http://localhost:8080/MONGO/api/article/getAllArt";
//    fetch(ruta, {
//        method: "GET",
//        headers: {
//            'Content-Type': 'application/json'
//        }
//    })
//            .then(response => response.json())
//            .then(articles => {
//                // Iterar sobre cada artículo en la respuesta
//                articles.forEach(article => {
//                    //console.log(article);
//                    // Crear un contenedor para el artículo
//                    let articleContainer = document.createElement('div');
//                    articleContainer.classList.add('article-container'); // Añadir una clase al contenedor
//                    let infocont = document.createElement('div');
//                    infocont.classList.add('artc-inf'); // Añadir una clase al contenedor
//                    // Crear un div para el artículo
//                    let articleDiv = document.createElement('div');
//                    articleDiv.classList.add('article'); // Añadir una clase al div del artículo
//                    let infoDiv = document.createElement('div');
//                    infoDiv.classList.add('article-i'); // Añadir una clase al div del artículo
//                    // Crear elementos para mostrar los datos del artículo
//                    let titleElement = document.createElement('h1');
//                    titleElement.classList.add("artc-inf");
//                    titleElement.textContent = article.title;
//                    let dateElement = document.createElement('h5');
//                    dateElement.classList.add("artc-inf");
//                    dateElement.textContent = "Date post: " + article.datePost;
//                    let descriptionElement = document.createElement('p');
//                    descriptionElement.classList.add("artc-inf");
//                    descriptionElement.textContent = "Description: " + article.description;
//                    let imag = document.createElement("div");
//                    imag.textContent = article.image;
//                    imag.classList.add("img-art");
//                    // Agregar los elementos al div del artículo
//                    infoDiv.appendChild(titleElement);
//                    infoDiv.appendChild(dateElement);
//                    infoDiv.appendChild(descriptionElement);
//                    articleDiv.appendChild(imag);
//                    // Agregar el div del artículo al contenedor del artículo
//                    infocont.appendChild(infoDiv);
//                    articleContainer.appendChild(articleDiv);
//                    
//                    // Obtener el elemento body y agregar el contenedor de artículo
//                    document.getElementById('arts').appendChild(articleContainer);
//                });
//            })
//            .catch(error => {
//                console.error('Error fetching data:', error);
//            });
//}
function getAllArt() {
    let ruta = "http://localhost:8080/MONGO/api/article/getAllArt";
    fetch(ruta, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(articles => {
        // Iterar sobre cada artículo en la respuesta
        articles.forEach(article => {
            // Crear un contenedor principal para el artículo
            let articleContainer = document.createElement('div');
            articleContainer.classList.add('article-container'); // Añadir una clase al contenedor principal

            // Crear un contenedor para la información del artículo (título, fecha, descripción)
            let infoContainer = document.createElement('div');
            infoContainer.classList.add('artc-inf'); // Añadir una clase al contenedor de información

            // Crear un div para el artículo (donde irá la imagen)
            let articleDiv = document.createElement('div');
            articleDiv.classList.add('img-art'); // Añadir una clase al div del artículo

            // Crear elementos para mostrar los datos del artículo
            let titleElement = document.createElement('h1');
            titleElement.textContent = article.title;

            let dateElement = document.createElement('h5');
            dateElement.textContent = "Date post: " + article.datePost;

            let descriptionElement = document.createElement('p');
            descriptionElement.textContent = "Description: " + article.description;

            // Crear un elemento para la imagen del artículo
            let imageElement = document.createElement('div');
            imageElement.textContent = article.image;
            imageElement.classList.add('article-image'); // Añadir una clase a la imagen

            // Agregar los elementos al contenedor de información
            infoContainer.appendChild(titleElement);
            infoContainer.appendChild(dateElement);
            infoContainer.appendChild(descriptionElement);

            // Agregar la imagen al div del artículo
            articleDiv.appendChild(imageElement);

            // Agregar el contenedor de información y el div del artículo al contenedor principal
            articleContainer.appendChild(infoContainer);
            articleContainer.appendChild(articleDiv);

            // Obtener el elemento donde se agregarán los artículos y agregar el contenedor principal del artículo
            document.getElementById('arts').appendChild(articleContainer);
        });
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });
}
