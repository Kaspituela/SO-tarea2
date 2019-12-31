import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


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
    
    static Queue parse(String str)
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
    public static void main(String args[])
    {
        Queue cola = Parser.parse("4+4");
        while(!cola.isEmpty())
            System.out.println(cola.poll());
    }
}