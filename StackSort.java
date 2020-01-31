public class StackSort{
 
    public StackSort(){
    }

    private boolean isSorted(int arr[]){
        for(int i = 0; i<arr.length-1; i++)
            if(arr[i]>arr[i+1])
                return false;
        return true;
    }

    private String[] returnSteps(int[] nums){
        String[] output = new String[2*nums.length];
        try{
            MyStack<Integer> st = new MyStack<Integer>();
            int index = 0;
            int sortedIndex = 0;
            int[] sorted = new int[nums.length];
            for(int i = 0; i<nums.length; i++){
                if(st.isEmpty()){
                    st.push(nums[i]);
                    output[index++] = "PUSH";
                }
                else{
                    while(st.isEmpty() != true && nums[i] > st.top()){
                        sorted[sortedIndex++] = st.pop();
                        output[index++] = "POP";
                    }
                    st.push(nums[i]);
                    output[index++] = "PUSH";
                }
            }
            while(st.isEmpty() != true){
                sorted[sortedIndex++] = st.pop();
                output[index++] = "POP";
            }
            for(int i = 0; i<sorted.length; i++)
                nums[i] = sorted[i];
        }
        catch(EmptyStackException e){
            System.out.println(e);
        }
        return output;
    }

    public String[] sort(int[] nums){
        int[] nums2 = new int[nums.length];
        for(int i = 0; i<nums.length; i++)
            nums2[i] = nums[i];
        String[] output = returnSteps(nums2);
        String[] error = {"NOTPOSSIBLE"};
        if(isSorted(nums2) != true)
            return error;
        return output;
    }

    public String[][] kSort(int nums[]){
        int[] nums2 = new int[nums.length];
        for(int i = 0; i<nums.length; i++)
            nums2[i] = nums[i];
        MyStack<String[]> st = new MyStack<String[]>();
        do{
            st.push(returnSteps(nums2));
        }while(isSorted(nums2) != true);
        String[][] output = new String[st.getStackSize()][2*nums.length];
        try{
            for(int i = st.getStackSize()-1; i>=0; i--){
                output[i] = st.pop();
            }
        }
        catch(EmptyStackException e){
            System.out.println(e);
        }
        return output;
    }

}