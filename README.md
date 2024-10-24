# Práctica 04 - Componentes de paleta Buttons

**Instrucciones**: Desarrolle una aplicación móvil en plataforma Android, utilizando el paradigma orientado a objetos y arreglos.

En la parte de diseño debe contemplar lo siguiente:  
* Agregar componentes como cajas de texto, botones, botones varios (Chip, ToggleButton o FloatingActionButton, al menos utilizar de dos de distintos  tipos) y etiquetas, que cumplan con la información definida en la clase (descrita líneas abajo).
* Especificar separación entre componentes y margen del tamaño de la pantalla (vista blue print).
* Definir diseño personalizado para cualquiera de los tipos de componentes a utilizar (Chip, ToggleButton o FloatingActionButton)
* Utilizar etiquetas definidas en el archivo Strings.xml

En la funcionalidad de cubrir lo siguiente:  
* Definir la clase **RopaDeportiva** que contiene los atributos <u>código</u>, <u>marca</u>, <u>modelo</u>, **talla** (chica, mediana, grande - para botones de opción), combinación de **colores** (negro, blanco, azul, rojo, naranja - para casillas de verificación) y <u>costo</u>.
* Declarar un **arreglo de objetos** de la clase anterior, con tamaño de 15.
* Programar la funcionalidad de cuatro botones: <u>Registrar</u>, <u>Buscar</u>, <u>Eliminar</u>, <u>Limpiar</u>, que a continuación se describe su actuar.

El <u>botón Registrar</u> debe colocar los datos de la clase en una posición dentro del arreglo de objetos. Se debe validar que exista espacio dentro del arreglo. Utilice mensajes para informar al usuario del registro o la falta de espacio. Cuando concluya el registro, deben limpiarse los componentes (volver al estado inicial).

El <u>botón Buscar</u> debe tomar el valor escrito en código y con ello buscar dentro del arreglo de objetos la información. Si existe debe mostrar la información dentro del componente correspondiente. En caso de no existir enviar un mensaje al usuario informativo.

El <u>botón Eliminar</u> debe borrar la información de la posición del arreglo de objetos donde fue localizada la información, enviado un mensaje informativo al usuario. Es deseable que si existe información en las posiciones a la derecha del registro a borrar, se recorran para evitar dejar huecos en el arreglo. En caso de intentar eliminar sin haber buscado la información, mostrar un mensaje de advertencia.

El <u>botón Limpiar</u> debe borrar el contenido de los componentes caja de texto y etiqueta, dejando todo en un estado inicial.
