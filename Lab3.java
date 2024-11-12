import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class DateValidatorTest {
    public boolean isThisDateValid(String dateToValidate, String dateFromat)
    {
        try(
        FileWriter fileWriter = new FileWriter("output.txt");
        BufferedWriter writer = new BufferedWriter(fileWriter)){
        if(dateToValidate == null){
            return false;
        }
        writer.write(dateToValidate);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false); // Устанавливает мягкий (lenient) или жёсткий (non-lenient)/  false //синтаксический анализ
  
    
         sdf.parse(dateToValidate);
        } catch (Exception e) {
            return false;
        }
            return true;
    }
}
public class Lab3 {
public static void main(String[] args) {
try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
FileWriter fileWriter = new FileWriter("output.txt");
BufferedWriter writer = new BufferedWriter(fileWriter)) {
        String lexemes = reader.readLine();
        String delimiters = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(lexemes, delimiters);
        List<String> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        List<String> binaryNumbers = new ArrayList<>();
        List<String> timeLexemes = new ArrayList<>();
        Random rand = new Random();

        for (String token : tokens) {
            try {
                if (isBinaryNumber(token)) {
                    binaryNumbers.add(token);
                    int decimalNumber = Integer.parseInt(token, 2);
                   // int randomNum = rand.nextInt(100);
                    writer.write("Двоичное число " + token + " преобразовано в десятичное: " + decimalNumber + "\n");
                } else if (isTimeLexeme(token)) {
                        timeLexemes.add(token);
                }
            } catch (NumberFormatException e) {
                // Ignore non-numeric tokens
            }
        }
        DateValidatorTest dv=new DateValidatorTest();
        Formatter writ = new Formatter();
        writ.format(binaryNumbers + " ");
        writer.write(binaryNumbers+""); 
        writer.write("\nВремя (HH\\mm\\ss): \n");
        Collections.sort(timeLexemes);
        for(String time:timeLexemes)
        {dv.isThisDateValid(time, "HH\\mm\\ss");}
        writer.write(timeLexemes + "\n");
        String asString=lexemes;
        int randomNum = rand.nextInt(10);
        writer.write("randomnum="+randomNum+ "\n");
        if (binaryNumbers.isEmpty()) {
            int mid = lexemes.length() / 2;
            lexemes = lexemes.substring(0, mid) + randomNum + lexemes.substring(mid);
        } else {
            for (String token : tokens)
            {
            if( isBinaryNumber(token))
            {
                lexemes = lexemes.replace(token, token+randomNum);
            }   
        }
        }
        writer.write("Измененная строка, с добавленным случайным числом после числа 2-й с\\с: \n" + lexemes + "\n");
        asString = asString.replace(".", ""); 
        asString = asString.replace(",", "");       
        asString = asString.replace("  ", " ");          
        String[] words = asString.split(" ");                         // методом split() разбиваем строку asString           
        for(String word : words) {
            writer.write("Длина подстроки: " + word + " = " + word.length() +"\n"); }  
        // Определение подстроки минимальной длины
        int minLength = asString.length();                                 
        int buffermin = 0;                                                  // переменной buffermin присваиваем номер первого элемента строкового массива
        for(int i = 0; i < words.length - 1; i++) {                         // сравниваем длины всех подстрок
            String temp = words[i];                                         // переменной temp присваиваем значение i-той подстроки
            if (temp.length() < minLength && isNumber(temp) ) {             // если длина i-той подстроки меньше записанной в minLength и подстрока число-вызываем функцию isNumber
                minLength = temp.length();                                  // её записываем в minLength
                buffermin = i;                                              // переменной buffermin присваиваем номер i-той подстроки минимальной длины
            }
        }
        asString = subtractSubstring( asString, words[buffermin]); // вызываем функцию subtractSubstring удаления подстроки buffermin из строки lexemes
        writer.write("\nСтрока с удаленной подстрокой минимальной длины: \n" + asString+ "\n");
        writ.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
private static boolean isBinaryNumber(String str) {
    for (char c : str.toCharArray()) {
        if (c != '0' && c != '1') {
            return false;
        }
    }
    return true;
}
private static boolean isTimeLexeme(String str) {
String regex="^([01]?[0-9]|2[0-3])[\\\\][0-5][0-9][\\\\][0-5][0-9]$";              
if(str.matches(regex))
{
    return true;
}
return false;
}
// ********** Функция удаления подстроки из строки **********
    private static String subtractSubstring(String input, String pattern) {
        Pattern p = Pattern.compile(pattern);                                   // вызваем статический метод compile и передаем в качестве первого аргумента строку
        Matcher m = p.matcher(input);                                           // вызваем метод matches(), коорый принимает регулярное выражение и возвращает true, если строка соответствует этому выражению
        return m.replaceAll("");   
                                     }                                 

//********** Функция для проверки того, является ли строка числом или нет **********
    public static boolean isNumber(String str) {   
       //str.toCharArray(); 
       //char c=str.charAt(0);                           
       try {
            Integer.parseInt(str);                                              // вызваем метод parseInt - преобразование строки в число, является ли она целым числом
        } 
       catch (Exception e) {
            return false;
        }
    
        {return true;       }           
                                                         // если соответствует возвращаем true
    }
}

