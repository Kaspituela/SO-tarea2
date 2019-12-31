import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
    static Queue<String> parse(String str)
    {
        Queue<String> cola = new LinkedList<>();
        Stack<Character> pila = new Stack<>();
        str = str.toLowerCase();
        String numero = "";
        for(int i=0; i<str.length(); i++)
        {
            Character tok = str.charAt(i);
            if(Character.isLetter(tok))
            {
                if(tok != 'x')
                    i += 3;
                cola.add(String.valueOf(tok));
            }
            else if(Character.isDigit(tok))
            {
                int contador = 0;
                while(Character.isDigit(tok))
                {
                    numero += tok;
                    contador++;
                    try
                    {
                        tok = str.charAt(i+contador);
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        break;
                    }
                }
                i += contador-1;
                cola.add(numero);
                numero = "";
            }
            else if(tok=='+'||tok=='-'||tok=='*'||tok=='/')
            {
                while(!pila.empty()&&prec(pila.peek(), tok))
                    cola.add(String.valueOf(pila.pop()));
                pila.push(tok);
            }
            else if(tok=='(')
                pila.push(tok);
            else if(tok==')')
            {
                while(!pila.empty()&&pila.peek()!='(')
                    cola.add(String.valueOf(pila.pop()));
                pila.pop();
            }
        }
        while(!pila.empty())
            cola.add(String.valueOf(pila.pop()));
        return cola;
    }
}


public class Problema1
{
    static Float solve(Queue<String> cola, float x, Hashtable<String, Queue<String>> tabla)
    {
        Float a, b;
        Stack<Float> pila = new Stack<Float>();
        String elem;
        
        while(!cola.isEmpty())
        {
            elem = cola.poll();
            try
            {
                a = Float.parseFloat(elem);
                pila.push(a);
            }
            catch(NumberFormatException e)
            {
                if(elem.equals("+")||elem.equals("-")||elem.equals("*")||elem.equals("/"))
                {
                    b = pila.pop();
                    a = pila.pop();
                    switch (elem.charAt(0))
                    {
                        case '+':
                            pila.push(a+b);
                            break;
                        case '-':
                            pila.push(a-b);
                            break;
                        case '*':
                            pila.push(a*b);
                            break;
                        case '/':
                            pila.push(a/b);
                            break;
                    }
                }
                else if(elem.equals("x"))
                {
                    pila.push(x);
                }
                else
                {
                    
                    Queue<String> aux = new LinkedList<String>(tabla.get(elem));
                    Float val = solve(aux, x, tabla);
                    pila.push(val);
                }
            }
        }
        return pila.pop();
    }
    public static void main(String args[])
    {
        int n;
        try
        {
            File file = new File("funciones.txt");
            Scanner sc = new Scanner(file);
            n = Integer.parseInt(sc.nextLine());
            Hashtable<String, Queue<String>> tabla = new Hashtable<String, Queue<String>>(n);
            while (sc.hasNextLine())
            {
                String text = sc.nextLine();
                tabla.put(text.substring(0, 1), Parser.parse(text.substring(4)));
            }
            
            Queue<String> aux = new LinkedList<String>(tabla.get("f"));
            
            System.out.println(solve(aux, 1, tabla));
            sc.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Archivo no encontrado");
            System.exit(1);
        }
    }
}