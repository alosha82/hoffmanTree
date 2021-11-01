//Alexsey Nechaev and Karin

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.*;



public class Main
{
    @SneakyThrows
    public static void main(String[] args)
    {
        String locationOfTheOriginalTextFile="test.txt";
        String locationWhereToSaveZippedTextFile="/test1.txt";
        String locationFromWhereToReadZippedTextFile="/test1.txt";
        String locationWhereToSaveUnzippedTextFile="/test2.txt";
        String s = FileUtils.readFileToString(CommonFunctions.getClassForName(locationOfTheOriginalTextFile),"UTF-8");


        MapCreator1 Map = new MapCreator1();
        CommonFunctions CF = new CommonFunctions();
        HoffmansTree Tree = new HoffmansTree();
        HoffmansNode root;
        String convertedText;
        String hoffmansText;
        String decodedText;
        Map<Character,Double> characterMap=  Map.CreateCharProbabilityTable(s);
        HoffmansNode[] nodesArray = CF.createHoffmansArray(characterMap);
        nodesArray = CF.SortHoffmansArrayByProbability(nodesArray);
        CF.PrintProbabilityOfNodesInArray(nodesArray);
        root = Tree.CreateHoffmansTree(nodesArray);
        Set<Character> setOfKeys = characterMap.keySet();
        Character[] keyChars = setOfKeys.toArray(new Character[0]);
        Map<Character, String> encodingHoffmansMap=Tree.EncodingHoffmansMap(root,keyChars);
        encodingHoffmansMap.forEach((key,val)-> System.out.println("code for char \"" +key+"\": " +val));
        convertedText=CF.CreateTextString(encodingHoffmansMap,s);
        System.out.println(convertedText);
        CF.CreateZippedFile(convertedText,locationWhereToSaveZippedTextFile);


        //read
        hoffmansText = CF.ReadZippedFile(locationFromWhereToReadZippedTextFile);
        decodedText = Tree.DecodingHoffmansCode(root,hoffmansText);
        CF.WriteToFile(decodedText,locationWhereToSaveUnzippedTextFile);

        System.out.println(decodedText);




    }

}
