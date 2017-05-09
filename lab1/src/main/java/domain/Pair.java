package domain;

public class Pair<L,R> {

    private  L left;
    private  R right;

    public Pair(){

    };


    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public Pair setLeft(L left) {
        this.left = left;
        return this;
    }

    public Pair setRight(R right) {
        this.right = right;
        return this;
    }

    public L getLeft() { return left; }
    public R getRight() { return right; }

    @Override
    public int hashCode() { return left.hashCode() ^ right.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair pairo = (Pair) o;
        return this.left.equals(pairo.getLeft()) &&
                this.right.equals(pairo.getRight());
    }

}