# SO-tarea2
Integrantes:
><p>-Harold Caballero; 201773602-k</p>
><p>-Guillermo Vasquez; 201773552-k</p>

<h3>Problema 1</h3>
Para compilar el programa llamar al comando 'make' o 'make compile'; para ejecutarlo 'make run'; para eliminar los archivos y carpetas creadas durante la compilacion ejecutar 'make clean'.

<h3>Problema 2</h3>
Para ejecutar el problema 2 escribir el comando 'make run1', luego se pedirá ingresar el arreglo y para esto se debe escribir números enteros separados por un espacio N N1 N2 ... Nn.  
Este problema se abordo con la idea del algoritmo mergesort, separando en 2 arreglos el arreglo original y aplicando mergesort en estos dos nuevos subproblemas, es en lo anterior donde se utilizan principalmente los threads, con el fin de calcular los 2 mergesort en paralelo y disminuir el tiempo de ejecución dado que estos dos subproblemas no están relacionados entre sí.

<h2>Nota</h2>
- Los comandos deben ser ejecutados desde la carpeta raiz.
- Con el comando compilar ya se están compilando los dos problemas.
- Las clases se crean en el directorio classes. El archivo 'funciones.txt' debe estar en la carpeta raiz.
