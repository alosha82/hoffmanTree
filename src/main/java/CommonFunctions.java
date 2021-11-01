import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class CommonFunctions {
    @SneakyThrows
    public static File getClassForName(String path) {
        URL res = CommonFunctions.class.getClassLoader().getResource(path);
        return new File(res.toURI());
    }

    public HoffmansNode[] createHoffmansArray(Map<Character, Double> characterMap) {
//        return characterMap.entrySet().stream()
//                .map(entry -> {
//                    HoffmansNode hn = new HoffmansNode();
//                    hn.setCharacter(entry.getKey());
//                    hn.setProbability(entry.getValue());
//                    return hn;
//                })
//                .toArray(HoffmansNode[]::new);

        HoffmansNode[] nodesArray = new HoffmansNode[characterMap.keySet().size()];
        Set<Character> setOfKeys = characterMap.keySet();
        Character[] keyChars = setOfKeys.toArray(new Character[0]);
        for (int i = 0; i < keyChars.length; i++) {
            nodesArray[i] = new HoffmansNode();
            val probability = characterMap.get(keyChars[i]);
            nodesArray[i].setProbability(probability);
            nodesArray[i].setCharacter(keyChars[i]);
            nodesArray[i].setLeft(null);
            nodesArray[i].setRight(null);
            nodesArray[i].setBacktoleft(null);
            nodesArray[i].setBacktoright(null);
        }
        return nodesArray;
    }

    public HoffmansNode[] SortHoffmansArrayByProbability(HoffmansNode[] array) {
        Arrays.sort(array, Comparator.comparing(HoffmansNode::getProbabilityObj));
        return array;
    }

    public String CreateTextString(Map<Character, String> hoffmansMap, String text) {
        String convertedText = "";
        for (int i = 0; i < text.length(); i++) {
            convertedText = convertedText + hoffmansMap.get(text.charAt(i));
        }
        return convertedText;
    }

    public BitSet fromString(String binary) {
        BitSet bitset = new BitSet(binary.length());
        for (int i = 0; i < binary.length(); i++) {
            bitset.set(i, binary.charAt(i) == '1');
        }
        return bitset;
    }

    @SneakyThrows
    public void CreateZippedFile(String s, String pathFile) {
        File file = new File(new File(".").getCanonicalPath() + pathFile);
        FileUtils.writeByteArrayToFile(file, fromString(s).toByteArray());
    }

    public void WriteToFile(String s, String pathFile) {
        File file = new File(pathFile);
        try {
            FileUtils.write(file, s, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ReadZippedFile(String pathFile1) {
        String hoffmanText = "";
        byte[] byteArr = new byte[0];
        try {
            byteArr = FileUtils.readFileToByteArray(new File(new File(".").getCanonicalPath()+pathFile1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BitSet bs = BitSet.valueOf(byteArr);

        boolean[] booleans = new boolean[byteArr.length*8];
        //boolean[] booleans = new boolean[bs.size()];
        bs.stream().forEachOrdered(index -> booleans[index] = true);
        System.out.println();
        for (int i = 0; i < booleans.length; i++) {
            if (booleans[i] == true) {
                hoffmanText = hoffmanText + "1";
            } else {
                hoffmanText = hoffmanText + "0";
            }
        }
        System.out.println(hoffmanText);
        return hoffmanText;
    }

    public void PrintProbabilityOfNodesInArray(HoffmansNode[] nodesArray) {
        List<Double> probList = Arrays.stream(nodesArray)
                .map(HoffmansNode::getProbability)
                .collect(Collectors.toList());
        System.out.println(probList);
    }

}
