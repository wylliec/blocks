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
        // HashCodeBuilder
        int sum = 0;
        for (int i = 0; i < this.block.length; i++) {
            sum += (int)Math.pow(256, i) * (block[i] % 256);
        }
        System.out.println("hash: " + sum);
        return sum;
//        int hash = block[0];
//        hash *= 37;
//        hash += block[1];
//        hash *= 37;
//        hash += block[2];
//        hash *= 37;
//        hash += block[3];
//        return hash;
    }

    public String toString(){
        return Arrays.toString(block);
    }
}
