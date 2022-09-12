package com.example.stockproject.model;

import java.sql.SQLOutput;

public class CreateDocSeq {

    private StringBuilder createStr(String currstr) {
        StringBuilder str = new StringBuilder();
        int a = currstr.charAt(0);
        int b = currstr.charAt(1);
        int c = Character.getNumericValue(currstr.charAt(2));;
        int d = Character.getNumericValue(currstr.charAt(3));;
        int e = Character.getNumericValue(currstr.charAt(4));;

        if (e != 9) {
            str.append(Character.toChars(a));
            str.append(Character.toChars(b));
            str.append(c);
            str.append(d);
            str.append(e + 1);
        } else {
            if (d != 9) {
                str.append(Character.toChars(a));
                str.append(Character.toChars(b));
                str.append(c);
                str.append(d + 1);
                str.append(0);
            } else {
                if (c != 9) {
                    str.append(Character.toChars(a));
                    str.append(Character.toChars(b));
                    str.append(c + 1);
                    str.append(0);
                    str.append(0);
                } else {
                    if (b != 90) {
                        str.append(Character.toChars(a));
                        str.append(Character.toChars(b+1));
                        str.append(0);
                        str.append(0);
                        str.append(0);
                    } else {
                        if (a != 90) {
                            str.append(Character.toChars(a+1));
                            str.append(Character.toChars(60));
                            str.append(0);
                            str.append(0);
                            str.append(0);
                        } else if (a == 90) {
                            System.out.println("Value broke!");
                        }

                    }
                }
            }

        }

        return str;
    }

    public static void main(String args[]) {
//        StringBuilder str = new StringBuilder();
//        char a=80;
//        for(int i=65;i<=90;i++){
//            for(int j=65;j<=90;j++){
//                for(int k=0;k<=9;k++){
//                    for(int l=0;l<=9;l++){
//                        for(int m=0;m<=9;m++){
//                            str.append((char) i);
//                            str.append((char) j);
//                            str.append(k);
//                            str.append(l);
//                            str.append(m);
//                            System.out.println(str);
//                        }
//                    }
//                }
//            }
//
//        }
        CreateDocSeq cr = new CreateDocSeq();
        StringBuilder ans = cr.createStr("BC000");
        System.out.println(ans);
    }
}

