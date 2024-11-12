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
        sdf.setLenient(false); 
  
    
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
                    writer.write("Двоичное число " + token + " преобразовано в десятичное: " + decimalNumber + "\n");
                } else if (isTimeLexeme(token)) {
                        timeLexemes.add(token);
                }
            } catch (NumberFormatException e) {
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
        String[] words = asString.split(" ");                               
        for(String word : words) {
            writer.write("Длина подстроки: " + word + " = " + word.length() +"\n"); }  
        int minLength = asString.length();                                 
        int buffermin = 0;                                                 
        for(int i = 0; i < words.length - 1; i++) {                        
            String temp = words[i];                                         
            if (temp.length() < minLength && isNumber(temp) ) {            
                minLength = temp.length();                                
                buffermin = i;                                             
            }
        }
        asString = subtractSubstring( asString, words[buffermin]); 
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
    private static String subtractSubstring(String input, String pattern) {
        Pattern p = Pattern.compile(pattern);                                   
        Matcher m = p.matcher(input);                                         
        return m.replaceAll("");   
                                     }                                 
    public static boolean isNumber(String str) {                           
       try {
            Integer.parseInt(str);                                           
        } 
       catch (Exception e) {
            return false;
        }
    
        {return true;       }           
                                                         // если соответствует возвращаем true
    }
}

