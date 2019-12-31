import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.File;


class Parser
{
    //responde a la pregunta de si el operador a tiene
    //mayor precedencia que el operador b
    private static boolean prec(Character a, Character b)
    {
        int va=0, vb=0;
        switch(a)
        {
            case '*':
                va = 3;
                break;
            case '/':
                va = 3;
                break;
            case '+':
                va = 2;
                break;
            case '-':
                va = 2;
        }
        switch(b)
        {
            case '*':
                vb = 3;
                break;
            case '/':
                vb = 3;
                break;
            case '+':
                vb = 2;
                break;
            case '-':
                vb = 2;
        }
        return va>vb;
    }
    
    //implementacion del algorithmo Shunting Yard
    //que convierte una expresión matemática en notación infija
    //a una en notación polaca inversa (RPN)s
    static Queue<Character> parse(String str)
    {
        Queue<Character> cola = new LinkedList<>();
        Stack<Character> pila = new Stack<>();
        str = str.toLowerCase();
        for(int i=0; i<str.length(); i++)
        {
            Character tok = str.charAt(i);
            if(Character.isLetter(tok))
            {
                if(tok != 'x')
                    i += 3;
                cola.add(tok);
            }
            else if(Character.isDigit(tok))
                cola.add(tok);
            else if(tok=='+'||tok=='-'||tok=='*'||tok=='/')
            {
                while(!pila.empty()&&prec(pila.peek(), tok))
                    cola.add(pila.pop());
                pila.push(tok);
            }
            else if(tok=='(')
                pila.push(tok);
            else if(tok==')')
            {
                while(!pila.empty()&&pila.peek()!='(')
                    cola.add(pila.pop());
                pila.pop();
            }
        }
        while(!pila.empty())
            cola.add(pila.pop());
        return cola;
    }
}


public class Problema1
{
    /*
    static float solve(Queue<Character> cola)
    {
        for(int i=0; i<cola.size(); i++)
        {

        }
    }
    */
    public static void main(String args[])
    {
        int n;
        try
        {
            File file = new File("../funciones.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine())
            {
                System.out.println(sc.nextLine());
            }
            sc.close();
        }
        catch(Exception e)
        {
            System.out.println("Archivo no encontrado");
            System.exit(1);
        }
    }
}