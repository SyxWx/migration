package com.bme.syx;

import java.util.Arrays;
import java.util.List;

public class listconTest {


    public static void main(String[] args) {
         List<String> list = Arrays.asList("额定压差(Pa)","额定电流(A)");

        if(list.contains("额定压差(Pa)")){
            System.out.println("额定压差(Pa)");

        }if(list.contains("额定电流(A)")){
            System.out.println("额定电流(A)");
        }else{
            System.out.println("2");
        }
    }
}


