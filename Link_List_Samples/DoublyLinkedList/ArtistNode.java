public class ArtistNode {
    private Artist data;
    private ArtistNode next;
    private ArtistNode prev;

    public ArtistNode(Artist data) {
        if (data == null) {
            throw new IllegalArgumentException("ArtistNode data cannot be null.");
        }
        this.data = data;
    }

    public Artist getData() {
        return data;
    }

    public void setData(Artist data) {
        if (data == null) return;
        this.data = data;
    }

    public ArtistNode getNext() {
        return next;
    }

    public void setNext(ArtistNode next) {
        this.next = next;
    }

    public ArtistNode getPrev() {
        return prev;
    }

    public void setPrev(ArtistNode prev) {
        this.prev = prev;
    }
}