
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
                    console.log(article.datePost);
                    //alert(article.usuario.username);
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
                    let userContainer = document.createElement('div');
                    userContainer.classList.add('users-container');

                    article.usuarios.forEach(usuario => {
                        let userElement = document.createElement('h5');
                        userElement.textContent = "Usuario: " + usuario.username;
                        userContainer.appendChild(userElement);
                    });
                    let comCont = document.createElement('div');
                    comCont.classList.add('commen-container');
                    article.comments.forEach(comment => {
                        let commElement = document.createElement('p');
                        commElement.textContent = "Comentario: " + comment.mensaje + "\n" + "Usuario: " + comment.usuario.username;
                        comCont.appendChild(commElement);
                    });
                    let descriptionElement = document.createElement('p');
                    descriptionElement.textContent = "Description: " + article.description;
                    // Crear un elemento para la imagen del artículo
                    let imageElement = document.createElement('img');
                    imageElement.src = article.image;
                    imageElement.classList.add('article-image'); // Añadir una clase a la imagen
                    // Agregar los elementos al contenedor de información
                    infoContainer.appendChild(titleElement);
                    infoContainer.appendChild(dateElement);
                    infoContainer.appendChild(userContainer);
                    infoContainer.appendChild(descriptionElement);
                    // Agregar la imagen al div del artículo
                    articleDiv.appendChild(imageElement);
                    // Agregar el contenedor de información y el div del artículo al contenedor principal
                    let reaccionesContainer = document.createElement('div');
                    reaccionesContainer.classList.add('reactions-container');
                    let reaccionDiv = document.createElement('p');
                    reaccionDiv.textContent = `Like: ${article.like}, dislake: ${article.dislike}`;
                    infoContainer.appendChild(reaccionDiv);
                    articleContainer.appendChild(infoContainer);
                    articleContainer.appendChild(articleDiv);
                    let like = document.createElement('i');
                    like.onclick = function () {
                        let valor = 1;
                        let titulo = article.title;
                        reacion(titulo, valor);
                    };
                    like.classList.add('fa', 'fa-thumbs-up');
                    let dislike = document.createElement('i');
                    dislike.onclick = function () {
                        let valor = 2;
                        let titulo = article.title;
                        reacion(titulo, valor);
                    };
                    dislike.classList.add('fa', 'fa-thumbs-down');
                    //reaccionesContainer.appendChild(reaccionDiv);
                    reaccionesContainer.appendChild(like);
                    reaccionesContainer.appendChild(dislike);
                    let secCom = document.createElement('div');
                    secCom.classList.add('commen-container');
                    let inputCom = document.createElement('input');
                    inputCom.classList.add('inpCom');
                    inputCom.id = "txtMsj";
                    secCom.appendChild(inputCom);
                    let btnCom = document.createElement('button');
                    btnCom.textContent = 'Enviar';
                    btnCom.onclick = function () {
                        let mensaje = inputCom.value;
                        let titulo = article.title;
                        inserCom(mensaje, titulo);
                    };
                    btnCom.classList.add('btnCom');
                    secCom.appendChild(btnCom);

                    articleContainer.appendChild(comCont);
                    articleContainer.appendChild(secCom);
                    articleContainer.appendChild(reaccionesContainer);
                    // Obtener el elemento donde se agregarán los artículos y agregar el contenedor principal del artículo
                    document.getElementById('arts').appendChild(articleContainer);
                });
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
}


