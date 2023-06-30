package com.bme.syx.lambdas;

import java.util.HashMap;
import java.util.Map;

public class test {


    public static void main1(String[] args) {

        int[]  nums = {1,2,4,5,6,5,7,3,11,15};
        int targe = 10;

        Map<Integer,Integer> map = new HashMap<>();

        for(int i =0 ; i<nums.length ; i++){
            if(map.containsKey(targe-nums[i])){
                System.out.println(map.get(targe-nums[i]));
                System.out.println(i);
            }else{
                map.put(nums[i],i);
            }
        }
    }

    public static void main2(String[] args) {

        int[] nums = {1,2,4,5,6,5,7,3,11,15};
        int targe = 10;
        for(int a = 0 ; a<nums.length;a++) {
            for(int b = 0; b< nums.length;b++){
                if(nums[a] + nums[b] == targe){
                    System.out.println( a+" - "+b);
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] nums = {5,2,5};
        int targe = 10;
        Map<Integer,Integer> map = new HashMap<>();
        for(int a = 0 ; a<nums.length;a++) {
            map.put(nums[a],a);
        }
        for(int a = 0 ; a<nums.length;a++) {
            if(map.containsKey(targe - nums[a])){
                int v = map.get(targe - nums[a]);
                if(v!=a ){
                    System.out.println(map.get(targe - nums[a])+" - "+a);

                }
            }
        }

    }

}
