import java.util.HashMap;
import java.util.Map;

public class MapCreator1
{
    public Map<Character,Double> CreateCharProbabilityTable(String s)
    {
        Map<Character,Double> characterMap= new HashMap<>();


        for (int i = 0; i <s.length() ; i++)
        {
            Character currentChar = s.charAt(i);
            Double occurances = characterMap.get(currentChar);
            if(occurances==null)
                occurances=0.0;
            characterMap.put(currentChar,occurances+1);
        }
        characterMap.forEach((key,val)-> characterMap.put(key,val/s.length()));
        characterMap.forEach((key,val)-> System.out.println("prob for char \"" +key+"\": " +val));
        return characterMap;
    }



}

