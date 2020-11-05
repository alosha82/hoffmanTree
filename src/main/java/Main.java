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
        String locationOfTheOriginalTextFile="D:\\games alexsey\\Zipper\\gtgame.txt";
        String locationWhereToSaveZippedTextFile="D:\\games alexsey\\Zipper\\gtgame1.txt";
        String locationFromWhereToReadZippedTextFile="D:\\games alexsey\\Zipper\\gtgame1.txt";
        String locationWhereToSaveUnzippedTextFile="D:\\games alexsey\\Zipper\\gtgame2.txt";
        String s = FileUtils.readFileToString(new File(locationOfTheOriginalTextFile),"UTF-8");


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