function insertarArticle() {
    let title = document.getElementById("txtTitle").value;
    let fecha = document.getElementById("txtDate").value;
    let descri = document.getElementById("txtDesc").value;

    // Leer la imagen seleccionada por el usuario
    let input = document.getElementById("photo");
    let file = input.files[0]; // Obtener el primer archivo seleccionado

    if (file) {
        let reader = new FileReader();
        reader.readAsDataURL(file); // Leer el contenido del archivo como una URL de datos
        reader.onload = function () {
            let imageCode = reader.result; // Obtener el código de la imagen
            // Crear el objeto de parámetros con el código de la imagen
            let params = {
                "title": title,
                "datePost": fecha,
                "description": descri,
                "image": imageCode, // Código de la imagen en lugar de la URL
                "like": 0,
                "dislike": 0,
                "comments": [],
                "usuario": [
                    {
                        "name": "mati"
                    }
                ]
            };

            console.log(params);
            if (parseInt(localStorage.getItem("token")) === 1) {
                let url = "http://localhost:8080/MONGO/api/article/insert";
                fetch(url, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(params)
                })
                        .then(response => response.json())
                        .then(data => {
                            //alert(JSON.stringify(data));
                        })
                        .catch(error => {
                            console.error("Error al enviar la solicitud:", error);
                        });

            } else {
                alert("FIRST LOG IN PLEASE");
            }
            clean();
        };
    } else {
        console.error("No se seleccionó ninguna imagen.");
    }
}
function inserCom(mensaje, titulo) {
    //alert (mensaje+"+"+titulo);
    if (mensaje && typeof mensaje === 'string') {
        // Eliminar espacios en blanco al inicio y al final del mensaje
        mensaje = mensaje.trim();


        let params = {
            "title": titulo,
            "comments": [
                {"mensaje": mensaje,
                    "usuarioComenta": localStorage.getItem("user")
                }
            ]
        };

        console.log(params);
        let token = localStorage.getItem("token");
        //alert("lalas "+token.length);
        if (token.length > 5) {
            //alert("caca");
            
            console.log("token" + token);
            let url = "http://localhost:8080/MONGO/api/article/updateCom";
            fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(params)
            })
                    .then(response => response.json())
                    .then(data => {
                        //alert(JSON.stringify(data));
                    })
                    .catch(error => {
                        console.error("Error al enviar la solicitud:", error);
                    });
            cargaArtc();
        } else {
             console.log("token" + token);
            alert("FIRST LOG IN PLEASE");
        }
        cleanCom();
    } else {
        // El mensaje está indefinido, null o no es una cadena
        alert("Llena el campo de mensaje");
        return; // Salir de la función
    }
}
function reacion(titulo, valor) {
    //hacer una condicion para saber a que icono le dan click 
    //alert(titulo+"/"+valor);
    let params = {};
    if (valor === 1) {
        params = {
            "title": titulo,
            "like": 1,
            "dislike": 0
        };
    } else {
        params = {
            "title": titulo,
            "like": 0,
            "dislike": 1
        };
    }
    //console.log(params);
    if (parseInt(localStorage.getItem("token")) > 0) {
        let url = "http://localhost:8080/MONGO/api/article/reacciones";
        fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(params)
        })
                .then(response => response.json())
                .then(data => {
                    //alert(JSON.stringify(data));
                })
                .catch(error => {
                    console.error("Error al enviar la solicitud:", error);
                });
        cargaArtc();
    } else {
        alert("FIRST LOG IN PLEASE");
    }
}


function logIn() {
    let username = document.getElementById("txtUser").value;
    let password = document.getElementById("txtPassw").value;
    let params = {
        "username": username,
        "password": password
    };
    //console.log(params);
    let url = "http://localhost:8080/MONGO/api/article/logIn";
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(params)
    })
            .then(response => response.json())
            .then(data => {
                //alert(JSON.stringify(data));
                if (data.success) {
                    alert("Login Succesful");
                    alertin();
                    console.log("logeado");
                    localStorage.setItem("token", 123456789);
                } else {
                    alert("USERNAME OR PASSWORD DOESNT EXIST \n CREATE AN ACCOUNT");
                    cargaSignIn();
                    console.log("no logeado");
                    localStorage.setItem("token", 0);
                }
            })
            .catch(error => {
                alert("Error al enviar la solicitud:", error);
            });
}
function logInv2() {
    let username = document.getElementById("txtUser").value;
    let email = document.getElementById("txtEmail").value;
    let password = document.getElementById("txtPassw").value;
    let params = {
        "username": username,
        "email": email,
        "password": password
    };

    let url = "http://localhost:8080/MONGO/api/article/logInv2";
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(params)
    })
            .then(response => response.json())
            .then(data => {
                if (data && Object.keys(data.success).length > 0) {
                    console.log(data);
                    alert("Login Successful \n Welcome: " + username);
                    console.log("Logged in");
                    localStorage.setItem("token", 12345789);
                    localStorage.setItem("user", username);
                    alertin();
                } else {
                    alert("USERNAME OR PASSWORD DOESN'T EXIST \n CREATE AN ACCOUNT");
                    //cargaSignIn();
                    console.log("Not logged in");
                    localStorage.setItem("token", 0);
                    cleanLog();
                }
            })
            .catch(error => {
                alert("Error al enviar la solicitud:", error);
            });
}

function singIn() {
    let username = document.getElementById("txtUserSi").value;
    let email = document.getElementById("txtEmailSi").value;
    let password = document.getElementById("txtPasswSi").value;
    let params = {
        "username": username,
        "email": email,
        "password": password
    };
    //console.log(params);
    let url = "http://localhost:8080/MONGO/api/article/singIn";
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(params)
    })
            .then(response => response.json())
            .then(data => {
                //alert(JSON.stringify(data));
                if (data.success) {
                    alert("Sign Succesful \n You have an account");
                    alertin();
                    console.log("Sign in succes");
                    localStorage.setItem("token", 123456789);
                } else {
                    alert("USERNAME HAS ALREADY EXIST \n TRY ANOTHERONE");
                    console.log("SIGN IN UNSUCCESFULE");
                    localStorage.setItem("token", 0);
                }
            })
            .catch(error => {
                alert("Error al enviar la solicitud:", error);
            });
}


function cleanLog() {
    document.getElementById("txtUser").value = "";
    document.getElementById("txtPassw").value = "";
}
;
function clean() {
    document.getElementById("txtTitle").value = "";
    document.getElementById("txtDate").value = "";
    document.getElementById("photo").value = "";
    document.getElementById("txtDesc").value = "";
}


function cleanCom() {
    document.getElementById("txtMsj").value = "";
}
function cargaLogOut() {
    localStorage.setItem("token", 0);
    localStorage.removeItem("user");
    alert("Log out successful");
}
