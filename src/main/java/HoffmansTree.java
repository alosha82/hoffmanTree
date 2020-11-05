import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class HoffmansTree
{
    public HoffmansNode CreateHoffmansTree (HoffmansNode[] array)
    {
        HoffmansNode root = new HoffmansNode();
        Vector<HoffmansNode> hoffmanNodes = new Vector(array.length);
        double tempProbability;
        for (int i = 0; i <array.length ; i++) {
            hoffmanNodes.add(array[i]);
        }
        while(hoffmanNodes.size()>1)
        {
            int index=0;
            HoffmansNode tempNode = new HoffmansNode();
            tempProbability = hoffmanNodes.get(0).getProbability() + hoffmanNodes.get(1).getProbability();
            tempNode.setProbability(tempProbability);
            tempNode.setCharacter(null);
            tempNode.setLeft(hoffmanNodes.get(0));
            tempNode.setRight(hoffmanNodes.get(1));
            tempNode.setBacktoleft(null);
            tempNode.setBacktoright(null);
            tempNode.getLeft().setBacktoleft(tempNode);
            tempNode.getRight().setBacktoright(tempNode);
            hoffmanNodes.remove(0);
            hoffmanNodes.remove(0);
            for (int i = 0; i <hoffmanNodes.size() ; i++)
            {
                if (tempNode.getProbability()>hoffmanNodes.get(i).getProbability())
                    index=i;

            }
            if(hoffmanNodes.size()==0)
                root = tempNode;
            else
                hoffmanNodes.insertElementAt(tempNode,index+1);
        }
        return root;

    }
    public String CodeCreation (HoffmansNode node)
    {
        String code = "";
        String reversedCode = "";
        while((node.getBacktoleft()!=null)||(node.getBacktoright()!= null))
        {
            if (node.getBacktoleft() != null)
            {
                node = node.getBacktoleft();
                code = code + "0";

            }
            else
            {
                node = node.getBacktoright();
                code = code + "1";
            }
        }
        for(int i = code.length() - 1; i >= 0; i--)
        {
            reversedCode = reversedCode + code.charAt(i);
        }
        return reversedCode;
    }
    public Map<Character,String> EncodingHoffmansMap(HoffmansNode node, Character[] keyChars)
    {
        Map<Character,String> hofmansMap= new HashMap<>();
        HoffmansNode tempNode ;
        for (int i = 0; i <keyChars.length ; i++)
        {
            tempNode=node.FindNodeByKey(keyChars[i]);
            hofmansMap.put(keyChars[i],CodeCreation(tempNode));
        }
        return hofmansMap;
    }
    public String DecodingHoffmansCode(HoffmansNode root,String hoffmansText)
    {
        String decodedText="";
        String tempString="";
        HoffmansNode tempNode;
        for (int i = 0; i <hoffmansText.length() ; i++)
        {
            tempString=tempString+hoffmansText.charAt(i);
            tempNode=root;

            for (int j = 0; j < tempString.length(); j++)
            {

                if (tempString.charAt(j)=='0')
                {
                    tempNode=tempNode.getLeft();
                }
                else
                if (tempString.charAt(j)=='1')
                {
                    tempNode=tempNode.getRight();
                }
            }
            if (tempNode.getCharacter()!= null)
            {
                decodedText=decodedText+tempNode.getCharacter();
                tempString="";
            }
        }
        return decodedText;
    }

}
