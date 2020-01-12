import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.lang.Integer;
class Hilos implements Runnable{
  //clase Hilos para implementar el metodo run()
  private int[] arr;  //Arreglo original
  private int inicio; //Posicion inicial en la cual se realiza el mergesort
  private int fin;    //Posicion final en la cual se realiza el mergesort
  private int[] res;  //Arreglo que guardara la respuesta final

  public Hilos(int[] arr,int inicio, int fin){
    this.arr = arr;
    this.inicio = inicio;
    this.fin = fin;
  }
  //Funcion merge para unir los dos subarreglos (Mayor a menor)
  public void merge(int[] res1,int[] res2){
    int tam1 = res1.length;
    int tam2 = res2.length;
    int[] new_res = new int[tam1+tam2];
    int i,j;
    for (i=0,j=0; i<tam1 && j<tam2 ;) {
      if (res1[i]>res2[j]) {
        new_res[i+j]=res1[i];
        i++;
      } else if(res2[j]>res1[i]){
        new_res[i+j]=res2[j];
        j++;
      } else{
        new_res[i+j]=res1[i];
        i++;
        new_res[i+j]=res2[j];
        j++;
      }
    }
    for (;i<tam1 ;i++ ) {
      new_res[i+j]=res1[i];
    }
    for (;j<tam2 ;j++ ) {
      new_res[i+j]=res2[j];
    }
    this.res = new_res;
  }

  public int[] getRes(){
    return this.res;
  }
  //Tarea que realizara el thread, basicamente realiza el algoritmo mergesort()
  public void run(){
    if (this.inicio < this.fin) {
      Hilos hilo1 = new Hilos(this.arr,this.inicio,(this.fin + this.inicio)/2); //mergesort()
      Thread thread1 = new Thread(hilo1);
      thread1.start();
      Hilos hilo2 = new Hilos(this.arr,(this.fin + this.inicio)/2 + 1,this.fin); //mergesort()
      Thread thread2 = new Thread(hilo2);
      thread2.start();
      try{        //Espera a que terminen los dos threads para realizar el merge
        thread1.join();
        thread2.join();
      } catch(InterruptedException e) {}
      merge(hilo1.res,hilo2.res);
    } else if(this.inicio >= this.fin){
      int[] new_res = new int[1];
      new_res[0] = this.arr[this.fin];
      this.res = new_res;
    }
  }
}

public class Problema2 {
  public static void main(String[] args) {
    //Solicitud de arreglo
    System.out.println("Ingrese el arreglo de enteros con el siguiente formato N N1 N2 N3 ... Nn:");
    Scanner in = new Scanner(System.in);
    String linea = in.nextLine();
    List<String> c = Arrays.asList(linea.split(" "));
    int[] arr = new int[c.size()];
    //Conversion de Lista de strings a un Arreglo de enteros
    for (int i = 0; i < c.size() ;i++ ) {
      arr[i] = Integer.parseInt(c.get(i));
    }
    int tam = arr.length;
    Hilos res = new Hilos(arr,0,tam-1); //Se crea un hilo inicial para resolver el problema
    Thread thread = new Thread(res);
    thread.start();
    try{
      thread.join();
    } catch(InterruptedException e) {}
    System.out.println("Resultado:"+Arrays.toString(res.getRes()));
  }
}
