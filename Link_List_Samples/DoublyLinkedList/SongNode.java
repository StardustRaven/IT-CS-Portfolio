/*
 * SongNode.java
 * Author: Star Isakson
 * Course: CS II
 * Date: March 2026
 *
 * Description:
 * A node used in the doubly linked playlist.
 * Each node stores a Song object and references
 * to the next and previous nodes in the list.
 */

public class SongNode {
    private Song data;
    private SongNode next;
    private SongNode prev;

    public Song getData() {
        return data;
    }

    public SongNode getNext() {
        return next;
    }

    public SongNode getPrev() {
        return prev;
    }

    public SongNode(Song data) {
        if (data == null) {
            throw new IllegalArgumentException("SongNode data cannot be null.");
        }
        this.data = data;
    }

    public Song getData() {
        return data;
    }

    public void setData(Song data) {
        if (data == null)
            return;
        this.data = data;
    }

    public SongNode getNext() {
        return next;
    }

    public void setNext(SongNode next) {
        this.next = next;
    }

    public SongNode getPrev() {
        return prev;
    }

    public void setPrev(SongNode prev) {
        this.prev = prev;
    }
}