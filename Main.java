package com.test;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws K_CalcException {
        Scanner input = new Scanner(System.in);
        String exp = input.nextLine();
        int[] data = proCess(exp);
        if (data[0] == -1) {
            throw new K_CalcException("incorrect expression, input two arabic(range 0-10) or roman(range I - X) numbers and arithmetic operator in format <number> <operator> <number>]");
        } else {
            int result = Calc.calcuLate(data);
            if (result == -11) {
                throw new K_CalcException("zero divide error");
            } else {
                if (data[3] == 1) {
                    System.out.println(Calc.calcuLate(data));
                } else if (data[3] == 2) {
                    if (result >= 1) {
                        System.out.println(Latin.toarabR100(Calc.calcuLate(data)));
                    } else {
                        throw new K_CalcException("result of operation with roman numbers is less than 1");
                    }
                }
            }
        }
    }

    static int[] proCess(String input) {
        String[] checkArab = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] checkLatin = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        boolean isArab = false;
        boolean isLatin = false;
        int type1 = 3;
        int type2 = 3;
        int optype = 4;
        String[] data = new String[2];
        int[] expression = {-1, -1, -1, -1};
        char[] checkOperators = {'+', '-', '*', '/'};
        int operators = 0;
        char operator = '!';
        if (input.isEmpty()) {
            return expression;
        }
        input = input.replaceAll(" ", "");
        char[] charinput = input.toCharArray();
        for (char c : charinput) {
            for (char x : checkOperators) {
                if (Character.compare(c, x) == 0) {
                    operators++;
                    operator = c;
                }
                if (operators > 1) {
                    return expression;
                }
            }
        }
        if (operators == 0) {
            return expression;
        }
        if (operator == '+') {
            String[] delim = input.split("\\+");
            if (delim.length == 2) {
                for (int i = 0; i < delim.length; i++)
                    data[i] = delim[i];
                optype = 0;
            } else {
                return expression;
            }
        }
        if (operator == '*') {
            String[] delim = input.split("\\*");
            if (delim.length == 2) {
                for (int i = 0; i < delim.length; i++)
                    data[i] = delim[i];
                optype = 2;
            } else {
                return expression;
            }
        }
        if (operator == '-') {
            String[] delim = input.split("-");
            if (delim.length == 2) {
                for (int i = 0; i < delim.length; i++)
                    data[i] = delim[i];
                optype = 1;
            } else {
                return expression;
            }
        }
        if (operator == '/') {
            String[] delim = input.split("/");
            if (delim.length == 2) {
                for (int i = 0; i < delim.length; i++)
                    data[i] = delim[i];
                optype = 3;
            } else {
                return expression;
            }
        }
        for (String s : checkArab) {
            if (s.equals(data[0])) {
                isArab = true;
            }
        }
        for (String s : checkLatin) {
            if (s.equals(data[0])) {
                isLatin = true;
            }
        }
        if (isArab && !isLatin) {
            type1 = 1;
        } else if (!isArab && isLatin) {
            type1 = 2;
        }
        isArab = false;
        isLatin = false;
        for (String s : checkArab) {
            if (s.equals(data[1])) {
                isArab = true;
            }
        }
        for (String s : checkLatin) {
            if (s.equals(data[1])) {
                isLatin = true;
            }
        }
        if (isArab && !isLatin) {
            type2 = 1;
        } else if (!isArab && isLatin) {
            type2 = 2;
        }
        if (((type1 - type2) == 0) && type1 != 3) {
            if (type1 == 2) {
                data[0] = Latin.toarabinput(data[0]);
                data[1] = Latin.toarabinput(data[1]);
            }
            expression[0] = Integer.parseInt(data[0]);
            expression[1] = Integer.parseInt(data[1]);
            expression[2] = optype;
            expression[3] = type1;
        }
        return expression;
    }
}

class Calc {
    static int calcuLate(int[] expression) {
        int result = -11;
        if (expression[2] == 3 && expression[1] == 0){
            return result;
        }
        switch (expression[2]) {
            case 0:
                result = expression[0] + expression[1];
                break;
            case 1:
                result = expression[0] - expression[1];
                break;
            case 2:
                result = expression[0] * expression[1];
                break;
            case 3:
                result = expression[0] / expression[1];
                break;
        }
        return result;
    }
}

class Latin {
    static String toarabinput(String number) {
        String arab = "fail";
        switch (number) {
            case "I":
                arab = "1";
                break;
            case "II":
                arab = "2";
                break;
            case "III":
                arab = "3";
                break;
            case "IV":
                arab = "4";
                break;
            case "V":
                arab = "5";
                break;
            case "VI":
                arab = "6";
                break;
            case "VII":
                arab = "7";
                break;
            case "VIII":
                arab = "8";
                break;
            case "IX":
                arab = "9";
                break;
            case "X":
                arab = "10";
                break;
        }
        return arab;
    }

    static String toarabR100(int number) {
        String result;
        String m = "";
        String s = "";
        int fnum = number / 10;
        int ost = number % 10;
        switch (ost) {
            case 0:
                s = "";
                break;
            case 1:
                s = "I";
                break;
            case 2:
                s = "II";
                break;
            case 3:
                s = "III";
                break;
            case 4:
                s = "IV";
                break;
            case 5:
                s = "V";
                break;
            case 6:
                s = "VI";
                break;
            case 7:
                s = "VII";
                break;
            case 8:
                s = "VIII";
                break;
            case 9:
                s = "IX";
                break;

        }
        switch (fnum) {
            case 0:
                m = "";
                break;
            case 1:
                m = "X";
                break;
            case 2:
                m = "XX";
                break;
            case 3:
                m = "XXX";
                break;
            case 4:
                m = "XL";
                break;
            case 5:
                m = "L";
                break;
            case 6:
                m = "LX";
                break;
            case 7:
                m = "LXX";
                break;
            case 8:
                m = "LXXX";
                break;
            case 9:
                m = "XC";
                break;
            case 10:
                m = "C";
                break;
        }
        result = m + s;
        return result;
    }
}

class K_CalcException extends Exception{
    K_CalcException(String m){
    super(m);}
}
