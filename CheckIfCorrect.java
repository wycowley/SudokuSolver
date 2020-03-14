public class CheckIfCorrect{
    private int[] nineNums = new int[9];
    public boolean check(int[] values){
        
        //Probably works
        for(int i = 0;i<3;i++){
            for(int k = 0;k<3;k++){
                //3x3 grid
                for(int j = 0;j<3;j++){
                    for(int l = 0;l<3;l++){
                        nineNums[j*3+l] = values[l+k*3+j*9+i*27];
                    }
                }
                if(!check9(nineNums)){
                    return false;
                }
            }
        }

        //Good, for all the rows
        for(int i = 0;i<9;i++){
            for(int k = 0;k<9;k++){
                nineNums[k] = values[k+i*9];
                
            }
            
            if(!check9(nineNums)){

                return false;
            }
        }
        
        //Good, for all the columns
        for(int i = 0;i<9;i++){
            for(int k = 0;k<9;k++){
                nineNums[k] = values[k*9+i];
            }
            
            if(!check9(nineNums)){

                return false;
            }
        }




        return true;
    }
    public boolean check9(int[] nums){
        int x = 0;
        for(int k = 1;k<=nums.length;k++){
            for(int i = 0;i<nums.length;i++){
                if(nums[i]==k){
                    x = 1;
                }
                if(nums[i]==0){
                    return false;
                }
            }
            if(x==0){
                return false;
            }
            x = 0;
        }
        return true;

    }
}