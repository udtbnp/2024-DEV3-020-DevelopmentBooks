package be.bnp.demo.models;

public class BucketComparator implements java.util.Comparator<Bucket>{
    @Override
    public int compare(Bucket a, Bucket b) {
        
        if (a == null && b == null){
            return 0;
        }
        if (a == null){
            return 1;
        }

        if (a.getBookList().size() == 3 && b.getBookList().size()== 4){
            return -1;
        } 
        else {
            if (a.getBookList().size() > b.getBookList().size()){
                return -1;
            }
            if (a.getBookList().size() == b.getBookList().size()){
                return 0;
            }
            if (a.getBookList().size() < b.getBookList().size()){
                return 1;
            }
        }
        return 0;
    }
}
