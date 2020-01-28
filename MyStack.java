class EmptyStackException extends Exception{
    public EmptyStackException(String s){
        super(s);
    }
}

public class MyStack<T>{

    private Object[] t;
    private int array_length;
    private int top;

    public MyStack(){
        t = new Object[10];
        array_length = 10;
        top = -1;
    }

    public T top()throws EmptyStackException{
        if(top == -1)
            throw new EmptyStackException("EmptyStackException");
        return (T)t[top];
    }

    public void push(T value){
        if(array_length == top+1){
            array_length += 10;
            Object[] t_dash = new Object[array_length];
            for(int i = 0; i<top+1; i++){
                t_dash[i] = t[i];
            }
            t = t_dash;
        }   
        t[++top] = value;
    }

    public T pop()throws EmptyStackException{
        if(top == -1)
            throw new EmptyStackException("EmptyStackException");
        return (T)t[top--];
    }

    public boolean isEmpty(){
        if(top == -1)
            return true;
        else
            return false;
    }

    public int getStackSize(){
        return top+1;
    }

}