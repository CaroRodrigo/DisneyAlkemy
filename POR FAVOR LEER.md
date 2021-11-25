# DisneyAlkemy

Hola equipo de Alkemy les comparto mi proyecto Disney

Video del proyecto funcionando : https://youtu.be/6EGfM5sBMIg

Explicacion
Usuario no registrado: solo puede registrarse e iniciar secion

Usuario Registrado: puede ver todas las peliculas / series 
y personajes cargados en base de datos

Administrador: puede hacer todo lo anterior e ingresar al panel del control
donde podra crear Calificaciones y Generos (Selectores de peliculas)
tambien solo seran los unicos autorizados para crear:
 calificaciones, generos, personajes y peliculas

si bien los botones los deje ocultos segun el rol que tenga... 
cualquiera puede ingresar desde las URL (lo deje asi para facilitar la revicion)

las rutas disponibles son:

Registro  y login (solo usuario sin registro): 
/auth/register 
/auth/login

Personajes y peliculas con detalles (Usuarios y admin)
/movies/list-all
/characters/list-all

Panel de control y rutas de acceso solo para el ADMIN:
/movies/list
/movies/form

/characters/list
/characters/form

/qualification/list
/qualification/form

/gender/list
/gender/form


Rutas maestras (Nadie tiene acceso a ellas solo escribiendo la URL)

/movies
/characters
/qualification
/gender