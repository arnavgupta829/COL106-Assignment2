public class MyCalculator{
    
    public MyCalculator(){
    }

    private String removeWhiteSpaces(String input){
        String out = "";
        for(int i = 0; i<input.length(); i++){
            if(input.charAt(i) != ' ')
                out+=String.valueOf(input.charAt(i));
        }
        return out;
    }

    private int checkPrec(String a){
        switch(a){
            case "+":
                return 0;
            case "-":
                return 1;
            case "*":
                return 2;
        }
        return 0;
    }
    
    public int calculate(String expression){ //throws EmptyStackException
        int output = 0;
        try{
            MyStack<String> infix = new MyStack<String>();
            MyStack<String> postfix = new MyStack<String>();
            MyStack<String> temp = new MyStack<String>();
            String expression2 = removeWhiteSpaces(expression);
            String tempStr = "";
            for(int i = expression2.length()-1; i>-1; i--){
                if(expression2.charAt(i) == '+' || expression2.charAt(i) == '-' || expression2.charAt(i) == '*' || expression2.charAt(i) == '(' || expression2.charAt(i) == ')'){
                    infix.push(tempStr);
                    infix.push(String.valueOf(expression2.charAt(i)));
                    tempStr = "";
                }
                else
                    tempStr=String.valueOf(expression2.charAt(i))+tempStr;
            }
            infix.push(tempStr);
            while(infix.isEmpty() != true){
                if(!(infix.top().equals("(") || infix.top().equals(")") || infix.top().equals("*") || infix.top().equals("+") || infix.top().equals("-"))){
                    postfix.push(infix.pop());
                }
                else if(infix.top().equals("(")){
                    temp.push(infix.pop());
                }
                else if(infix.top().equals(")")){
                    while(temp.isEmpty() != true && !(temp.top().equals("("))){
                        postfix.push(temp.pop());
                    }
                    temp.pop();
                    infix.pop();
                }
                else{
                    while(temp.isEmpty() != true && checkPrec(infix.top()) < checkPrec(temp.top())){
                        postfix.push(temp.pop());
                    }
                    temp.push(infix.pop());
                }
            }
            while(temp.isEmpty() != true)
                postfix.push(temp.pop());
            while(postfix.isEmpty() != true){
                if(!(postfix.top().equals("")))
                    temp.push(postfix.pop());
                else
                    postfix.pop();
            }
            while(temp.isEmpty() != true){
                if(!(temp.top().equals("*") || temp.top().equals("+") || temp.top().equals("-"))){
                    postfix.push(temp.pop());
                }
                else{
                    int a = Integer.valueOf(postfix.pop());
                    int b = Integer.valueOf(postfix.pop());
                    if(temp.top().equals("*")){
                        postfix.push(String.valueOf(a*b));
                        temp.pop();
                    }
                    else if(temp.top().equals("+")){
                        postfix.push(String.valueOf(a+b));
                        temp.pop();
                    }
                    else{
                        postfix.push(String.valueOf(b-a));
                        temp.pop();
                    }
                }
            }
            output = Integer.valueOf(postfix.pop());
        }
        catch(EmptyStackException e){
            System.out.println(e);
        }
        return output;
    }

}