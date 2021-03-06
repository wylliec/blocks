package Blocks;

import java.util.Arrays;

/**
 * Created by Caleb on 4/19/2016.
 */
public class Block {
    // The number of arguments to create a block
    public static final int N = 4;

    private int[] block;

    public Block(int[] block) {
        if(block.length != Block.N) {
            throw new IllegalArgumentException("Block must have " + Block.N + " parameters");
        }
        this.block = block;
    }

    public Block(int h, int w, int y, int x) {
        this(new int[]{h, w, y, x});
    }

    public void move(Board.Direction d) {
        block[2] += d.getY();
        block[3] += d.getX();
    }
    public void unmove(Board.Direction d) {
        block[2] -= d.getY();
        block[3] -= d.getX();
    }

    public int getH() {
        return block[0];
    }

    public int getW() {
        return block[1];
    }

    public int getY() {
        return block[2];
    }

    public int getX() {
        return block[3];
    }

    public Block deepCopy() {
        return new Block(getH(), getW(), getY(), getX());
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || !(o instanceof Block)) {
            return false;
        }
        Block other = (Block)o;
        if(other.block.length != Block.N || this.block.length != Block.N) {
            throw new IllegalStateException("Blocks must have " + Block.N + " parameters");
        }
        for (int i = 0; i < Block.N; i++) {
            if(other.block[i] != this.block[i]){
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < this.block.length; i++) {
            sum = (sum << 8) + (block[i] % 256);
        }
        return sum;
    }

    public String toString(){
        return getH() + " " + getW() + " " + getY() + " " + getX();
    }

    public int distanceTo(Block other) {
        return Math.abs(this.getX() - other.getX()) + Math.abs(this.getY() - other.getY());
    }

    public boolean sameType(Block other) {
        return this.getH() == other.getH() && this.getW() == other.getW();
    }
}
