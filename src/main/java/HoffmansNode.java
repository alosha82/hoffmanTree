public class HoffmansNode
{
    private double probability=0;
    private Character character=0;

    private HoffmansNode left;
    private HoffmansNode right;
    private HoffmansNode backtoright;
    private HoffmansNode backtoleft;

    @Override
    public String toString() {
        return "HoffmansNode{" +
                "probability=" + probability +
                ", character=" + character +
                '}';
    }

    public HoffmansNode getRight() {
        return right;
    }

    public void setRight(HoffmansNode right) {
        this.right = right;
    }

    public double getProbability() {
        return probability;
    }

    public Double getProbabilityObj() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public Character getCharacter() {
        return character;
    }

    public HoffmansNode FindNodeByKey (Character v){
        HoffmansNode result = null;

        //check for value first
        if(this.getCharacter() == v) return this;

        //check if you can go any further from the current node
        if(this.getLeft() == null && this.getRight() == null) return null;

        //now go right
        result = this.getRight().FindNodeByKey(v);

        //check the node
        if(result != null && result.getCharacter() == v) return result;

        //if not found return whatever is returned by searching left
        return this.getLeft().FindNodeByKey(v);
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public HoffmansNode getLeft() {
        return left;
    }

    public void setLeft(HoffmansNode left) {
        this.left = left;
    }

    public HoffmansNode getBacktoright() {
        return backtoright;
    }

    public void setBacktoright(HoffmansNode backtoright) {
        this.backtoright = backtoright;
    }

    public HoffmansNode getBacktoleft() {
        return backtoleft;
    }

    public void setBacktoleft(HoffmansNode backtoleft) {
        this.backtoleft = backtoleft;
    }
}
