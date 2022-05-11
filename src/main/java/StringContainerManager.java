public class StringContainerManager {
    private Container nextInChain = null;
    private Boolean targetReached = false;

    public void removeElement(String data, Container currentNode) {
        if (currentNode.getPostCode().equals(data)) {
            nextInChain = currentNode.getNextElement();
            targetReached = true;
            return;
        } else {
            removeElement(data, currentNode.getNextElement());
        }
        if (targetReached) {
            currentNode.setNextElement(nextInChain);
            targetReached = false;
        }
    }

    public void removeElement(int index, Container currentNode, int recursionDepth) {
        if (recursionDepth == 0) {
            currentNode = currentNode.getNextElement();
        }
        if (currentNode.getIndex() == index) {
            nextInChain = currentNode.getNextElement();
            targetReached = true;
            return;
        } else {
            removeElement(index, currentNode.getNextElement(), recursionDepth + 1);
        }
        if (targetReached) {
            currentNode.setNextElement(nextInChain);
            targetReached = false;
        }
    }
}