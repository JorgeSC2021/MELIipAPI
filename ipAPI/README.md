# MELIipAPI
Servicio de consulta de información de peticiones

# EJECUTAR EN DOCKER
cd ./ipApi
docker build -t meli/ipapi .
docker run -p 8080:8080 meli/ipapi

# DECISIONES
1- Se utiliza Spring Boot, Maven y Java 21
2- Se añade un atributo a la salida del api de Country, con el fin de mantener el consumo de ésta y no tener un llamado no usado esto también se hace porque esta api tiene
   una salida de información diferente a las otras dos y presentar como se captura la salida de este servicio.
3- Se calcula con fórmula de 3 la conversión a dólares ya que el api de monedas en su versión gratuita solo devuelve como base siempre el EURO.
4- Se decidió usar la fórmula del sitio web https://es.martech.zone/calculate-great-circle-distance/#:~:text=La%20forma%20sencilla%20de%20calcular,se%20conoce%20como%20distancia%20euclidiana.
   ya que no se contaba con la experticie matemática para hacer el cálculo o uso de un api conocida
5- Se utilizó una base de datos en memoria H2, para persistir los consumos del api de muestra de información, con el fin de calcular los estadísticos de las preguntas de mínimo, máximo y promedio 
   y evitar tener más despliegues de componentes
6- Se manejan las variables en el properpeties para centralizar los valores flexibles del API

# RECOMENDACIONES
1- Los servicios de Country y fixer se deben modificar a asincrónicos e implementar la clase CompletableFuture para cambiar el comportamiento del API y mejorar el tiempo de delay, sin embargo,
   hay que tener en cuenta que estos servicios externos cuenten con las capacidades de manejar transaccionalidad alta para poder soportar esa alta concurrencia, por ende, deben ser analizadas 
   las capacidades de estos proveedores
2- Se debe tener un motor NoSQL que lleve la estadística de la Base de Datos en memoria H2, ya que mitigaría el riesgo de perdida de información del H2, o la otra alternativa es introducir un servicio
   que este rescatando la información de H2 cada cierto tiempo y haga mantenimiento sobre lo captura liberando espacio en memoria de H2, y las consultas se modifiquen apuntando a la base de datos NoSQL